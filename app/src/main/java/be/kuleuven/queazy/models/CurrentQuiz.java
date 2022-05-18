package be.kuleuven.queazy.models;

public class CurrentQuiz {

    private static int correctAnswers;

    public CurrentQuiz(){
        correctAnswers = 0;
    }

    public static void incrementCorrectAnswers(){
        correctAnswers += 1;
    }

    public static int getCorrectAnswers() {
        return correctAnswers;
    }
}
