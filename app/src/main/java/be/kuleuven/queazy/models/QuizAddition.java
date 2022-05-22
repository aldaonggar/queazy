package be.kuleuven.queazy.models;

import java.util.ArrayList;

public class QuizAddition {
    private static ArrayList<String> q, ans, correctAns;
    private static String quizName, difficulty;
    private static int quizid, questionid, answerid;
    public QuizAddition() {
        q = new ArrayList<>();
        ans = new ArrayList<>();
        correctAns = new ArrayList<>();
        quizName = "";
        difficulty = "";
    }

    public static void setQuizName(String quizname) {
        quizName = quizname;
    }

    public static void setDifficulty(String diff) {
        difficulty = diff;
    }
}
