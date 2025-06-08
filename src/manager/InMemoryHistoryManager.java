package manager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> taskViewHistory = new ArrayList<>();


    @Override
    public void addTaskView(Task task) {
        if (taskViewHistory.size() >= 10) {
            taskViewHistory.removeFirst();
        }
        taskViewHistory.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(taskViewHistory);
    }
}
