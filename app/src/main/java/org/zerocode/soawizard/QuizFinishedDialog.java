package org.zerocode.soawizard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Damjan on 01-Jul-18.
 */

public class QuizFinishedDialog extends Dialog {
    public Activity c;
    public Dialog d;
    public Button yes, no;

    public QuizFinishedDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quizfinished);

    }


}
