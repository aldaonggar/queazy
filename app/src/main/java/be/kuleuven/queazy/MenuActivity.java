package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.queazy.models.CurrentUser;

public class MenuActivity extends AppCompatActivity {
    private Button btnLogOut;
    private Button btnAddQuizSuggestion;
    private Button btnProfile;
    private Button btnQuizzes;
    private Button btnRankings;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnAddQuizSuggestion = (Button) findViewById(R.id.btnAddQuizSuggestion);
        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnQuizzes = (Button) findViewById(R.id.btnQuizzes);
        btnRankings = (Button) findViewById(R.id.btnRankings);

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