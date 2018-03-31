package me.micha.calculator2.page.pages;

import android.widget.SeekBar;
import android.widget.TextView;

import me.micha.calculator2.R;
import me.micha.calculator2.page.Page;

/**
 * Created by micha on 27.03.2018.
 */

public class ConfigPage extends Page {

    public ConfigPage() {
        setFragmentId(R.layout.fragment_config_page);
    }

    @Override
    public void onCreate() {
        ((SeekBar)findViewById(R.id.decimalPlaces)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((TextView)findViewById(R.id.dec)).setText("Decimal places:" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onSelected() {

    }
}
