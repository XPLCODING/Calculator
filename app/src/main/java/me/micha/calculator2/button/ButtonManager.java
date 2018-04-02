package me.micha.calculator2.button;

import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;
import me.micha.calculator2.calculation.Calculator;

public class ButtonManager {

    private static List<Button> buttons = new ArrayList<>();
    private static boolean SECOND = false;

    public static void load() {
        add(new Button(R.id.second, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSecond();
            }
        }));

        add(new Button(R.id.one, "1", "", ""));
        add(new Button(R.id.two, "2", "", ""));
        add(new Button(R.id.three, "3", "", ""));
        add(new Button(R.id.four, "4", "", ""));
        add(new Button(R.id.five, "5", "", ""));
        add(new Button(R.id.six, "6", "", ""));
        add(new Button(R.id.seven, "7", "", ""));
        add(new Button(R.id.eight, "8", "", ""));
        add(new Button(R.id.nine, "9", "", ""));
        add(new Button(R.id.zero, "0", "", ""));

        add(new Button(R.id.cameraScan,new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                MainActivity.getInstance().startActivityForResult(intent, 0);
            }
        }));

        add(new Button(R.id.divide, "/", "", ""));
        add(new Button(R.id.multiplicate, "*", "!", "x!"));
        add(new Button(R.id.subtract, "−", "", ""));
        add(new Button(R.id.add, "+", "", ""));
        add(new Button(R.id.power, "^", "", ""));
        add(new Button(R.id.leftpranthese, "(", "", ""));
        add(new Button(R.id.rightparanthese, ")", "", ""));
        add(new Button(R.id.sinus, "sin(", "asin(", "asin"));
        add(new Button(R.id.cosinus, "cos(", "acos(", "acos"));
        add(new Button(R.id.tangens, "tan(", "atan(", "atan"));
        add(new Button(R.id.dot, ".", "", ""));
        add(new Button(R.id.log, "log(", "", ""));
        add(new Button(R.id.ln, "ln(", "", ""));
        add(new Button(R.id.pi, "π", "", ""));
        add(new Button(R.id.e, "e", "", ""));
        add(new Button(R.id.negative_minus, "-", "", ""));
        add(new Button(R.id.sqrt, "²", "√(", "√"));

        add(new Button(R.id.clear, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView)MainActivity.getInstance().findViewById(R.id.enterfield)).setText("");
            }
        }));

        add(new Button(R.id.enter, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = (String) ((TextView)MainActivity.getInstance().findViewById(R.id.enterfield)).getText();
                if(input != null && !input.isEmpty()) {
                    Calculator.calc(input);
                    ((TextView)MainActivity.getInstance().findViewById(R.id.enterfield)).setText("");
                }
            }
        }));

    }

    public static void add(Button button) {
        buttons.add(button);
    }

    public static boolean second() {
        return SECOND;
    }

    public static void toggleSecond() {
        SECOND = !SECOND;

        for(Button button : buttons) {
            if(button.getSecondText() != null && !button.getSecondText().isEmpty()) {
                String text = button.getText();
                button.setText(button.getSecondText());
                button.setSecondText(text);
            }
        }

    }
}
