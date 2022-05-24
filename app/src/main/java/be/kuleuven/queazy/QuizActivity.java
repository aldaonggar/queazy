package be.kuleuven.queazy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.queazy.models.CurrentQuiz;
import be.kuleuven.queazy.models.CurrentUser;

public class QuizActivity extends AppCompatActivity {
    private int quizID, questionNr, points;
    private RequestQueue requestQueue;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
    private TextView txtQuestionDB, txtTimer;
    private CountDownTimer timer;
    private String username;

    private int[] iscorrect;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView txtQuizOver;
    private Button btnPopupOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        username = CurrentUser.getCurrentUser();
        btnAnswer1 = findViewById(R.id.btnAnswer1);
        btnAnswer2 = findViewById(R.id.btnAnswer2);
        btnAnswer3 = findViewById(R.id.btnAnswer3);
        btnAnswer4 = findViewById(R.id.btnAnswer4);
        txtQuestionDB = findViewById(R.id.txtQuestionDB);
        txtTimer = findViewById(R.id.txtTimer);
        points = 100;


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            quizID = extras.getInt("quizID");
            questionNr = extras.getInt("questionNr");
        }

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/questions/" + quizID + "/" + questionNr + "/" + quizID + "/" + questionNr;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
            response -> {
                iscorrect = new int[5];
                for(int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject o = response.getJSONObject(i);
                        String question = o.getString("question");
                        String answer = o.getString("answer");
                        iscorrect[i] = o.getInt("iscorrect");
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


        timer = new CountDownTimer(25000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtTimer.setText("Time: " + millisUntilFinished/1000);
                points -= 2;
            }

            public void onFinish(){
                txtTimer.setText("TIME OVER");
                createNewContactDialog(33);
            }
        }.start();

    }


    public void BtnSetText(int i, String ans) {
        if (i == 0) {
            btnAnswer1.setText(ans);
            onBtnAnsClicked(btnAnswer1, i);
        }
        if (i == 1) {
            btnAnswer2.setText(ans);
            onBtnAnsClicked(btnAnswer2, i);
        }
        if (i == 2) {
            btnAnswer3.setText(ans);
            onBtnAnsClicked(btnAnswer3, i);
        }
        if (i == 3) {
            btnAnswer4.setText(ans);
            onBtnAnsClicked(btnAnswer4, i);
        }
    }

    public void onBtnAnsClicked(Button myButton, int i) {
        myButton.setOnClickListener((view) -> {
            if(iscorrect[i] == 1){
                CurrentQuiz.incrementCorrectAnswers();
                myButton.setBackgroundColor(0xFF2FFF00);
            }
            else if(iscorrect[i] == 0) {
                myButton.setBackgroundColor(0xFFFF0000);
            }
            createNewContactDialog(iscorrect[i]);
        });
    }

    public void createNewContactDialog(int correctness){

        timer.cancel();

        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popupquizover, null);
        txtQuizOver = contactPopupView.findViewById(R.id.txtQuizOver);
        btnPopupOk = contactPopupView.findViewById(R.id.btnPopupOk);


        if (correctness == 1) {
            txtQuizOver.setText("Correct answer" + "\n" + "Points earned: " + points);
            updateUsersPoints();
        } else if (correctness == 0) {
            points = 0;
            txtQuizOver.setText("Wrong answer" + "\n" + "Points earned: " + points);
        } else {
            points = 0;
            txtQuizOver.setText("Out of time" + "\n" + "Points earned: " + points);
        }
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();


        onBtnPopUpClicked(btnPopupOk);

    }

    public void updateUsersPoints(){
        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/pointsAddition/" + points + "/" + username;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {},
                error -> Toast.makeText(QuizActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(submitRequest);
    }

    public void onBtnPopUpClicked(Button btnOk) {

        Intent intent = new Intent(this, QuizActivity.class);
        Intent intent2 = new Intent(this, QuizListActivity.class);

        btnOk.setOnClickListener(view -> {
            if (questionNr < 5) {
                intent.putExtra("quizID", quizID);
                intent.putExtra("questionNr", questionNr + 1);
                startActivity(intent);
            } else {
                updateUsersAttendance();
                startActivity(intent2);
            }
        });
    }

    public void updateUsersAttendance() {
        requestQueue = Volley.newRequestQueue(this);

        int correctAns = CurrentQuiz.getCorrectAnswers();
        int qp = 0;
        if (correctAns == 5) {
            qp = 1;
            addPassedQuiz();
        }

        String requestURL = "https://studev.groept.be/api/a21pt216/updateQuizAttendance/" + qp + "/" + 1 + "/" + username;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {},
                error -> Toast.makeText(QuizActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(submitRequest);
    }

    public void addPassedQuiz() {
        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/addPassedQuiz/" + username + "/" + quizID;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {},
                error -> Toast.makeText(QuizActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(submitRequest);
    }
}