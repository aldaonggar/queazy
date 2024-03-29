package be.kuleuven.queazy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import be.kuleuven.queazy.R;
import be.kuleuven.queazy.interfaces.BackBtn;
import be.kuleuven.queazy.models.CurrentQuiz;
import be.kuleuven.queazy.models.CurrentUser;

public class QuizListActivity extends AppCompatActivity implements BackBtn {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);

        putQuizzes();
    }

    @Override
    public void onBackBtnClicked(View caller) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void putQuizzes() {
        LinearLayout myLayout = findViewById(R.id.llQuizList);

        String requestURL = "https://studev.groept.be/api/a21pt216/quizName";
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject o;
                        try {

                            o = response.getJSONObject(i);
                            String quizname = o.getString("quizname");
                            String difficulty = o.getString("difficulty");
                            int quizID = o.getInt("id");
                            Button myButton = new Button(this);

                            myButton.setLayoutParams(new LinearLayout.LayoutParams(
                                    1000,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            ));

                            myButton.setTextColor(0xFFFFFFFF);
                            myButton.setBackgroundColor(0xFFFFC107);
                            myButton.setText(quizname);
                            myButton.setX(40);
                            myLayout.addView(myButton);

                            myButton.setOnClickListener((view) -> {
                                Intent intent = new Intent(this, QuizActivity.class);
                                intent.putExtra("quizID", quizID);
                                intent.putExtra("questionNr", 1);
                                checkPassedQuery(quizID, intent);
                            });

                            TextView myText = new TextView(this);

                            myText.setLayoutParams(new LinearLayout.LayoutParams(
                                    1000,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            ));

                            myText.setTextColor(0xFFFFFFFF);
                            myText.setText("Difficulty: " + difficulty);
                            myText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            myText.setPadding(0,0,0,50);
                            myLayout.addView(myText);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },

                error -> Toast.makeText(QuizListActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()

        );
        requestQueue.add(queueRequest);

    }
    public void checkPassedQuery(int id, Intent intent) {
        String username = CurrentUser.getCurrentUser();

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/getPassedQuizzes/" + username;
        ArrayList<Integer> responseIDs = new ArrayList<>();

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            responseIDs.add(o.getInt("quizid"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    checkPassed(intent, responseIDs, id);
                },
                error -> Toast.makeText(QuizListActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }

    public void checkPassed(Intent intent, ArrayList<Integer> IDs, int id) {
        if (IDs.contains(id))
            Toast.makeText(QuizListActivity.this, "You've already done this quiz", Toast.LENGTH_LONG).show();
        else {
            new CurrentQuiz();
            startActivity(intent);
        }
    }
}