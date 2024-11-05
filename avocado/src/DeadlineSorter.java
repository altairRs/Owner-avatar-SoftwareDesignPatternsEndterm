import java.util.Comparator;

public class DeadlineSorter implements TaskSorter {
    @Override
    public Comparator<Task> sortBy() {
        return Comparator.comparing(Task::getDeadline);
    }
}
