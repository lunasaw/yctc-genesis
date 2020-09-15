package edu.yctc.project.system.attendance.attendance;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * 出勤状态名字枚举
 *
 * @author wlt
 */
public class AttendStateName {

    /** 正常出勤（从头到尾都在） */
    public static final String ATTENDANCE_NAME = "正常出勤";
    /** 缺席（从头到尾都不在） */
    public static final String ABSENT_NAME = "缺席";
    /** 迟到（仅一开始不在） */
    public static final String LATE_NAME = "迟到";
    /** 早退（仅后来不在） */
    public static final String LEAVE_EARLY_NAME = "早退";
    /** 系统未开始签到 */
    public static final String NOT_START_SIGN_IN_NAME = "系统未开始签到";
    /** 缺少数据 */
    public static final String MISSING_DATA_NAME = "缺少数据";

    public static final List<String> ALL_ATTEND_STATE_NAME = ImmutableList.of(ATTENDANCE_NAME, ABSENT_NAME, LATE_NAME,
        LEAVE_EARLY_NAME, NOT_START_SIGN_IN_NAME,
        MISSING_DATA_NAME);

    public static boolean isLegal(String attendState) {
        for (String string : ALL_ATTEND_STATE_NAME) {
            if (string == attendState) {
                return true;
            }
        }
        return false;
    }
}
