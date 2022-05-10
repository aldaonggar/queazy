package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RankingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
    }

    public void onBtnBackToMenuPage3_Clicked(View caller) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}