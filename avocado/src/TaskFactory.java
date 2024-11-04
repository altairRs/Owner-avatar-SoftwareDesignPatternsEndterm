public class TaskFactory {
    public static Task createTask(String name, String category, String deadline, int priority) {
        return new Task(name, category, deadline, priority);
    }
}
