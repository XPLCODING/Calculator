package me.micha.calculator2.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.NumberPicker;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;
import me.micha.calculator2.color.ColorPickerDialog;

/**
 * Created by micha on 08.04.2018.
 */

public class YPlotView extends CustomViewContainer {

    private CheckBox checkBox;
    private ImageView color;
    private NumberPicker x, y;

    public YPlotView(Context context, AttributeSet attributeSet) {
        super(context, R.layout.yplot_view, attributeSet);
    }

    public YPlotView(Context context, boolean active, int color, int xList, int yList) {
        super(context, R.layout.yplot_view);
        checkBox.setActivated(active);
        this.color.setBackgroundColor(color);
        x.setValue(xList);
        y.setValue(yList);
    }

    @Override
    public void onCreate() {
        checkBox = findViewById(R.id.active);
        color = findViewById(R.id.colorPick);

        x = findViewById(R.id.xList);
        String[] array = {"L1", "L2", "L3", "L4", "L5", "L6"};
        x.setMinValue(0);
        x.setMaxValue(array.length - 1);
        x.setDisplayedValues(array);

        y = findViewById(R.id.yList);
        y.setMinValue(0);
        y.setMaxValue(array.length - 1);
        y.setDisplayedValues(array);

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPickerDialog dialog = new ColorPickerDialog(MainActivity.getInstance(), new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void colorChanged(String key, int color) {
                        findViewById(R.id.colorPick).setBackgroundColor(color);
                    }
                }, "color", Color.BLACK, Color.BLACK);
                dialog.show();
            }
        });

    }

    public boolean isActive() {
        return checkBox.isActivated();
    }

}
