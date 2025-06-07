package manager;

import tasks.Task;

import java.util.List;

public interface HistoryManager {

    void addTaskView (Task task);

    List<Task> getHistory();
}
