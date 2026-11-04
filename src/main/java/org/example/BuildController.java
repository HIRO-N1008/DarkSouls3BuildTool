package org.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BuildController {

    private final WeaponService weaponService;
    private final OriginService originService;
    private final ArmorService armorService;
    private final RingService ringService;

    public BuildController(
            WeaponService weaponService,
            OriginService originService,
            ArmorService armorService,
            RingService ringService
    ) {
        this.weaponService = weaponService;
        this.originService = originService;
        this.armorService = armorService;
        this.ringService = ringService;
    }

    // 入力されたステータスを返す
    @PostMapping("/api/status")
    public Map<String, Object> receiveStatus(
            @RequestBody Map<String, Integer> status) {

        return Map.of(
                "message", "Javaでステータスを受け取りました！",
                "status", status
        );
    }

    // 武器ごとの装備可能判定を返す

    @PostMapping("/api/weapons/check")
    public List<Map<String, Object>> checkWeapons(
            @RequestBody Map<String, Object> status
    ) {
        int strength = ((Number) status.getOrDefault("strength", 0)).intValue();
        int dexterity = ((Number) status.getOrDefault("dexterity", 0)).intValue();
        int intelligence = ((Number) status.getOrDefault("intelligence", 0)).intValue();
        int faith = ((Number) status.getOrDefault("faith", 0)).intValue();

        boolean twoHanded = Boolean.TRUE.equals(status.get("twoHanded"));

        int effectiveStrength = twoHanded
                ? (int) Math.floor(strength * 1.5)
                : strength;

        List<Map<String, Object>> results = new ArrayList<>();

        for (Weapon weapon : weaponService.getWeapons()) {
            boolean canEquip = weaponService.canEquip(
                    weapon,
                    strength,
                    dexterity,
                    intelligence,
                    faith,
                    twoHanded
            );

            List<String> missing = new ArrayList<>();

            if (effectiveStrength < weapon.getStrength()) {
                missing.add("筋力があと"
                        + (weapon.getStrength() - effectiveStrength)
                        + "必要");
            }
            if (dexterity < weapon.getDexterity()) {
                missing.add("技量があと"
                        + (weapon.getDexterity() - dexterity) + "必要");
            }
            if (intelligence < weapon.getIntelligence()) {
                missing.add("理力があと"
                        + (weapon.getIntelligence() - intelligence) + "必要");
            }
            if (faith < weapon.getFaith()) {
                missing.add("信仰があと"
                        + (weapon.getFaith() - faith) + "必要");
            }

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("name", weapon.getName());
            item.put("category", weapon.getCategory());
            item.put("weight", weapon.getWeight());
            item.put("strength", weapon.getStrength());
            item.put("dexterity", weapon.getDexterity());
            item.put("intelligence", weapon.getIntelligence());
            item.put("faith", weapon.getFaith());
            item.put("strengthScaling", weapon.getStrengthScaling());
            item.put("dexterityScaling", weapon.getDexterityScaling());
            item.put("intelligenceScaling", weapon.getIntelligenceScaling());
            item.put("faithScaling", weapon.getFaithScaling());
            item.put("canEquip", canEquip);
            item.put("missing", missing);

            results.add(item);
        }

        return results;
    }

    // 素性ごとの作成可能判定を返す
    @PostMapping("/api/origins/check")
    public List<Map<String, Object>> checkOrigins(
            @RequestBody Map<String, Integer> status
    ) {
        int targetLevel = status.getOrDefault("level", 1);

        int vigor = status.getOrDefault("vigor", 0);
        int attunement = status.getOrDefault("attunement", 0);
        int endurance = status.getOrDefault("endurance", 0);
        int vitality = status.getOrDefault("vitality", 0);
        int strength = status.getOrDefault("strength", 0);
        int dexterity = status.getOrDefault("dexterity", 0);
        int intelligence = status.getOrDefault("intelligence", 0);
        int faith = status.getOrDefault("faith", 0);
        int luck = status.getOrDefault("luck", 0);

        List<Map<String, Object>> results = new ArrayList<>();

        for (Origin origin : originService.getOrigins()) {
            List<String> exceeds = new ArrayList<>();

            if (vigor < origin.getVigor()) exceeds.add("生命力");
            if (attunement < origin.getAttunement()) exceeds.add("集中力");
            if (endurance < origin.getEndurance()) exceeds.add("持久力");
            if (vitality < origin.getVitality()) exceeds.add("体力");
            if (strength < origin.getStrength()) exceeds.add("筋力");
            if (dexterity < origin.getDexterity()) exceeds.add("技量");
            if (intelligence < origin.getIntelligence()) exceeds.add("理力");
            if (faith < origin.getFaith()) exceeds.add("信仰");
            if (luck < origin.getLuck()) exceeds.add("運");

            boolean compatible = exceeds.isEmpty();

            int requiredLevel = origin.getLevel()
                    + vigor - origin.getVigor()
                    + attunement - origin.getAttunement()
                    + endurance - origin.getEndurance()
                    + vitality - origin.getVitality()
                    + strength - origin.getStrength()
                    + dexterity - origin.getDexterity()
                    + intelligence - origin.getIntelligence()
                    + faith - origin.getFaith()
                    + luck - origin.getLuck();

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("name", origin.getName());
            item.put("compatible", compatible);
            item.put("exceeds", exceeds);
            item.put("requiredLevel", compatible ? requiredLevel : null);
            item.put(
                    "levelMatches",
                    compatible && requiredLevel == targetLevel
            );

            results.add(item);
        }

        results.sort((a, b) ->
                Boolean.compare(
                        (Boolean) b.get("compatible"),
                        (Boolean) a.get("compatible")
                )
        );

        return results;
    }

    // 防具一覧を返す
    @GetMapping("/api/armors")
    public List<Map<String, Object>> getArmors() {
        List<Map<String, Object>> results = new ArrayList<>();

        for (Armor armor : armorService.getAllArmors()) {
            Map<String, Object> item = new LinkedHashMap<>();

            item.put("name", armor.getName());
            item.put("category", armor.getCategory());
            item.put("weight", armor.getWeight());

            item.put("physical", armor.getPhysical());
            item.put("strike", armor.getStrike());
            item.put("slash", armor.getSlash());
            item.put("thrust", armor.getThrust());

            item.put("magic", armor.getMagic());
            item.put("fire", armor.getFire());
            item.put("lightning", armor.getLightning());
            item.put("dark", armor.getDark());

            item.put("blood", armor.getBlood());
            item.put("poison", armor.getPoison());
            item.put("frost", armor.getFrost());
            item.put("curse", armor.getCurse());

            item.put("poise", armor.getPoise());

            results.add(item);
        }

        return results;
    }

    // 指輪一覧を返す
    @GetMapping("/api/rings")
    public List<Ring> getRings() {
        return ringService.getAllRings();
    }
}