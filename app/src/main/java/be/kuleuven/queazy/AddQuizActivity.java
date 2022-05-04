package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuizActivity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz);
    }

    public void onBtnAddQuestion_Clicked(View caller){
        Intent intent = new Intent(this, AddQuizActivity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}