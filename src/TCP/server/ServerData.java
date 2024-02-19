package TCP.server;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.function.Predicate;

@Getter
@NoArgsConstructor
public class ServerData {
    private final ArrayList<String> usersList = new ArrayList<>();
    private final ArrayList<String> messages = new ArrayList<>();

    public synchronized void addToUsersList(String username) {
        usersList.add(username);
    }

    public synchronized void removeFromUsersList(String username) {
        usersList.removeIf(Predicate.isEqual(username));
    }

    public synchronized void addToMessages(String message) {
        messages.add(message);
    }

}
