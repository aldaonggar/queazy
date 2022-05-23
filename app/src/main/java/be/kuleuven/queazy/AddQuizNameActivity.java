package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.queazy.models.QuizAddition;

public class AddQuizNameActivity extends AppCompatActivity {

    private Button btnAddQuestions;
    private Button btnBackToMenuPage;
    private EditText txtQuizName;
    private Spinner spDifficulty;
    private RequestQueue requestQueue;
    private QuizAddition newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_name);
        btnAddQuestions = findViewById(R.id.btnAddQuestions);
        btnBackToMenuPage = findViewById(R.id.btnBackToMenuPage4);
        txtQuizName = findViewById(R.id.txtQuizNameAdd);
        spDifficulty = findViewById(R.id.spDifficulty);
        newQuiz = new QuizAddition();
    }

    public void onBtnAddQuizName_Clicked(View caller){
        String diff = spDifficulty.getSelectedItem().toString();
        String qn = txtQuizName.getText().toString();
        if (quizNameCheck(qn)) {
            findQuizID();
            newQuiz.setQuizName(qn);
            newQuiz.setDifficulty(diff);
            Intent intent = new Intent(this, AddQuestion1Activity.class);
            intent.putExtra("newquiz", newQuiz);
            intent.putExtra("questionNr", 1);
            startActivity(intent);
        } else {
            Toast.makeText(AddQuizNameActivity.this, "Insert name of your quiz", Toast.LENGTH_LONG).show();
        }
    }

    public void findQuizID() {
        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/getLastQuizID";

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            newQuiz.setQuizid(o.getInt("id"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(AddQuizNameActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }
    public void onBtnBackToMenuPage4_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public boolean quizNameCheck(String qn) {
        return !qn.equals("");
    }

}