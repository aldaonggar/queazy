package be.kuleuven.queazy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import be.kuleuven.queazy.models.AddQuestion;

public class AddQuestion1Activity extends AppCompatActivity implements AddQuestion {

    private Button btnAddQuestion;
    private Button btnAddQuiz;
    private Button btnCancel;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView txtAreYouSure;
    private EditText txtQuestion, txtA, txtB, txtC, txtD;
    private RadioGroup rgAns;
    private Button btnPopupBackToMenu, btnPopupCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question1);
        btnAddQuestion = (Button) findViewById(R.id.btnAddQuestion1);
        btnAddQuiz = (Button) findViewById(R.id.btnAddQuiz1);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtQuestion = (EditText) findViewById(R.id.txtWriteQuestion1);
        txtA = (EditText) findViewById(R.id.txtA1);
        txtB = (EditText) findViewById(R.id.txtB1);
        txtC = (EditText) findViewById(R.id.txtC1);
        txtD = (EditText) findViewById(R.id.txtD1);
        rgAns = (RadioGroup) findViewById(R.id.rgAns);
    }

    @Override
    public boolean TxtfieldsCheck() {
        String qs = txtQuestion.getText().toString();
        String ansA = txtA.getText().toString();
        String ansB = txtB.getText().toString();
        String ansC = txtC.getText().toString();
        String ansD = txtD.getText().toString();
        RadioButton selectedBtn = (RadioButton) findViewById(rgAns.getCheckedRadioButtonId());
        String checkedBtn = selectedBtn.getText().toString();
        if (!qs.equals("") && !ansA.equals("") && !ansB.equals("") && !ansC.equals("") && !ansD.equals("") && !checkedBtn.equals(""))
            return true;
        return false;
    }

    @Override
    public String getCorAns() {
        RadioButton selectedBtn = (RadioButton) findViewById(rgAns.getCheckedRadioButtonId());
        String checkedBtn = selectedBtn.getText().toString();
        return checkedBtn;
    }

    public void onBtnAddQuestion1_Clicked(View caller){
        Intent intent = new Intent(this, AddQuestion1Activity.class);
        startActivity(intent);
    }

    public void onBtnAddQuiz1_Clicked(View caller){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onBtnCancel_Clicked(View caller){
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