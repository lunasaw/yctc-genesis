package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Skinstatus {

    private float darkCircle;

    private float stain;

    private float acne;

    private float health;

    public float getDarkCircle() {
        return darkCircle;
    }

    public void setDarkCircle(float darkCircle) {
        this.darkCircle = darkCircle;
    }

    public float getStain() {
        return stain;
    }

    public void setStain(float stain) {
        this.stain = stain;
    }

    public float getAcne() {
        return acne;
    }

    public void setAcne(float acne) {
        this.acne = acne;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "{" + "\"darkCircle\":" + darkCircle + ", \"stain\":" + stain + ", \"acne\":" + acne + ", \"health\":"
            + health + '}';
    }
}
