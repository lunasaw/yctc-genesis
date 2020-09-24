package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Landmark {

    /** 头部 */
    private Point head;

    /** Object 脖子 */
    private Point neck;

    /** Object 左肩 */
    private Point leftShoulder;

    /** Object 左肘 */
    private Point leftElbow;

    /** Object 左手 */
    private Point leftHand;

    /** Object 右肩 */
    private Point rightShoulder;

    /** Object 右肘 */
    private Point rightElbow;

    /** Object 右手 */
    private Point rightHand;

    /** Object 左臀 */
    private Point leftButtocks;

    /** Object 左膝 */
    private Point leftKnee;

    /** Object 左脚 */
    private Point leftFoot;

    /** Object 右臀 */
    private Point rightButtocks;

    /** Object 右膝 */
    private Point rightKnee;

    /** Object 右脚 */
    private Point rightFoot;

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public Point getNeck() {
        return neck;
    }

    public void setNeck(Point neck) {
        this.neck = neck;
    }

    public Point getLeftShoulder() {
        return leftShoulder;
    }

    public void setLeftShoulder(Point leftShoulder) {
        this.leftShoulder = leftShoulder;
    }

    public Point getLeftElbow() {
        return leftElbow;
    }

    public void setLeftElbow(Point leftElbow) {
        this.leftElbow = leftElbow;
    }

    public Point getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Point leftHand) {
        this.leftHand = leftHand;
    }

    public Point getRightShoulder() {
        return rightShoulder;
    }

    public void setRightShoulder(Point rightShoulder) {
        this.rightShoulder = rightShoulder;
    }

    public Point getRightElbow() {
        return rightElbow;
    }

    public void setRightElbow(Point rightElbow) {
        this.rightElbow = rightElbow;
    }

    public Point getRightHand() {
        return rightHand;
    }

    public void setRightHand(Point rightHand) {
        this.rightHand = rightHand;
    }

    public Point getLeftButtocks() {
        return leftButtocks;
    }

    public void setLeftButtocks(Point leftButtocks) {
        this.leftButtocks = leftButtocks;
    }

    public Point getLeftKnee() {
        return leftKnee;
    }

    public void setLeftKnee(Point leftKnee) {
        this.leftKnee = leftKnee;
    }

    public Point getLeftFoot() {
        return leftFoot;
    }

    public void setLeftFoot(Point leftFoot) {
        this.leftFoot = leftFoot;
    }

    public Point getRightButtocks() {
        return rightButtocks;
    }

    public void setRightButtocks(Point rightButtocks) {
        this.rightButtocks = rightButtocks;
    }

    public Point getRightKnee() {
        return rightKnee;
    }

    public void setRightKnee(Point rightKnee) {
        this.rightKnee = rightKnee;
    }

    public Point getRightFoot() {
        return rightFoot;
    }

    public void setRightFoot(Point rightFoot) {
        this.rightFoot = rightFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Landmark landmark = (Landmark)o;

        if (head != null ? !head.equals(landmark.head) : landmark.head != null) {
            return false;
        }
        if (neck != null ? !neck.equals(landmark.neck) : landmark.neck != null) {
            return false;
        }
        if (leftShoulder != null ? !leftShoulder.equals(landmark.leftShoulder) : landmark.leftShoulder != null) {
            return false;
        }
        if (leftElbow != null ? !leftElbow.equals(landmark.leftElbow) : landmark.leftElbow != null) {
            return false;
        }
        if (leftHand != null ? !leftHand.equals(landmark.leftHand) : landmark.leftHand != null) {
            return false;
        }
        if (rightShoulder != null ? !rightShoulder.equals(landmark.rightShoulder) : landmark.rightShoulder != null) {
            return false;
        }
        if (rightElbow != null ? !rightElbow.equals(landmark.rightElbow) : landmark.rightElbow != null) {
            return false;
        }
        if (rightHand != null ? !rightHand.equals(landmark.rightHand) : landmark.rightHand != null) {
            return false;
        }
        if (leftButtocks != null ? !leftButtocks.equals(landmark.leftButtocks) : landmark.leftButtocks != null) {
            return false;
        }
        if (leftKnee != null ? !leftKnee.equals(landmark.leftKnee) : landmark.leftKnee != null) {
            return false;
        }
        if (leftFoot != null ? !leftFoot.equals(landmark.leftFoot) : landmark.leftFoot != null) {
            return false;
        }
        if (rightButtocks != null ? !rightButtocks.equals(landmark.rightButtocks) : landmark.rightButtocks != null) {
            return false;
        }
        if (rightKnee != null ? !rightKnee.equals(landmark.rightKnee) : landmark.rightKnee != null) {
            return false;
        }
        return rightFoot != null ? rightFoot.equals(landmark.rightFoot) : landmark.rightFoot == null;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (neck != null ? neck.hashCode() : 0);
        result = 31 * result + (leftShoulder != null ? leftShoulder.hashCode() : 0);
        result = 31 * result + (leftElbow != null ? leftElbow.hashCode() : 0);
        result = 31 * result + (leftHand != null ? leftHand.hashCode() : 0);
        result = 31 * result + (rightShoulder != null ? rightShoulder.hashCode() : 0);
        result = 31 * result + (rightElbow != null ? rightElbow.hashCode() : 0);
        result = 31 * result + (rightHand != null ? rightHand.hashCode() : 0);
        result = 31 * result + (leftButtocks != null ? leftButtocks.hashCode() : 0);
        result = 31 * result + (leftKnee != null ? leftKnee.hashCode() : 0);
        result = 31 * result + (leftFoot != null ? leftFoot.hashCode() : 0);
        result = 31 * result + (rightButtocks != null ? rightButtocks.hashCode() : 0);
        result = 31 * result + (rightKnee != null ? rightKnee.hashCode() : 0);
        result = 31 * result + (rightFoot != null ? rightFoot.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + "\"head\":" + head + ", \"neck\":" + neck + ", \"leftShoulder\":" + leftShoulder
            + ", \"leftElbow\":" + leftElbow + ", \"leftHand\":" + leftHand + ", \"rightShoulder\":" + rightShoulder
            + ", \"rightElbow\":" + rightElbow + ", \"rightHand\":" + rightHand + ", \"leftButtocks\":"
            + leftButtocks + ", \"leftKnee\":" + leftKnee + ", \"leftFoot\":" + leftFoot + ", \"rightButtocks\":"
            + rightButtocks + ", \"rightKnee\":" + rightKnee + ", \"rightFoot\":" + rightFoot + '}';
    }
}
