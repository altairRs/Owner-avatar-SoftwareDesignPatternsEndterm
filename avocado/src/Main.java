import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to Avocado Task Manager!");

        do {
            System.out.println("Commands: add, list, exit");
            System.out.print("Enter command: ");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter deadline: ");
                    String deadline = scanner.nextLine();
                    System.out.print("Enter priority (1-5): ");
                    int priority = Integer.parseInt(scanner.nextLine());

                    Task task = TaskFactory.createTask(name, category, deadline, priority);
                    taskManager.addTask(task);
                    System.out.println("Task added.");
                    break;

                case "list":
                    taskManager.listTasks();
                    break;

                case "exit":
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }
        } while (!command.equals("exit"));

        scanner.close();
    }
}
