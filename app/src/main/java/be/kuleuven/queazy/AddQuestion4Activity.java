package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuestion4Activity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnBackToAddQuestion3Page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question4);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion4);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz4);
        btnBackToAddQuestion3Page = (Button) findViewById(R.id.btnBackToAddQuestion3Page);
    }

    public void onBtnAddQuestion4_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion5Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz4_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnBackToAddQuestion3Page_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion3Activity.class);
        startActivity(intent);
    }

}