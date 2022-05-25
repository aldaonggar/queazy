package be.kuleuven.queazy.models;

public class CurrentUser {
    private static String currentUser;

    public CurrentUser(){
        currentUser = null;
    }

    public static void setCurrentUser(String user) {
        currentUser = user;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void logOut(){currentUser = null;}
}
