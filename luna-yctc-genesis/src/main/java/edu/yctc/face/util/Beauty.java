package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Beauty {

    private float femaleScore;

    private float maleScore;

    public float getFemaleScore() {
        return femaleScore;
    }

    public void setFemaleScore(float femaleScore) {
        this.femaleScore = femaleScore;
    }

    public float getMaleScore() {
        return maleScore;
    }

    public void setMaleScore(float maleScore) {
        this.maleScore = maleScore;
    }

    @Override
    public String toString() {
        return "{" + "\"femaleScore\":" + femaleScore + ", \"maleScore\":" + maleScore + '}';
    }
}
