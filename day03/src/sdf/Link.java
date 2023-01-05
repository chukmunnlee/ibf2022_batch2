package sdf;

public class Link {

    private int hitPoint = 4;

    public int getHitPoint() { return hitPoint; }
    public void setHitPoint(int hitPoint) { this.hitPoint = hitPoint; }

    public void attack(Damagable damagable) {
        damagable.hit(hitPoint);
    }
    
}
