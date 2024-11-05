import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskPersistence {
    private static final String FILE_NAME = "tasks.txt";

    public static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(String.format("%s,%s,%s,%d%n",
                        task.getName(),
                        task.getCategory(),
                        task.getDeadline(),
                        task.getPriority()));
            }
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Task task = new Task(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                tasks.add(task);
            }
            System.out.println("Tasks loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
