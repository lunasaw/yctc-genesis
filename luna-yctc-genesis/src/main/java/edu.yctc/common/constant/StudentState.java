package edu.yctc.common.constant;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * 学生听课状态
 *
 * @author Tony
 */
public class StudentState {

    /** 认真听课 */
    public static final int LISTENING = 1;
    /** 玩手机 */
    public static final int PLAY_MOBILE_PHONE = 2;
    /** 发呆 */
    public static final int DAZE = 3;
    /** 睡觉 */
    public static final int SLEEP = 4;

    public static final List<Integer> ALL_STATE = ImmutableList.of(LISTENING, PLAY_MOBILE_PHONE, DAZE, SLEEP);

    public static boolean isLegal(int state) {
        for (Integer integer : ALL_STATE) {
            if (integer == state) {
                return true;
            }
        }
        return false;
    }
}
