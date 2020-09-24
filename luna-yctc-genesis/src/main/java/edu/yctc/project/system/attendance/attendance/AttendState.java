package edu.yctc.project.system.attendance.attendance;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * 出勤状态枚举
 *
 * @author Tony
 */
public class AttendState {

    /** 正常出勤（从头到尾都在） */
    public static final int ATTENDANCE = 1;
    /** 缺席（从头到尾都不在） */
    public static final int ABSENT = 2;
    /** 迟到（仅一开始不在） */
    public static final int LATE = 3;
    /** 早退（仅后来不在） */
    public static final int LEAVE_EARLY = 4;
    /** 系统未开始签到 */
    public static final int NOT_START_SIGN_IN = 5;

    public static final List<Integer> ALL_ATTEND_STATE = ImmutableList.of(ATTENDANCE, ABSENT, LATE, LEAVE_EARLY,
        NOT_START_SIGN_IN);

    public static boolean isLegal(int attendState) {
        for (Integer integer : ALL_ATTEND_STATE) {
            if (integer == attendState) {
                return true;
            }
        }
        return false;
    }
}
