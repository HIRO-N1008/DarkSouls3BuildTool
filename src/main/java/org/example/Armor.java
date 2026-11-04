package org.example;

public class Armor {

    private String name;
    private String category;
    private double weight;

    private double physical;
    private double strike;
    private double slash;
    private double thrust;

    private double magic;
    private double fire;
    private double lightning;
    private double dark;

    private double blood;
    private double poison;
    private double frost;
    private double curse;

    private double poise;

    public Armor(
            String name,
            String category,
            double weight,
            double physical,
            double strike,
            double slash,
            double thrust,
            double magic,
            double fire,
            double lightning,
            double dark,
            double blood,
            double poison,
            double frost,
            double curse,
            double poise
    ) {
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.physical = physical;
        this.strike = strike;
        this.slash = slash;
        this.thrust = thrust;
        this.magic = magic;
        this.fire = fire;
        this.lightning = lightning;
        this.dark = dark;
        this.blood = blood;
        this.poison = poison;
        this.frost = frost;
        this.curse = curse;
        this.poise = poise;
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

    public double getPhysical() {
        return physical;
    }

    public double getStrike() {
        return strike;
    }

    public double getSlash() {
        return slash;
    }

    public double getThrust() {
        return thrust;
    }

    public double getMagic() {
        return magic;
    }

    public double getFire() {
        return fire;
    }

    public double getLightning() {
        return lightning;
    }

    public double getDark() {
        return dark;
    }

    public double getBlood() {
        return blood;
    }

    public double getPoison() {
        return poison;
    }

    public double getFrost() {
        return frost;
    }

    public double getCurse() {
        return curse;
    }

    public double getPoise() {
        return poise;
    }
}
