package be.kuleuven.queazy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddQuestion3Activity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnCancel3;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView txtAreYouSure;
    private Button btnPopupBackToMenu, btnPopupCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question3);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion3);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz3);
        btnCancel3 = (Button) findViewById(R.id.btnCancel3);
    }

    public void onBtnAddQuestion3_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion4Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz3_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnCancel3_Clicked(View caller){
        createNewContactDialog();
    }

    public void createNewContactDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        txtAreYouSure = (TextView) contactPopupView.findViewById(R.id.txtAreYouSure);
        btnPopupCancel = (Button) contactPopupView.findViewById(R.id.btnPopupCancel);
        btnPopupBackToMenu = (Button) contactPopupView.findViewById(R.id.btnPopupBackToMenu);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btnPopupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define button
                dialog.dismiss();
            }
        });


        btnPopupBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define button
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

    }

}