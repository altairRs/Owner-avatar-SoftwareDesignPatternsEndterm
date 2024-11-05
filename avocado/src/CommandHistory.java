import java.util.Stack;

public class CommandHistory {
    private Stack<String> history = new Stack<>();

    public void addCommand(String command) {
        history.push(command);
    }

    public String undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}
