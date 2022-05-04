package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class AddQuestion1Activity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnBackToAddQuizNamePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question1);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion1);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz1);
        btnBackToAddQuizNamePage = (Button) findViewById(R.id.btnBackToAddQuizNamePage);
    }

    public void onBtnAddQuestion1_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion2Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz1_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnBackToAddQuizNamePage_Clicked(View caller){
        Intent intent = new Intent(this, AddQuizNameActivity.class);
        startActivity(intent);
    }

}