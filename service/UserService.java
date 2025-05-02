package service;

import model.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, User> users = new HashMap<>();
    
    static {
        // Hardcoded users for testing
        users.put("student1", new User("student1", "pass1"));
        users.put("student2", new User("student2", "pass2"));
        users.put("student3", new User("student3", "pass3"));
    }
    
    public static boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
}