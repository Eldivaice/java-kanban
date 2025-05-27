package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Integer> subtasksForEpic;


    public Epic(String title, String description, ArrayList<Integer> subtasksForEpic) {
        super(title, description, TaskStatus.NEW);
        this.subtasksForEpic = subtasksForEpic;
    }

    public ArrayList<Integer> getSubtasksForEpic() {
        return subtasksForEpic;
    }

}