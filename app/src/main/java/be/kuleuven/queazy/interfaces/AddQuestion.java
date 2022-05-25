package be.kuleuven.queazy.interfaces;

import android.view.View;

import java.util.ArrayList;

public interface AddQuestion {

    boolean txtfieldsCheck();
    String getCorAns();
    void nextActivity(View caller);
    void collectAnswers(ArrayList<String> answers);
    void addQuizToDB();
    void onBtnCancel_Clicked(View caller);
}
