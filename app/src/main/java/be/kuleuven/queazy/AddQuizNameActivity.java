package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import be.kuleuven.queazy.models.QuizAddition;

public class AddQuizNameActivity extends AppCompatActivity {

    private Button btnAddQuestions;
    private Button btnBackToMenuPage;
    private EditText txtQuizName;
    private Spinner spDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_name);
        btnAddQuestions = (Button) findViewById(R.id.btnAddQuestions);
        btnBackToMenuPage = (Button) findViewById(R.id.btnBackToMenuPage4);
        txtQuizName = (EditText) findViewById(R.id.txtQuizNameAdd);
        spDifficulty = (Spinner) findViewById(R.id.spDifficulty);
    }

    public void onBtnAddQuizName_Clicked(View caller){
        String diff = spDifficulty.getSelectedItem().toString();
        String qn = txtQuizName.getText().toString();
        if (quizNameCheck(qn)) {
            QuizAddition.setQuizName(qn);
            QuizAddition.setDifficulty(diff);
            Intent intent = new Intent(this, AddQuestion1Activity.class);
            startActivity(intent);
        } else {
            Toast.makeText(AddQuizNameActivity.this, "Insert name of your quiz", Toast.LENGTH_LONG).show();
        }
    }

    public void onBtnBackToMenuPage4_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public boolean quizNameCheck(String qn) {
        if (qn.equals(""))
            return false;
        return true;
    }

}