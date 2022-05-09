package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class PersonalDetailsActivity extends AppCompatActivity {
    private Button btnDeleteAccount;
    private Button btnBackToProfilePage;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        btnBackToProfilePage = (Button) findViewById(R.id.btnBackToProfilePage);
        btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
    }

    public void onBtnBackToProfilePage_Clicked(View caller){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onBtnDeleteAccount_Clicked(View caller){
        TextView username = (TextView) findViewById(R.id.txtUsername3);
        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/deleteAcc/" + username.getText();

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject responseObject = response.getJSONObject(0);
                            Toast.makeText(PersonalDetailsActivity.this, "Deletion was successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(caller.getContext(), LoginActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            Toast.makeText(PersonalDetailsActivity.this, "Could not delete an account", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PersonalDetailsActivity.this, "Server is not responding", Toast.LENGTH_LONG).show();
                    }
                }

        );
        requestQueue.add(submitRequest);
    }
}