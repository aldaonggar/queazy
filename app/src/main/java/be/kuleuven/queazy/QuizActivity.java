package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class QuizActivity extends AppCompatActivity {
    private int quizID;
    private int questionNr;
    private RequestQueue requestQueue;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private TextView txtQuestionDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
        btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);
        btnAnswer3 = (Button) findViewById(R.id.btnAnswer3);
        btnAnswer4 = (Button) findViewById(R.id.btnAnswer4);
        txtQuestionDB = (TextView) findViewById(R.id.txtQuestionDB);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            quizID = extras.getInt("quizID");
            questionNr = extras.getInt("questionNr");
        }

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/questions/" + quizID + "/" + questionNr + "/" + quizID + "/" + questionNr;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
            response -> {

                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject o = response.getJSONObject(i);
                        String question = o.getString("question");
                        String answer = o.getString("answer");
                        txtQuestionDB.setText(question);
                        BtnSetText(i, answer);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            error -> Toast.makeText(QuizActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(submitRequest);
    }

    public void BtnSetText(int i, String ans) {
        if (i == 0) {
            btnAnswer1.setText(ans);
            onBtnAnsClicked(btnAnswer1);
        }
        if (i == 1) {
            btnAnswer2.setText(ans);
            onBtnAnsClicked(btnAnswer2);
        }
        if (i == 2) {
            btnAnswer3.setText(ans);
            onBtnAnsClicked(btnAnswer3);
        }
        if (i == 3) {
            btnAnswer4.setText(ans);
            onBtnAnsClicked(btnAnswer4);
        }
    }

    public void onBtnAnsClicked(Button myButton) {
        myButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("quizID", quizID);
            intent.putExtra("questionNr", questionNr + 1);
            startActivity(intent);
        });
    }
}