import java.util.Comparator;

public class NameSorter implements TaskSorter {
    @Override
    public Comparator<Task> sortBy() {
        return Comparator.comparing(Task::getName);
    }
}