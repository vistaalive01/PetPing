package com.example.petping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.fragment.app.DialogFragment;


public class DialogFiltering extends DialogFragment {
    private CheckBox ageLeastOne;
    private CheckBox ageOnetoFive;
    private CheckBox ageFivetoTen;
    private CheckBox ageTenUp;
    private Spinner spinColor;
    public  filterSelected mOnInputSelected;
    private EditText mInput;
    private ArrayList<String> petSearchAge = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_filtering, null);

        mInput = view.findViewById(R.id.input);
        ageLeastOne = view.findViewById(R.id.cb_age_least1y);
        ageOnetoFive = view.findViewById(R.id.cb_age_1to5y);
        ageFivetoTen = view.findViewById(R.id.cb_age_5to10y);
        ageTenUp = view.findViewById(R.id.cb_age_10_up);

        spinColor = view.findViewById(R.id.color_spinner);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(getContext(), R.array.color_array, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinColor.setAdapter(colorAdapter);

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
                        if(ageLeastOne.isChecked()){
                            petSearchAge.add("0 ปี");
                        }
                        if(ageOnetoFive.isChecked()){
                            petSearchAge.add("1 ปี");
                            petSearchAge.add("2 ปี");
                            petSearchAge.add("3 ปี");
                            petSearchAge.add("4 ปี");
                            petSearchAge.add("5 ปี");
                        }
                        if(ageFivetoTen.isChecked()){
                            petSearchAge.add("6 ปี");
                            petSearchAge.add("7 ปี");
                            petSearchAge.add("8 ปี");
                            petSearchAge.add("9 ปี");
                            petSearchAge.add("10 ปี");
                        }
                        if(ageTenUp.isChecked()){
                            petSearchAge.add("10 ปี");
                        }
                        String input = mInput.getText().toString();
                        String color = spinColor.getSelectedItem().toString();
                        mOnInputSelected.sendFiltering(input, color, petSearchAge);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputSelected = (filterSelected) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface filterSelected{
        void sendFiltering(String input, String color, ArrayList<String> petSearchAge);
    }
}
