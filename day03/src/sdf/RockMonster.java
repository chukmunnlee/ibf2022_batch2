package sdf;

public class RockMonster extends Monster {
    public RockMonster() { }
    public RockMonster(int health) { 
        this.health = health;
    }

    @Override
    public void hit(int damage) {
        this.health -= damage * .5;
    }
    
}
