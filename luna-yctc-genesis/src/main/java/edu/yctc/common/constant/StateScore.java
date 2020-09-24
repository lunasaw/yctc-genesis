package edu.yctc.common.constant;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * 学生上课状态得分
 * 
 * @author 15272
 */
public class StateScore {

    /** 认真听课 */
    public static final int LISTENING_SCORE = 10;
    /** 玩手机 */
    public static final int PLAY_MOBILE_PHONE_SCORE = 6;
    /** 发呆 */
    public static final int DAZE_SCORE = 8;
    /** 睡觉 */
    public static final int SLEEP_SCORE = 7;

    public static final List<Integer> ALL_STATE_SCORE = ImmutableList.of(LISTENING_SCORE,
        PLAY_MOBILE_PHONE_SCORE, DAZE_SCORE,
        SLEEP_SCORE);

    public static boolean isLegal(int stateScore) {
        for (Integer integer : ALL_STATE_SCORE) {
            if (integer == stateScore) {
                return true;
            }
        }
        return false;
    }
}
