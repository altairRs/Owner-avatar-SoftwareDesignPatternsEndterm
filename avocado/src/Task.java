public class Task {
    private String name;
    private String category;
    private String deadline;
    private int priority;

    public Task(String name, String category, String deadline, int priority) {
        this.name = name;
        this.category = category;
        this.deadline = deadline;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("Task: %s | Category: %s | Deadline: %s | Priority: %d",
                name, category, deadline, priority);
    }
}
