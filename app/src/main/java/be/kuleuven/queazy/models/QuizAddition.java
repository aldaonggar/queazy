package be.kuleuven.queazy.models;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import be.kuleuven.queazy.AddQuizNameActivity;
import be.kuleuven.queazy.RankingsActivity;

public class QuizAddition implements Serializable {

    private ArrayList<String> questions, ans, correctAns;
    private String quizName, difficulty;
    private int quizid;
    private RequestQueue requestQueue;

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

    public void addQuestion(String qs){ questions.add(qs); }

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


    public void addToDB(Context cntxt) {
        addQuizNameToDB(cntxt);
        addQuestionsToDB(cntxt);
        addAnswersToDB(cntxt);
    }

    public void addQuizNameToDB(Context cntxt) {
        requestQueue = Volley.newRequestQueue(cntxt);

        String requestURL = "https://studev.groept.be/api/a21pt216/addQuizName/" + this.getQuizid() + "/" + this.getQuizName() + "/" + this.getDifficulty();

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {},
                error -> Toast.makeText(cntxt, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }

    public void addQuestionsToDB(Context cntxt) {
        for (int i = 0; i < 5; i++) {
            requestQueue = Volley.newRequestQueue(cntxt);
            int questionID = i + 1;
            String requestURL = "https://studev.groept.be/api/a21pt216/addQuestionsToDB/" + this.getQuizid() + "/" + questionID + "/" + this.getQuestions().get(i);

            JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                    response -> {
                    },
                    error -> Toast.makeText(cntxt, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
            );
            requestQueue.add(queueRequest);
        }
    }

    public void addAnswersToDB(Context cntxt) {
        String ansID = "A";
        for (int i = 0; i < 20; i++) {
            int questionID = (i/4) + 1;

            if (i % 4 == 1)
                ansID = "B";
            else if (i % 4 == 2)
                ansID = "C";
            else if (i % 4 == 3)
                ansID = "D";


            requestQueue = Volley.newRequestQueue(cntxt);

            String requestURL = "https://studev.groept.be/api/a21pt216/addAnswersToDB/" + this.getQuizid() + "/" + questionID + "/" + ansID + "/" + this.getAnswers().get(i) + "/" + (this.getCorrectAns().get((i/4) + 1) == ansID);

            JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                    response -> {
                    },
                    error -> Toast.makeText(cntxt, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
            );
            requestQueue.add(queueRequest);
        }
    }
}
