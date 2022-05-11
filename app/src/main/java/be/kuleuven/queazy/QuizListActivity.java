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

public class QuizListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);
        LinearLayout myLayout = findViewById(R.id.llQuizList);
        Button myButton = new Button(this);
        myButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        myButton.setText("asdasdassdasdasdasdadsdasdasdasdasdasdasdsadasdasdSdasdasdasdasdasdaddsa");
        myButton.setBackgroundColor(0xFFFFC107);
        myButton.setTextColor(0xFFFFFFFF);
        myLayout.addView(myButton);
        Button myButton1 = new Button(this);
        myButton1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        myLayout.addView(myButton1);
        Button myButton2 = new Button(this);
        myButton2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        myLayout.addView(myButton2);
    }

    public void onBtnBackToMenuPage_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}