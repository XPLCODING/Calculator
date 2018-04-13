package me.micha.calculator2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import me.micha.calculator2.R;

/**
 * Created by micha on 08.04.2018.
 */

public class ButtonFieldView extends CustomViewContainer {

    private TextView input;

    public ButtonFieldView(Context context) {
        super(context, R.layout.buttonfield_view);
    }

    public ButtonFieldView(Context context, AttributeSet attributeSet) {
        super(context, R.layout.buttonfield_view, attributeSet);
    }

    public void setInputField(TextView textView) {
        input = textView;
    }

    @Override
    public void onCreate() {

    }
}
