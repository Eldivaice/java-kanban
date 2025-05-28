package tasks;

public class Subtask extends Task{
    private Integer epicId;

    public Subtask(String title, String description, TaskStatus taskStatus, Integer epicId) {
        super(title, description, taskStatus);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }
}
