package be.kuleuven.queazy.models;

import java.io.Serializable;
import java.util.ArrayList;

public class QuizAddition implements Serializable {

    private ArrayList<String> questions, ans, correctAns;
    private String quizName, difficulty;
    private int quizid;

    public QuizAddition() {
        questions = new ArrayList<>();
        ans = new ArrayList<>();
        correctAns = new ArrayList<>();
        quizName = "";
        difficulty = "";
    }

    public void setQuizName(String quizname) {
        quizName = quizname;
    }

    public void setQuizid(int qid) {
        quizid = qid + 1;
    }

    public void setDifficulty(String diff) {
        difficulty = diff;
    }

    public void addQuestion(String qs){ questions.add(qs);}

    public void addCorrectAns(String corAns) { correctAns.add(corAns); }

    public void addAnswers(ArrayList<String> answers) {
        for (int i = 0 ; i < answers.size(); i++)
            ans.add(answers.get(i));
    }

    public int getQuizid(){
        return quizid;
    }

    public String getQuizName() {
        return quizName;
    }

    public ArrayList<String> getCorrectAns() {
        return correctAns;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<String> getAnswers() {
        return ans;
    }

}
