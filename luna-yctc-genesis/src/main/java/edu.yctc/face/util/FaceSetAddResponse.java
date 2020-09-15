package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class FaceSetAddResponse extends FaceSetCreatResponse {

    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FaceSetAddResponse that = (FaceSetAddResponse)o;

        return taskId != null ? taskId.equals(that.taskId) : that.taskId == null;
    }

    @Override
    public int hashCode() {
        return taskId != null ? taskId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" + "\"taskId\":\'" + taskId + "\'" + '}';
    }
}
