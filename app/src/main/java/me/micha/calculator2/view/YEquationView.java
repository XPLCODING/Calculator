package me.micha.calculator2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.micha.calculator2.GraphActivity;
import me.micha.calculator2.R;

/**
 * Created by micha on 08.04.2018.
 */

public class YEquationView extends CustomViewContainer {

    private String equation;

    public YEquationView(Context context) {
        super(context, R.layout.yeq_view);
    }

    public YEquationView(Context context, boolean active, String equation) {
        super(context, R.layout.yeq_view);
        this.equation = equation;
    }

    public YEquationView(Context context, AttributeSet attributeSet) {
        super(context, R.layout.yeq_view, attributeSet);
    }

    public String getEquation() {
        return equation;
    }

    @Override
    public void onCreate() {
        findViewById(R.id.equation).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonFieldView buttonFieldView = new ButtonFieldView(GraphActivity.INSTANCE);
                buttonFieldView.setInputField(((TextView)findViewById(R.id.equation)));
                ButtonFieldView.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 416);
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
                buttonFieldView.setLayoutParams(params);
                ((RelativeLayout)findViewById(R.id.graphPage_container)).addView(buttonFieldView);
            }
        });
    }
}
