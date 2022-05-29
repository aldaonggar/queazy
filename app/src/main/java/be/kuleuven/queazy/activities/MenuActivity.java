package be.kuleuven.queazy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import be.kuleuven.queazy.R;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

    public void onBtnProfile_Clicked(View caller){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}