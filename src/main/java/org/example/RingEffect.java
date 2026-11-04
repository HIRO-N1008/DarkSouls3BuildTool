package org.example;

public class RingEffect {

    private String effectType;
    private double effectValue;

    public RingEffect(
            String effectType,
            double effectValue
    ) {
        this.effectType = effectType;
        this.effectValue = effectValue;
    }

    public String getEffectType() {
        return effectType;
    }

    public double getEffectValue() {
        return effectValue;
    }
}
