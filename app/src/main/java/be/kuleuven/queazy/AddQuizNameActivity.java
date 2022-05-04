package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuizNameActivity extends AppCompatActivity {

    private Button btnAddQuestions;
    private Button btnBackToMenuPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz_name);
        btnAddQuestions = (Button) findViewById(R.id.btnAddQuestions);
        btnBackToMenuPage = (Button) findViewById(R.id.btnBackToMenuPage);
    }

    public void onBtnAddQuestions_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion1Activity.class);
        startActivity(intent);
    }

    public void onBtnBackToMenuPage_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}