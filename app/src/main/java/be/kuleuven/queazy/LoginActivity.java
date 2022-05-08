package be.kuleuven.queazy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
        if (!usernameExists()) {
            Toast.makeText(LoginActivity.this, "Incorrect username", Toast.LENGTH_SHORT).show();
        } else if(!passwordMatch()) {
            Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
        } else if (usernameExists() && passwordMatch()) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    public boolean usernameExists() {
        EditText username = (EditText) findViewById(R.id.txtUsername);
        String newUsername = String.valueOf(username);
        ArrayList<String> usernames = new ArrayList<>();
        boolean exist = true;

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/usernameCheck";

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String responseStringUsername = "";
                            JSONArray responseArray = new JSONArray(response);
                            for(int i = 0; i < response.length(); i++){
                                JSONObject currentObject = responseArray.getJSONObject( i );
                                responseStringUsername = currentObject.getString("username");
                                usernames.add(responseStringUsername); //if possible, rewrite using lambda expressions
                            }
                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show();
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

        if (!usernames.contains(newUsername))
            exist = false;
        return exist;
    }

    public boolean passwordMatch() {
        EditText pwrd = (EditText) findViewById(R.id.Password);
        EditText username = (EditText) findViewById(R.id.txtUsername);
        String password = String.valueOf(pwrd);
        ArrayList<String> passwords = new ArrayList<>();
        boolean unique = true;

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/passwordCheck" + "/" + username.getText();

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String responseStringUsername = "";
                            JSONArray responseArray = new JSONArray(response);
                            for(int i = 0; i < response.length(); i++){
                                JSONObject currentObject = responseArray.getJSONObject( i );
                                responseStringUsername = currentObject.getString("password");
                                passwords.add(responseStringUsername); //if possible, rewrite using lambda expressions
                            }
                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show();
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

        if (!passwords.contains(password))
            unique = false;
        return unique;
    }
}