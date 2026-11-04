
package org.example;

public class Origin {

    private final String name;
    private final int level;

    private final int vigor;
    private final int attunement;
    private final int endurance;
    private final int vitality;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private final int faith;
    private final int luck;

    public Origin(
            String name,
            int level,
            int vigor,
            int attunement,
            int endurance,
            int vitality,
            int strength,
            int dexterity,
            int intelligence,
            int faith,
            int luck
    ) {
        this.name = name;
        this.level = level;
        this.vigor = vigor;
        this.attunement = attunement;
        this.endurance = endurance;
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.faith = faith;
        this.luck = luck;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getVigor() {
        return vigor;
    }

    public int getAttunement() {
        return attunement;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getVitality() {
        return vitality;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getFaith() {
        return faith;
    }

    public int getLuck() {
        return luck;
    }
}
