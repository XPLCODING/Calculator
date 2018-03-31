package me.micha.calculator2.page.pages;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.micha.calculator2.R;
import me.micha.calculator2.button.ButtonClickListener;
import me.micha.calculator2.calculation.Calculator;
import me.micha.calculator2.page.Page;

public class CalculatorPage extends Page {

    public CalculatorPage() {
        setFragmentId(R.layout.fragment_calculator_page);
        setRotatable(false);
    }

    @Override
    public void onCreate() {
        findViewById(R.id.one).setOnClickListener(new ButtonClickListener("1", ""));
        findViewById(R.id.two).setOnClickListener(new ButtonClickListener("2", ""));
        findViewById(R.id.three).setOnClickListener(new ButtonClickListener("3", ""));
        findViewById(R.id.four).setOnClickListener(new ButtonClickListener("4", ""));
        findViewById(R.id.five).setOnClickListener(new ButtonClickListener("5", ""));
        findViewById(R.id.six).setOnClickListener(new ButtonClickListener("6", ""));
        findViewById(R.id.seven).setOnClickListener(new ButtonClickListener("7", ""));
        findViewById(R.id.eight).setOnClickListener(new ButtonClickListener("8", ""));
        findViewById(R.id.nine).setOnClickListener(new ButtonClickListener("9", ""));
        findViewById(R.id.zero).setOnClickListener(new ButtonClickListener("0", ""));

        findViewById(R.id.cameraScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        findViewById(R.id.divide).setOnClickListener(new ButtonClickListener("/", ""));
        findViewById(R.id.multiplicate).setOnClickListener(new ButtonClickListener("*", ""));
        findViewById(R.id.subtract).setOnClickListener(new ButtonClickListener("-", ""));
        findViewById(R.id.add).setOnClickListener(new ButtonClickListener("+", ""));
        findViewById(R.id.power).setOnClickListener(new ButtonClickListener("^", ""));
        findViewById(R.id.leftpranthese).setOnClickListener(new ButtonClickListener("(", ""));
        findViewById(R.id.rightparanthese).setOnClickListener(new ButtonClickListener(")", ""));
        findViewById(R.id.sinus).setOnClickListener(new ButtonClickListener("sin(", "asin("));
        findViewById(R.id.cosinus).setOnClickListener(new ButtonClickListener("cos(", "acos("));
        findViewById(R.id.tangens).setOnClickListener(new ButtonClickListener("tan(", "atan("));
        findViewById(R.id.dot).setOnClickListener(new ButtonClickListener(".", ""));

        findViewById(R.id.second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonClickListener.SECOND = !ButtonClickListener.SECOND;

                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout);


            }
        });

        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String input = (String) ((TextView)findViewById(R.id.enterfield)).getText();
                if(input != null && !input.isEmpty()) {
                    Calculator.calc(input);
                    ((TextView)findViewById(R.id.enterfield)).setText("");
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String scanned = Calculator.getStringFromImage((Bitmap)data.getExtras().get("data"));
        ((TextView)findViewById(R.id.enterfield)).setText(scanned);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null) getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(getActivity() != null) getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }
}
