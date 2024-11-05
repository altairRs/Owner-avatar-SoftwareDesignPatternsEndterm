import java.util.HashMap;
import java.util.Map;

public class UserManager {
    // Step 1: Create a private static instance of UserManager (the singleton instance).
    private static UserManager instance;

    // Step 2: Private constructor to prevent instantiation from outside the class.
    private UserManager() {
        users = new HashMap<>();
        loggedInUsers = new HashMap<>();
    }

    // Step 3: Provide a public static method to access the singleton instance.
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Storage for users (username and password)
    private Map<String, String> users;
    private Map<String, Boolean> loggedInUsers;

    // Register a user with a username and password
    public void registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("User already exists: " + username);
        } else {
            users.put(username, password);
            System.out.println("User registered: " + username);
        }
    }

    // Login a user
    public boolean loginUser(String username, String password) {
        // Check if the username exists and the password matches
        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUsers.put(username, true); // Mark user as logged in
            System.out.println("User logged in successfully: " + username);
            return true;
        } else {
            System.out.println("Invalid username or password for user: " + username);
            return false;
        }
    }

    // Logout a user
    public void logoutUser(String username) {
        if (loggedInUsers.containsKey(username) && loggedInUsers.get(username)) {
            loggedInUsers.put(username, false); // Mark user as logged out
            System.out.println("User logged out: " + username);
        } else {
            System.out.println("User is not logged in: " + username);
        }
    }

    // Check if a user is logged in
    public boolean isUserLoggedIn(String username) {
        return loggedInUsers.getOrDefault(username, false);
    }
}
