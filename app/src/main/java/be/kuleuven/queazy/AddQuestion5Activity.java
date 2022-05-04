package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddQuestion5Activity extends AppCompatActivity {

    private Button btnAddQuiz;
    private Button btnBackToAddQuestion4Page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question5);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz5);
        btnBackToAddQuestion4Page = (Button) findViewById(R.id.btnBackToAddQuestion4Page);
    }

    public void onBtnAddQuiz5_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnBackToAddQuestion4Page_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion4Activity.class);
        startActivity(intent);
    }

}