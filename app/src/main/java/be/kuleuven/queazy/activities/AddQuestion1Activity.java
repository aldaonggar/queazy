package be.kuleuven.queazy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import be.kuleuven.queazy.R;
import be.kuleuven.queazy.interfaces.AddQuestion;
import be.kuleuven.queazy.models.QuizAddition;

public class AddQuestion1Activity extends AppCompatActivity implements AddQuestion {

    private AlertDialog dialog;
    private EditText txtQuestion, txtA, txtB, txtC, txtD;
    private RadioGroup rgAns;
    private QuizAddition newQuiz;
    private int questionNr;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question1);
        Button btnAddQuestion = findViewById(R.id.btnAddQuestion1);
        Button btnAddQuiz = findViewById(R.id.btnAddQuiz1);
        txtQuestion = findViewById(R.id.txtWriteQuestion1);
        txtA = findViewById(R.id.txtA1);
        txtB = findViewById(R.id.txtB1);
        txtC = findViewById(R.id.txtC1);
        txtD = findViewById(R.id.txtD1);
        rgAns = findViewById(R.id.rgAns);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionNr = extras.getInt("questionNr");
            newQuiz = (QuizAddition) extras.getSerializable("newquiz");
        }

        if(questionNr == 5) {
            btnAddQuestion.setVisibility(View.INVISIBLE);
            btnAddQuestion.setClickable(false);
        }

        if(questionNr < 5) {
            btnAddQuiz.setVisibility(View.INVISIBLE);
            btnAddQuiz.setClickable(false);
        }

        //Toast.makeText(AddQuestion1Activity.this, "Question " + questionNr, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean txtfieldsCheck() {
        String qs = txtQuestion.getText().toString();
        String ansA = txtA.getText().toString();
        String ansB = txtB.getText().toString();
        String ansC = txtC.getText().toString();
        String ansD = txtD.getText().toString();
        return (!qs.equals("") && !ansA.equals("") && !ansB.equals("") && !ansC.equals("") && !ansD.equals("") && !getCorAns().equals("-1") && (!ansA.equals(ansB) && !ansA.equals(ansC) && !ansA.equals(ansD) && !ansB.equals(ansC) && !ansB.equals(ansD) && !ansC.equals(ansD)));
    }

    @Override
    public String getCorAns() {
        String checkedBtn = "-1";
        RadioButton selectedBtn;
        if (rgAns.getCheckedRadioButtonId() != -1) {
            selectedBtn = findViewById(rgAns.getCheckedRadioButtonId());
            checkedBtn = selectedBtn.getText().toString();
        }
        return checkedBtn;
    }

    @Override
    public void collectAnswers(ArrayList<String> answers) {
        answers.add(txtA.getText().toString());
        answers.add(txtB.getText().toString());
        answers.add(txtC.getText().toString());
        answers.add(txtD.getText().toString());
    }

    @Override
    public void nextActivity(View caller) {

        if (txtfieldsCheck()) {
            ArrayList<String> answers = new ArrayList<>();
            collectAnswers(answers);
            newQuiz.addAnswers(answers);
            newQuiz.addQuestion(txtQuestion.getText().toString());
            newQuiz.addCorrectAns(getCorAns());

            if (questionNr < 5) {
                questionNr++;
                Intent intent = new Intent(this, AddQuestion1Activity.class);
                intent.putExtra("questionNr", questionNr);
                intent.putExtra("newquiz", newQuiz);
                startActivity(intent);
            } else {
                addQuizToDB();
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }

        } else {
            Toast.makeText(AddQuestion1Activity.this, "Unfilled fields or repeating answers", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void addQuizToDB() {
        addQuizNameToDB();
        addQuestionsToDB();
        addAnswersToDB();
    }


    @Override
    public void onBtnCancel_Clicked(View caller){
        createNewContactDialog();
    }

    public void addQuizNameToDB() {
        requestQueue = Volley.newRequestQueue(AddQuestion1Activity.this);

        String requestURL = "https://studev.groept.be/api/a21pt216/addQuizName/" + newQuiz.getQuizid() + "/" + newQuiz.getQuizName() + "/" + newQuiz.getDifficulty();

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {},
                error -> Toast.makeText(AddQuestion1Activity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }

    public void addQuestionsToDB() {
        for (int i = 0; i < newQuiz.getQuestions().size(); i++) {
            requestQueue = Volley.newRequestQueue(AddQuestion1Activity.this);
            int questionID = i + 1;
            String requestURL = "https://studev.groept.be/api/a21pt216/addQuestionsToDB/" + newQuiz.getQuizid() + "/" + questionID + "/" + newQuiz.getQuestions().get(i);

            JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                    response -> {},
                    error -> Toast.makeText(AddQuestion1Activity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
            );
            requestQueue.add(queueRequest);
        }
    }
    public void addAnswersToDB() {
        String ansID = "A";
        for (int i = 0; i < newQuiz.getAnswers().size(); i++) {
            int questionID = (i/4) + 1;
            int correctness = 0;

            if (i % 4 == 1)
                ansID = "B";
            else if (i % 4 == 2)
                ansID = "C";
            else if (i % 4 == 3)
                ansID = "D";

            if (newQuiz.getCorrectAns().get(i / 4).equals(ansID))
                correctness = 1;

            requestQueue = Volley.newRequestQueue(AddQuestion1Activity.this);

            String requestURL = "https://studev.groept.be/api/a21pt216/addAnswersToDB/" + newQuiz.getQuizid() + "/" + questionID + "/" + ansID + "/" + newQuiz.getAnswers().get(i) + "/" + correctness;

            JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                    response -> {},
                    error -> Toast.makeText(AddQuestion1Activity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
            );
            requestQueue.add(queueRequest);
        }
    }

    public void createNewContactDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        Button btnPopupCancel = contactPopupView.findViewById(R.id.btnPopupCancel);
        Button btnPopupBackToMenu = contactPopupView.findViewById(R.id.btnPopupBackToMenu);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btnPopupCancel.setOnClickListener(view -> dialog.dismiss());


        btnPopupBackToMenu.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MenuActivity.class)));

    }

}