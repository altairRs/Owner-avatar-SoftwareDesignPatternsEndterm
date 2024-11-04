import java.util.Comparator;

public class PrioritySorter implements TaskSorter {
    @Override
    public Comparator<Task> sortBy() {
        return Comparator.comparingInt(Task::getPriority);
    }
}
