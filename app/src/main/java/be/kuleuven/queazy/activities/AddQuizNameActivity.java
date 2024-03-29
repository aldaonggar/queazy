package be.kuleuven.queazy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.queazy.R;
import be.kuleuven.queazy.interfaces.BackBtn;
import be.kuleuven.queazy.models.QuizAddition;

public class AddQuizNameActivity extends AppCompatActivity implements BackBtn {

    private EditText txtQuizName;
    private Spinner spDifficulty;
    private QuizAddition newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_name);
        txtQuizName = findViewById(R.id.txtQuizNameAdd);
        spDifficulty = findViewById(R.id.spDifficulty);
        newQuiz = new QuizAddition();
    }

    @Override
    public void onBackBtnClicked(View caller) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnAddQuizName_Clicked(View caller){
        String diff = spDifficulty.getSelectedItem().toString();
        String qn = txtQuizName.getText().toString();
        if (quizNameCheck(qn)) {
            findQuizID(qn, diff);
        } else {
            Toast.makeText(AddQuizNameActivity.this, "Insert name of your quiz", Toast.LENGTH_LONG).show();
        }
    }

    public void findQuizID(String qn, String diff) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/getLastQuizID";

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            newQuiz.setQuizid(o.getInt("id"));
                            newQuiz.setQuizName(qn);
                            newQuiz.setDifficulty(diff);
                            Intent intent = new Intent(this, AddQuestion1Activity.class);
                            intent.putExtra("newquiz", newQuiz);
                            intent.putExtra("questionNr", 1);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(AddQuizNameActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }



    public boolean quizNameCheck(String qn) {
        return !qn.equals("");
    }
}