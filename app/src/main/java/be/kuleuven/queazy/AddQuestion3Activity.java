package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuestion3Activity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnBackToAddQuestion2Page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question3);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion3);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz3);
        btnBackToAddQuestion2Page = (Button) findViewById(R.id.btnBackToAddQuestion2Page);
    }

    public void onBtnAddQuestion3_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion4Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz3_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnBackToAddQuestion2Page_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion2Activity.class);
        startActivity(intent);
    }

}