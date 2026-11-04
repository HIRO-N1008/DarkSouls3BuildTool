package org.example;

import java.util.List;

public class Ring {

    private String name;
    private double weight;
    private List<RingEffect> effects;

    public Ring(
            String name,
            double weight,
            List<RingEffect> effects
    ) {
        this.name = name;
        this.weight = weight;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public List<RingEffect> getEffects() {
        return effects;
    }
}
