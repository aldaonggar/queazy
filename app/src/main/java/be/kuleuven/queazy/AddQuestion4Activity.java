package be.kuleuven.queazy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddQuestion4Activity extends AppCompatActivity {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnCancel4;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView txtAreYouSure;
    private Button btnPopupBackToMenu, btnPopupCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question4);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion4);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz4);
        btnCancel4 = (Button) findViewById(R.id.btnCancel4);
    }

    public void onBtnAddQuestion4_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion5Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz4_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnCancel4_Clicked(View caller){
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