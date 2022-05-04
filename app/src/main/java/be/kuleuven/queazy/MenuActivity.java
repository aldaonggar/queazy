package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button btnLogOut;
    private Button btnAddQuizSuggestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnAddQuizSuggestion = (Button) findViewById(R.id.btnAddQuizSuggestion);
    }

    public void onBtnLogOut_Clicked(View caller){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onBtnAddQuizSuggestion_Clicked(View caller){
        Intent intent = new Intent(this, AddQuizNameActivity.class);
        startActivity(intent);
    }
}