package be.kuleuven.queazy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import be.kuleuven.queazy.interfaces.AddQuestion;
import be.kuleuven.queazy.models.QuizAddition;

public class AddQuestion1Activity extends AppCompatActivity implements AddQuestion {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnCancel;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView txtAreYouSure;
    private EditText txtQuestion, txtA, txtB, txtC, txtD;
    private RadioGroup rgAns;
    private Button btnPopupBackToMenu, btnPopupCancel;
    private QuizAddition newQuiz;
    private int questionNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question1);
        btnAddQuestion = findViewById(R.id.btnAddQuestion1);
        btnAddQuiz = findViewById(R.id.btnAddQuiz1);
        btnCancel = findViewById(R.id.btnCancel);
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

        Toast.makeText(AddQuestion1Activity.this, "Question " + questionNr, Toast.LENGTH_LONG).show();
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
        newQuiz.addToDB( AddQuestion1Activity.this);
    }

    @Override
    public void onBtnCancel_Clicked(View caller){
        createNewContactDialog();
    }

    public void createNewContactDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        txtAreYouSure = contactPopupView.findViewById(R.id.txtAreYouSure);
        btnPopupCancel = contactPopupView.findViewById(R.id.btnPopupCancel);
        btnPopupBackToMenu = contactPopupView.findViewById(R.id.btnPopupBackToMenu);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btnPopupCancel.setOnClickListener(view -> dialog.dismiss());


        btnPopupBackToMenu.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MenuActivity.class)));

    }

}