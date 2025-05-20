package model;

import java.time.LocalDate;

public class SessionManager {
    private static User currentUser;
    private static final LocalDate today = LocalDate.now();

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static LocalDate getToday() {
        return today;
    }
}
