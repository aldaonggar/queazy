package be.kuleuven.queazy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import be.kuleuven.queazy.R;
import be.kuleuven.queazy.models.CurrentUser;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String SUBMIT_URL = "https://studev.groept.be/api/a21pt216/loginCheck/";

        String requestURL = SUBMIT_URL + username + "/" + password;

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                response ->  {

                    String responseStringUsername;
                    String responseStringPassword;

                    try {
                        JSONObject responseObject = response.getJSONObject(0);
                        responseStringUsername = responseObject.getString("username");
                        responseStringPassword = responseObject.getString("password");

                        if(responseStringUsername.equals(username) && responseStringPassword.equals(password)){
                            CurrentUser.setCurrentUser(username);
                            Intent intent2 = new Intent(caller.getContext(), MenuActivity.class);
                            startActivity(intent2);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                },

                error -> Toast.makeText(LoginActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()

        );

        requestQueue.add(submitRequest);

    }
}