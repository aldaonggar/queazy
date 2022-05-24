package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.queazy.models.CurrentUser;

public class ProfileActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Button btnLogOut2;
    private Button btnBackToMenuPage;
    private TextView username;
    private int totalpoints;
    private int ranking;
    private String badge;
    private String un;
    private int rank;
    private String badge2;
    private TextView txtQuizzesPassedValue;
    private TextView txtPointsValue;
    private TextView txtBadgeValue;
    private TextView txtRankValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogOut2 = findViewById(R.id.btnLogOut);
        btnBackToMenuPage = findViewById(R.id.btnBackToMenuPage2);
        username = findViewById(R.id.txtUsername3);
        txtQuizzesPassedValue = findViewById(R.id.txtQuizzesPassedValue);
        txtPointsValue = findViewById(R.id.txtPointsValue);
        txtBadgeValue = findViewById(R.id.txtBadgeValue);
        txtRankValue = findViewById(R.id.txtRankValue);

        username.setText(CurrentUser.getCurrentUser());

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/orderUsersByPoints";

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            ranking = i + 1;
                            un = o.getString("username");
                            badge = o.getString("badge");

                            if (un.equals(CurrentUser.getCurrentUser())) {
                                this.txtBadgeValue.setText(badge);
                                this.txtRankValue.setText(String.valueOf(ranking));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(ProfileActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }


    public void onBtnLogOut_Clicked(View caller){
        CurrentUser.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onBtnBackToMenuPage2_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}