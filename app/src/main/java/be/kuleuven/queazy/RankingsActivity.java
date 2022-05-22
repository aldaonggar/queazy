package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
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
        LinearLayout myLayout = findViewById(R.id.llRankings);

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/orderUsersByPoints";

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            String username = o.getString("username");
                            int totalpoints = o.getInt("totalpoints");
                            int ranking = i + 1;


                            //ranking

                            Button myButton = new Button(this);

                            myButton.setLayoutParams(new LinearLayout.LayoutParams(
                                    100,100,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            ));
                            myButton.setTextColor(0xFFFFFFFF);
                            //myButton.setBackgroundColor(0xFFFFC107);
                            myButton.setText(String.valueOf(ranking));
                            myButton.setX(45);
                            myLayout.addView(myButton);


                            //username

                            Button myButton2 = new Button(this);
                            myButton2.setLayoutParams(new LinearLayout.LayoutParams(
                                    600,100,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            ));
                            myButton2.setTextColor(0xFFFFFFFF);
                            //myButton2.setBackgroundColor(0xFFFFC107);
                            myButton2.setText(username);
                            myButton2.setX(200);
                            myButton2.setY(-102);
                            myLayout.addView(myButton2);


                            //points

                            Button myButton3 = new Button(this);
                            myButton3.setLayoutParams(new LinearLayout.LayoutParams(
                                    150,100,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            ));
                            myButton3.setTextColor(0xFFFFFFFF);
                            //myButton3.setBackgroundColor(0xFFFFC107);myButton3.setBackgroundColor(0xFFFFC107);
                            myButton3.setText(String.valueOf(totalpoints));
                            myButton3.setX(850);
                            myButton3.setY(-201);
                            myLayout.addView(myButton3);


                            if(i + 1 == 1){
                                myButton.setBackgroundColor(0xF0CBA135);
                                myButton2.setBackgroundColor(0xF0CBA135);
                                myButton3.setBackgroundColor(0xF0CBA135);
                            }else if(i + 1 == 2){
                                myButton.setBackgroundColor(0xFFC0C0C0);
                                myButton2.setBackgroundColor(0xFFC0C0C0);
                                myButton3.setBackgroundColor(0xFFC0C0C0);
                            }else if(i + 1 == 3){
                                myButton.setBackgroundColor(0xFFCD7F32);
                                myButton2.setBackgroundColor(0xFFCD7F32);
                                myButton3.setBackgroundColor(0xFFCD7F32);
                            }else{
                                myButton.setBackgroundColor(0xFFFFC107);
                                myButton2.setBackgroundColor(0xFFFFC107);
                                myButton3.setBackgroundColor(0xFFFFC107);
                            }


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