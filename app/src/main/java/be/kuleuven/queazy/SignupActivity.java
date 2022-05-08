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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private Button btnSignUp;
    private Button btnBackToLoginPage;
    private RequestQueue requestQueue;
    private RequestQueue requestQueue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnBackToLoginPage = (Button) findViewById(R.id.btnBackToLoginPage);
    }

    public void onBtnBackToLoginPage_Clicked(View caller){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onBtnSignUp_Clicked(View caller){
        if (passwordLength() && passwordMatching() && uniqueUsername()) {
            signup();
            signup2();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else if (!passwordMatching()) {
            Toast.makeText(SignupActivity.this, "Passwords must match", Toast.LENGTH_SHORT).show();
        } else if (!uniqueUsername()) {
            Toast.makeText(SignupActivity.this, "This username already exists", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean passwordLength() {
        EditText password = (EditText) findViewById(R.id.txtPasswordCreate);
        String pwrd = String.valueOf(password.getText());
        if (pwrd.length() >= 8)
            return true;
        else
            return false;
    }

    public boolean passwordMatching() {
        EditText password = (EditText) findViewById(R.id.txtPasswordCreate);
        EditText repeatPassword = (EditText) findViewById(R.id.txtPasswordCreate2);
        String pwrd = String.valueOf(password.getText());
        String rpwrd = String.valueOf((repeatPassword.getText()));
        if (pwrd.equals(rpwrd))
            return true;
        else
            return false;
    }

    public boolean uniqueUsername() {
        EditText username = (EditText) findViewById(R.id.txtUsernameCreate);
        String newUsername = String.valueOf(username);
        ArrayList<String> usernames = new ArrayList<>();
        boolean unique = true;

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = "https://studev.groept.be/api/a21pt216/usernameCheck";

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET, requestURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String responseStringUsername = "";
                            for(int i = 0; i < response.length(); i++){
                                JSONObject currentObject = response.getJSONObject( i );
                                responseStringUsername = currentObject.getString("username");
                                usernames.add(responseStringUsername); //if possible, rewrite using lambda expressions
                            }
                        } catch (JSONException e) {
                            Toast.makeText(SignupActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(submitRequest);

        if (usernames.contains(newUsername))
            unique = false;
        return unique;
    }

    public void signup() {
        EditText username = (EditText) findViewById(R.id.txtUsernameCreate);
        EditText fullname = (EditText) findViewById(R.id.txtNameCreate);
        EditText password = (EditText) findViewById(R.id.txtPasswordCreate);

        requestQueue = Volley.newRequestQueue(this);

        String SUBMIT_URL = "https://studev.groept.be/api/a21pt216/SignUp";
        String requestURL = SUBMIT_URL + "/" + fullname.getText() + "/" + username.getText() + "/" + password.getText();

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        requestQueue.add(submitRequest);

    }

    public void signup2() {
        EditText username = (EditText) findViewById(R.id.txtUsernameCreate);

        requestQueue = Volley.newRequestQueue(this);

        String SUBMIT_URL = "https://studev.groept.be/api/a21pt216/SignUp2";
        String requestURL = SUBMIT_URL + "/" + username.getText() + "/" + null + "/" + null + "/" + null + "/" + null + "/" + null;

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {}
                }
        );
        requestQueue2.add(submitRequest);
    }
}



