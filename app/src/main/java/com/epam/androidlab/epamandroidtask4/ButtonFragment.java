package com.epam.androidlab.epamandroidtask4;

import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class ButtonFragment extends Fragment {
    private static final String KEY = "buttonColors";
    private ArrayList<Integer> buttonColors;
    private Button buttonFirst;
    private Button buttonSecond;
    private Button buttonThird;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonColors = new ArrayList<>();
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_button, container, false);

        buttonFirst = (Button) view.findViewById(R.id.button_first);
        buttonSecond = (Button) view.findViewById(R.id.button_second);
        buttonThird = (Button) view.findViewById(R.id.button_third);

        setButtonColors();

        buttonFirst.setBackgroundColor(buttonColors.get(0));
        buttonSecond.setBackgroundColor(buttonColors.get(1));
        buttonThird.setBackgroundColor(buttonColors.get(2));

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TypedArray colors = getResources().obtainTypedArray(R.array.button_colors);
                int randomIndex = (int) (Math.random() * colors.length());
                int color = colors.getColor(randomIndex, 0);
                v.setBackgroundColor(color);
                switch (v.getId()) {
                    case R.id.button_first:
                        buttonColors.set(0, color);
                        break;
                    case R.id.button_second:
                        buttonColors.set(1, color);
                        break;
                    case R.id.button_third:
                        buttonColors.set(2, color);
                        break;
                }
            }
        };

        buttonFirst.setOnClickListener(onClickListener);
        buttonSecond.setOnClickListener(onClickListener);
        buttonThird.setOnClickListener(onClickListener);

        return view;
    }

    private void setButtonColors() {
        buttonColors.add(getButtonColor(buttonFirst));
        buttonColors.add(getButtonColor(buttonSecond));
        buttonColors.add(getButtonColor(buttonThird));
    }

    private int getButtonColor(Button button) {
        ColorDrawable buttonClr = (ColorDrawable) button.getBackground();
        return buttonClr.getColor();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<Integer> colors = savedInstanceState.getIntegerArrayList(KEY);
            for (int i = 0; i < colors.size(); i++) {
                buttonColors.set(i, colors.get(i));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(KEY, buttonColors);
    }
}
