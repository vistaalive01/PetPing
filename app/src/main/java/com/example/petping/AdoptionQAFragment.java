package com.example.petping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AdoptionQAFragment extends Fragment {
    private ViewFlipper flipper;
    private ImageButton btnOne, btnTwoB,btnTwoA;
    private ImageButton btnThreeB,btnThreeA;
    private ImageButton btnFourB,btnFourA;
    private ImageButton btnFiveB,btnFiveA;
    private ImageButton btnSixB,btnSixA;
    private ImageButton btnSevenB,btnSevenA;
    private ImageButton btnEightB,btnEightA;
    private ImageButton btnNineB,btnNineA;
    private ImageButton btnTenB,btnTenA;
    private ImageButton btnEleven;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_adoption_qa_process, null);
        flipper = view.findViewById(R.id.flipper_qa_process);

        flipper = view.findViewById(R.id.flipper_qa_process);
        btnOne = view.findViewById(R.id.qa_one_btn);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_two)));
            }
        });

        btnTwoB = view.findViewById(R.id.qa_two_btnB);
        btnTwoA = view.findViewById(R.id.qa_two_btnA);
        btnTwoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_one)));
            }
        });
        btnTwoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_three)));
            }
        });

        btnThreeB = view.findViewById(R.id.qa_three_btnB);
        btnThreeA = view.findViewById(R.id.qa_three_btnA);
        btnThreeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_two)));
            }
        });
        btnThreeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_four)));
            }
        });

        btnFourB = view.findViewById(R.id.qa_four_btnB);
        btnFourA = view.findViewById(R.id.qa_four_btnA);
        btnFourB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_three)));
            }
        });
        btnFourA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_five)));
            }
        });

        btnFiveB = view.findViewById(R.id.qa_five_btnB);
        btnFiveA = view.findViewById(R.id.qa_five_btnA);
        btnFiveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_four)));
            }
        });
        btnFiveA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_six)));
            }
        });

        btnSixB = view.findViewById(R.id.qa_six_btnB);
        btnSixA = view.findViewById(R.id.qa_six_btnA);
        btnSixB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_five)));
            }
        });
        btnSixA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_seven)));
            }
        });

        btnSevenB = view.findViewById(R.id.qa_seven_btnB);
        btnSevenA = view.findViewById(R.id.qa_seven_btnA);
        btnSevenB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_six)));
            }
        });
        btnSevenA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_eight)));
            }
        });

        btnEightB = view.findViewById(R.id.qa_eight_btnB);
        btnEightA = view.findViewById(R.id.qa_eight_btnA);
        btnEightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_seven)));
            }
        });
        btnEightA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_nine)));
            }
        });

        btnNineB = view.findViewById(R.id.qa_nine_btnB);
        btnNineA = view.findViewById(R.id.qa_nine_btnA);
        btnNineB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_eight)));
            }
        });
        btnNineA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_ten)));
            }
        });

        btnTenB = view.findViewById(R.id.qa_ten_btnB);
        btnTenA = view.findViewById(R.id.qa_ten_btnA);
        btnTenB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_nine)));
            }
        });
        btnTenA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_eleven)));
            }
        });

        btnEleven = view.findViewById(R.id.qa_eleven_btn);
        btnEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipper.setDisplayedChild(flipper.indexOfChild(view.findViewById(R.id.qa_ten)));
            }
        });

        return view;
    }
}
