package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private Button btnLogOut2;
    private Button btnBackToMenuPage;
    private Button btnPersonalDetails;
    private TextView username;
    private String un;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogOut2 = (Button) findViewById(R.id.btnLogOut2);
        btnBackToMenuPage = (Button) findViewById(R.id.btnBackToMenuPage2);
        btnPersonalDetails = (Button) findViewById(R.id.btnPersonalDetails);
        username =  (TextView) findViewById(R.id.txtUsername3);

        username.setText(LoginActivity.getValue());
    }

    public void onBtnLogOut2_Clicked(View caller){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onBtnBackToMenuPage2_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnPersonalDetails_Clicked(View caller){
        Intent intent = new Intent(this, PersonalDetailsActivity.class);
        startActivity(intent);
    }
}