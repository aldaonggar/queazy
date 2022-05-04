package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button btnSignUpSuggestion;
    private Button btnLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSignUpSuggestion = (Button) findViewById(R.id.btnSignUpSuggestion);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
    }

    public void onBtnSignUpSuggestion_Clicked(View caller){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public void onBtnLogIn_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}