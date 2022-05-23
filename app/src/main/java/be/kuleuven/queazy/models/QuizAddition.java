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

    public static void addQuestion(String qs){ q.add(qs); }

    public static void addCorrectAns(String corAns) { correctAns.add(corAns); }

    public static void addAnswers(ArrayList<String> answers) {
        for (int i = 0 ; i < answers.size(); i++)
            ans.add(answers.get(i));
    }
}
