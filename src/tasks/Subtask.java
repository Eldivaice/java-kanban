package tasks;

public class Subtask extends Task{
    private Integer epicId;


    public Subtask(String title, String description, TaskStatus taskStatus) {
        super(title, description, taskStatus);

    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }
}
