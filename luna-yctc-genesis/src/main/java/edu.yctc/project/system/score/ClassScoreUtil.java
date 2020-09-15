package edu.yctc.project.system.score;

import java.util.List;

import edu.yctc.common.constant.StateScore;
import edu.yctc.common.constant.StudentState;
import edu.yctc.project.system.courseState.domain.StudentCoursestate;


public class ClassScoreUtil {

    public static Double calculateScoreByState(List<StudentCoursestate> list) {

        double score = 0;

        // 参数检验
        for (StudentCoursestate stateDO : list) {
            if (StudentState.isLegal(Integer.parseInt(stateDO.getState().toString())) == false) {
                return null;
            }
        }
        for (StudentCoursestate stateDO : list) {
            score += getScoreByState(Integer.parseInt(stateDO.getState().toString()));
        }
        return  score;
    }

    public static double getScoreByState(int state) {
        switch (state) {
            case StudentState.LISTENING:
                return StateScore.LISTENING_SCORE;
            case StudentState.DAZE:
                return StateScore.DAZE_SCORE;
            case StudentState.PLAY_MOBILE_PHONE:
                return StateScore.PLAY_MOBILE_PHONE_SCORE;
            case StudentState.SLEEP:
                return StateScore.SLEEP_SCORE;
            default:
                return 0;
        }
    }
}
