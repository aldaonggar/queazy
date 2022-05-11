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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button btnSignUpSuggestion;
    private Button btnLogIn;
    private RequestQueue requestQueue;
    //private TextView username;

    private static String value;
    public static String getValue() {
        return value;
    }

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

    public void onBtnLogIn_Clicked(View caller) {
        EditText textUsername = (EditText) findViewById(R.id.txtUsername);
        EditText textPassword = (EditText) findViewById(R.id.Password);
        String username = String.valueOf(textUsername.getText());
        String password = String.valueOf(textPassword.getText());


        requestQueue = Volley.newRequestQueue(this);

        String SUBMIT_URL = "https://studev.groept.be/api/a21pt216/loginCheck/";

        String requestURL = SUBMIT_URL + username + "/" + password;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String responseStringUsername = "";
                        String responseStringPassword = "";

                        try {
                            JSONObject responseObject = response.getJSONObject(0);
                            responseStringUsername = responseObject.getString("username");
                            responseStringPassword = responseObject.getString("password");

                            if(responseStringUsername.equals(username) && responseStringPassword.equals(password)){
                                //Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                //intent.putExtra("username", username);
                                //startActivity(intent);
                                value = textUsername.getText().toString().trim();
                                Intent intent2 = new Intent(caller.getContext(), MenuActivity.class);
                                startActivity(intent2);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show();
                    }
                }

        );

        requestQueue.add(submitRequest);

    }
}