import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize with Singleton instances
        TaskManager taskManager = new TaskManager();
        UserManager userManager = UserManager.getInstance(); // Using Singleton
        CommandHistory commandHistory = new CommandHistory();
        Scanner scanner = new Scanner(System.in);
        String command;
        User loggedInUser = null;

        // Load existing tasks at startup
        taskManager.getTasks().addAll(TaskPersistence.loadTasks());

        System.out.println("Welcome to Avocado Task Manager!");

        // User registration and login
        System.out.print("Register username: ");
        String username = scanner.nextLine();
        System.out.print("Register password: ");
        String password = scanner.nextLine();
        userManager.registerUser(username, password);

        System.out.print("Login username: ");
        username = scanner.nextLine();
        System.out.print("Login password: ");
        password = scanner.nextLine();
        boolean loginSuccessful = userManager.loginUser(username, password);

        if (loginSuccessful) {
            loggedInUser = new User(username, password); // Pass both username and password
            System.out.println("Login successful!");

            do {
                System.out.println("Commands: add, list, save, load, sort, undo, exit");
                System.out.print("Enter command: ");
                command = scanner.nextLine();
                commandHistory.addCommand(command);

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

                    case "save":
                        TaskPersistence.saveTasks(taskManager.getTasks());
                        System.out.println("Tasks saved.");
                        break;

                    case "load":
                        taskManager.getTasks().clear(); // Clear current tasks
                        taskManager.getTasks().addAll(TaskPersistence.loadTasks());
                        System.out.println("Tasks loaded.");
                        break;

                    case "sort":
                        System.out.print("Sort by (name, deadline): ");
                        String sortBy = scanner.nextLine();
                        TaskSorter sorter = null;
                        if (sortBy.equals("name")) {
                            sorter = new NameSorter();
                        } else if (sortBy.equals("deadline")) {
                            sorter = new DeadlineSorter();
                        }

                        if (sorter != null) {
                            Collections.sort(taskManager.getTasks(), sorter.sortBy());
                            System.out.println("Tasks sorted.");
                        } else {
                            System.out.println("Invalid sort option.");
                        }
                        break;

                    case "undo":
                        String lastCommand = commandHistory.undo();
                        if (lastCommand != null) {
                            System.out.println("Undid command: " + lastCommand);
                        } else {
                            System.out.println("No command to undo.");
                        }
                        break;

                    case "exit":
                        System.out.println("Exiting application...");
                        break;

                    default:
                        System.out.println("Unknown command. Please try again.");
                        break;
                }
            } while (!command.equals("exit"));
        } else {
            System.out.println("Login failed. Exiting application...");
        }

        scanner.close();
    }
}
