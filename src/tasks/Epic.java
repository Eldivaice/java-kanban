package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtasksForEpic;


    public Epic(String title, String description) {
        super(title, description, TaskStatus.NEW);
        this.subtasksForEpic = new ArrayList<>();
    }

    public ArrayList<Integer> getSubtasksForEpic() {
        return subtasksForEpic;
    }

}