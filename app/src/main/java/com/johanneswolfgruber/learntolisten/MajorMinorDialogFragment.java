package com.johanneswolfgruber.learntolisten;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;

public class MajorMinorDialogFragment extends DialogFragment {

    private String[] mAnswers = {"Major", "Minor", "Augmented", "Diminished"};
    private static Object checkedItem;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Was the triad major, minor, augmented or diminished?")
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
