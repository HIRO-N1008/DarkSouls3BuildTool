package org.example;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeaponService {

    private final List<Weapon> weapons;

    public WeaponService() {
        this.weapons = loadWeapons();
    }

    private List<Weapon> loadWeapons() {
        List<Weapon> loadedWeapons = new ArrayList<>();

        try (
                var input = new ClassPathResource("data/weapons.csv").getInputStream();
                var reader = new BufferedReader(
                        new InputStreamReader(input, StandardCharsets.UTF_8)
                )
        ) {
            // 1行目は見出しなので読み飛ばす
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] columns = line.split(",", -1);

                // CSVは10列必要
                if (columns.length != 11) {
                    throw new IllegalStateException(
                            "CSVの列数が正しくありません：" + line
                    );
                }

                String name = columns[0].trim();
                String category = columns[1].trim();
                double weight = Double.parseDouble(columns[2].trim());

                int strength = Integer.parseInt(columns[3].trim());
                int dexterity = Integer.parseInt(columns[4].trim());
                int intelligence = Integer.parseInt(columns[5].trim());
                int faith = Integer.parseInt(columns[6].trim());

                String strengthScaling = columns[7].trim();
                String dexterityScaling = columns[8].trim();
                String intelligenceScaling = columns[9].trim();
                String faithScaling = columns[10].trim();

                Weapon weapon = new Weapon(
                        name,
                        category,
                        weight,
                        strength,
                        dexterity,
                        intelligence,
                        faith,
                        strengthScaling,
                        dexterityScaling,
                        intelligenceScaling,
                        faithScaling
                );

                loadedWeapons.add(weapon);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "武器データのCSVを読み込めませんでした。",
                    e
            );
        }

        return List.copyOf(loadedWeapons);
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public boolean canEquip(
            Weapon weapon,
            int strength,
            int dexterity,
            int intelligence,
            int faith
    ) {
        return canEquip(
                weapon,
                strength,
                dexterity,
                intelligence,
                faith,
                false
        );
    }

    public boolean canEquip(
            Weapon weapon,
            int strength,
            int dexterity,
            int intelligence,
            int faith,
            boolean twoHanded
    ) {
        int effectiveStrength = twoHanded
                ? (int) Math.floor(strength * 1.5)
                : strength;

        return effectiveStrength >= weapon.getStrength()
                && dexterity >= weapon.getDexterity()
                && intelligence >= weapon.getIntelligence()
                && faith >= weapon.getFaith();
    }
}
