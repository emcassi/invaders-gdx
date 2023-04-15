package org.alexwayne.invaders.drops;

public class DropChance {
    DropTypes drop;
    float hitRate;

    public DropChance(DropTypes drop, float hitRate){
        this.drop = drop;
        this.hitRate = hitRate;
    }

    public DropTypes getType(){
        return drop;
    }

    public float getHitRate(){
        return hitRate;
    }

}
