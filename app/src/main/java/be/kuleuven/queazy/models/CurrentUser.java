package be.kuleuven.queazy.models;

import android.graphics.Bitmap;

public class CurrentUser {
    private static String currentUser;
    private static Bitmap currentAvatar;

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
