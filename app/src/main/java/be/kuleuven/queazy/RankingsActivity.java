package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RankingsActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String username;
    private int totalpoints;
    private int ranking;
    private String badge;
    private int quizzespassed;
    private int quizzesattended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rankings);
        LinearLayout myLayout = findViewById(R.id.llQuizList);

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/SignUp2/" + username + "/" + totalpoints + "/" + ranking +
                "/" + badge + "/" + quizzespassed + "/" + quizzesattended;

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            String username = o.getString("username");
                            int totalpoints = o.getInt("totalpoints");
                            int ranking = o.getInt("ranking");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(RankingsActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }


    public void onBtnBackToMenuPage3_Clicked(View caller) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}