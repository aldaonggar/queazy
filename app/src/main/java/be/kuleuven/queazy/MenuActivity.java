package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {
    private Button btnLogOut;
    private Button btnAddQuizSuggestion;
    private ImageButton ibProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnAddQuizSuggestion = (Button) findViewById(R.id.btnAddQuizSuggestion);
        ibProfile = (ImageButton) findViewById(R.id.ibProfile);
    }

    public void onBtnLogOut_Clicked(View caller){
        Intent intent = new Intent(this, LoginActivity.class);
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