package com.johanneswolfgruber.learntolisten;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;

public class IntervalDialogFragment extends DialogFragment {

    private String[] mAnswers = {"Perfect Unison", "Minor Second", "Major Second", "Minor Third",
                                    "Major Third", "Perfect Fourth", "Tritone", "Perfect Fifth",
                                    "Minor Sixth", "Major Sixth", "Minor Seventh",
                                    "Major Seventh", "Perfect Octave"};

    private static Object checkedItem;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Which interval did you hear?")
                .setSingleChoiceItems(mAnswers, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListView mListView = ((AlertDialog)dialog).getListView();
                        checkedItem = mListView.getAdapter().getItem(mListView
                                .getCheckedItemPosition());
                        String answer = (String) checkedItem;
                        GameActivity callingActivity = (GameActivity) getActivity();
                        callingActivity.onUserSelectValue(answer);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    public static String getAnswer(){
        return (String) checkedItem;
    }
}
