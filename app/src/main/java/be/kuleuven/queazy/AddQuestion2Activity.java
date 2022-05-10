package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuestion2Activity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnCancel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question2);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion2);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz2);
        btnCancel2 = (Button) findViewById(R.id.btnCancel2);
    }

    public void onBtnAddQuestion2_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion3Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz2_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnCancel2_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}