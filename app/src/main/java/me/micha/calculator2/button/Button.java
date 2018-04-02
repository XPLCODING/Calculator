package me.micha.calculator2.button;

import android.view.View;

import me.micha.calculator2.MainActivity;

public class Button {

    private String enter, second, secondText;
    private int id;

    public Button(int id, String enter, String second, String secondText) {
        this.id = id;
        this.enter = enter;
        this.second = second;
        this.secondText = secondText;

        findViewById(id).setOnClickListener(new ButtonClickListener(enter, second));
    }

    public Button(int id, View.OnClickListener onClickListener) {
        this.id = id;

        findViewById(id).setOnClickListener(onClickListener);
    }

    public void setText(String text) {
        ((android.widget.Button)findViewById(id)).setText(text);
    }

    public String getText() {
        return (String)((android.widget.Button)findViewById(id)).getText();
    }

    public int getId() {
        return id;
    }

    public String getEnter() {
        return enter;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }

    public String getSecond() {
        return second;
    }

    public View findViewById(int id) {
        return MainActivity.getInstance().findViewById(id);
    }
}
