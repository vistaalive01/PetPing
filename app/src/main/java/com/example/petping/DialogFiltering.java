package com.example.petping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;


public class DialogFiltering extends DialogFragment {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private CheckBox ageLeastOne;
    private CheckBox ageOnetoFive;
    private CheckBox ageFivetoTen;
    private CheckBox ageTenUp;
    public OnInputSelected mOnInputSelected;
    private TextView mActionOk, mActionCancel, mInputDisplay;
    private static final String TAG = "MyCustomDialog";
    private EditText mInput;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_filtering, null);
//        editTextUsername = view.findViewById(R.id.edit_username);
//        editTextPassword = view.findViewById(R.id.edit_password);

        mInput = view.findViewById(R.id.input);

        builder.setView(view)
                .setTitle("Title")
                .setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getDialog().dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String input = mInput.getText().toString();
                        mOnInputSelected.sendInput(input);
                    }
                });

////        ageLeastOne = view.findViewById(R.id.cb_age_least1y);
////        ageOnetoFive = view.findViewById(R.id.cb_age_1to5y);
////        ageFivetoTen = view.findViewById(R.id.cb_age_5to10y);
////        ageTenUp = view.findViewById(R.id.cb_age_10_up);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface OnInputSelected{
        void sendInput(String input);
    }
}
