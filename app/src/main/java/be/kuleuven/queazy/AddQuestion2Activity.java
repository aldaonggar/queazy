package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuestion2Activity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnBackToAddQuestion1Page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question2);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion2);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz2);
        btnBackToAddQuestion1Page = (Button) findViewById(R.id.btnBackToAddQuestion1Page);
    }

    public void onBtnAddQuestion2_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion3Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz2_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnBackToAddQuestion1Page_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion1Activity.class);
        startActivity(intent);
    }
}