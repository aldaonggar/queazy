package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import be.kuleuven.queazy.models.CurrentUser;

public class MenuActivity extends AppCompatActivity {
    private Button btnLogOut;
    private Button btnAddQuizSuggestion;
    private ImageButton ibProfile;
    private Button btnQuizzes;
    private Button btnRankings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnAddQuizSuggestion = (Button) findViewById(R.id.btnAddQuizSuggestion);
        ibProfile = (ImageButton) findViewById(R.id.ibProfile);
        btnQuizzes = (Button) findViewById(R.id.btnQuizzes);
        btnRankings = (Button) findViewById(R.id.btnRankings);
    }

    public void onBtnQuizzes_Clicked(View caller){
        Intent intent = new Intent(this, QuizListActivity.class);
        startActivity(intent);
    }

    public void onBtnRankings_Clicked(View caller){
        Intent intent = new Intent(this, RankingsActivity.class);
        startActivity(intent);
    }

    public void onBtnAddQuizSuggestion_Clicked(View caller){
        Intent intent = new Intent(this, AddQuizNameActivity.class);
        startActivity(intent);
    }

    public void onIbProfile_Clicked(View caller){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}