package com.example.varun.dialogchecker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.varun.digitallearning.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by varun on 12/03/15.
 */
public class DialogChecker extends Activity {
    AlertDialog.Builder alertadd;
    ImageView image;
    final View view;
    final Timer t;

    public DialogChecker(Context context){

        alertadd=new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_LIGHT);
        LayoutInflater factory = LayoutInflater.from(context);
        view= factory.inflate(R.layout.dialog_image, null);
        image= (ImageView) view.findViewById(R.id.imageView);
        t = new Timer();
    }

    public void showCorrect(){
        image.setImageResource(R.drawable.ic_dialog_correct);
        alertadd.setView(view);

        final AlertDialog dlg=alertadd.create();
        dlg.show();


        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 1000);
    }

    public void showInCorrect(){
        image.setImageResource(R.drawable.ic_dialog_incorrect);
        alertadd.setView(view);
        final AlertDialog dlg=alertadd.create();
        dlg.show();


        t.schedule(new TimerTask() {
            public void run() {
                dlg.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 1000);
    }
}
