package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.queazy.interfaces.BackBtn;

public class RankingsActivity extends AppCompatActivity implements BackBtn {

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
        myLayout.setOrientation(LinearLayout.VERTICAL);
        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/orderUsersByPoints";

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response -> {

                    for(int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject o = response.getJSONObject(i);
                            username = o.getString("username");
                            totalpoints = o.getInt("totalpoints");
                            ranking = i + 1;

                            LinearLayout row = new LinearLayout(this);
                            row.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            ));

                            for (int j = 0; j < 3; j++) {
                                setBtnCharacteristics(row, j);
                            }
                            myLayout.addView(row);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                error -> Toast.makeText(RankingsActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(queueRequest);
    }

    @Override
    public void onBackBtnClicked(View caller) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void setBtnCharacteristics(LinearLayout row, int j) {

        Button myButton = new Button(this);

        String txt = "";
        int width = 0;
        int x = 0;
        if (j == 0) {
            txt = String.valueOf(ranking);
            width = 100;
            x = 30;
        } else if (j == 1) {
            txt = username;
            width = 600;
            x = 60;
        } else if (j == 2) {
            txt = String.valueOf(totalpoints);
            width = 250;
            x = 90;
        }

        myButton.setLayoutParams(new LinearLayout.LayoutParams(
                width,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        myButton.setX(x);
        myButton.setY(-10);
        myButton.setText(txt);
        myButton.setTextColor(0xFFFFFFFF);
        setBtnColor(myButton);
        row.addView(myButton);
    }

    public void setBtnColor(Button myButton) {
        if(ranking == 1)
            myButton.setBackgroundColor(0xF0CBA135);
        else if(ranking == 2)
            myButton.setBackgroundColor(0xFFC0C0C0);
        else if(ranking == 3)
            myButton.setBackgroundColor(0xFFCD7F32);
        else
            myButton.setBackgroundColor(0xFFFFC107);

    }
}