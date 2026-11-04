package org.example;

public class Weapon {
    private String name;
    private String category;
    private double weight;

    private int strength;
    private int dexterity;
    private int intelligence;
    private int faith;

    private String strengthScaling;
    private String dexterityScaling;
    private String intelligenceScaling;
    private String faithScaling;

    public Weapon(
            String name,
            String category,
            double weight,
            int strength,
            int dexterity,
            int intelligence,
            int faith,
            String strengthScaling,
            String dexterityScaling,
            String intelligenceScaling,
            String faithScaling
    ) {
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.faith = faith;
        this.strengthScaling = strengthScaling;
        this.dexterityScaling = dexterityScaling;
        this.intelligenceScaling = intelligenceScaling;
        this.faithScaling = faithScaling;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getWeight() {
        return weight;
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

    public String getStrengthScaling() {
        return strengthScaling;
    }

    public String getDexterityScaling() {
        return dexterityScaling;
    }

    public String getIntelligenceScaling() {
        return intelligenceScaling;
    }

    public String getFaithScaling() {
        return faithScaling;
    }
}