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

import be.kuleuven.queazy.models.CurrentUser;

public class SignupActivity extends AppCompatActivity {

    private Button btnSignUp;
    private Button btnBackToLoginPage;
    private int usersWithSameName;
    private RequestQueue requestQueue;
    private boolean uniqueUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnBackToLoginPage = findViewById(R.id.btnBackToLoginPage);
        EditText textUsername = findViewById(R.id.txtUsernameCreate);
        textUsername.setOnFocusChangeListener((view, b) -> {
            if(!b){
                uniqueUsernameCheck();
            }
        });
    }

    public void onBtnBackToLoginPage_Clicked(View caller){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onBtnSignUp_Clicked(View caller){
        uniqueUsernameCheck();
        uniqueUser = false;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        uniqueUser = usersWithSameName == 0;
        if (passwordLength() && passwordMatching() && uniqueUser) {
            signup();
            signup2();
            EditText textUsername = findViewById(R.id.txtUsernameCreate);
            String user = String.valueOf(textUsername.getText());
            CurrentUser.setCurrentUser(user);
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else if (!uniqueUser) {
            Toast.makeText(SignupActivity.this, "This username already exists", Toast.LENGTH_SHORT).show();
        } else if (!passwordMatching()) {
            Toast.makeText(SignupActivity.this, "Passwords must match", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean passwordLength() {
        EditText password = findViewById(R.id.txtPasswordCreate);
        String pwrd = String.valueOf(password.getText());
        return pwrd.length() >= 8;
    }

    public boolean passwordMatching() {
        EditText password = findViewById(R.id.txtPasswordCreate);
        EditText repeatPassword = findViewById(R.id.txtPasswordCreate2);
        String pwrd = String.valueOf(password.getText());
        String rpwrd = String.valueOf((repeatPassword.getText()));
        return pwrd.equals(rpwrd);
    }

    public void uniqueUsernameCheck(){

        requestQueue = Volley.newRequestQueue(this);

        String REQUEST_URL = "https://studev.groept.be/api/a21pt216/sameusername/";

        EditText textUsername = findViewById(R.id.txtUsernameCreate);
        String un = String.valueOf(textUsername.getText());

        String SUBMIT_URL = REQUEST_URL + un;

        StringRequest submitRequest = new StringRequest(Request.Method.GET, SUBMIT_URL,
                response -> {

                    try {
                        String responseStringUsername = "";
                        JSONArray responseArray = new JSONArray(response);
                        for(int i=0;i<responseArray.length();i++){
                            JSONObject currentObject = responseArray.getJSONObject( i );
                            responseStringUsername = currentObject.getString("COUNT(*)");
                            usersWithSameName = Integer.valueOf(responseStringUsername);
                        }

                    } catch (JSONException e) {
                        Toast.makeText(SignupActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                },

                error -> Toast.makeText(SignupActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()

        );
        requestQueue.add(submitRequest);
    }

    public void signup() {
        EditText username = findViewById(R.id.txtUsernameCreate);
        EditText fullname = findViewById(R.id.txtNameCreate);
        EditText password = findViewById(R.id.txtPasswordCreate);

        requestQueue = Volley.newRequestQueue(this);

        String SUBMIT_URL = "https://studev.groept.be/api/a21pt216/SignUp";
        String requestURL = SUBMIT_URL + "/" + fullname.getText() + "/" + username.getText() + "/" + password.getText();

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                response -> {},
                error -> Toast.makeText(SignupActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(submitRequest);

    }



    public void signup2() {
        EditText username = findViewById(R.id.txtUsernameCreate);

        requestQueue = Volley.newRequestQueue(this);

        String SUBMIT_URL = "https://studev.groept.be/api/a21pt216/SignUp2";
        String requestURL = SUBMIT_URL + "/" + username.getText() + "/" + 0 + "/" + "Newbie" + "/" + 0 + "/" + 0;

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                response -> {},
                error -> Toast.makeText(SignupActivity.this, "Unable to communicate with the server", Toast.LENGTH_LONG).show()
        );
        requestQueue.add(submitRequest);
    }



}



