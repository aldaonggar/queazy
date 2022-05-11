package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizListActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private static final String QUEUE_URL = "https://studev.groept.be/api/a21pt216/quizName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        LinearLayout myLayout = findViewById(R.id.llQuizList);


        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET, QUEUE_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String quizname = "";
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject o = null;
                            try {
                                o = response.getJSONObject(i);
                                quizname += o.get("quizname");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            /*
                            Button myButton = new Button();
                            myButton.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT
                            ));
                            myButton.setTextColor(0x6200EE);
                            myButton.setText(quizname);
                            myLayout.addView(myButton);
                            */
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(QuizListActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show();
                    }
                }

        );

    }


    public void onBtnBackToMenuPage_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}