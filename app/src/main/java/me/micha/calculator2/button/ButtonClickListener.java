package me.micha.calculator2.button;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;

/**
 * Created by micha on 08.03.2018.
 */

public class ButtonClickListener implements View.OnClickListener {

    public static boolean SECOND = false;

    private String enter, second;
    public ButtonClickListener(String enter, String second) {
        this.enter = enter;
        this.second = second;
    }

    @Override
    public void onClick(View view) {
        TextView enterField = (TextView) MainActivity.getInstance().findViewById(R.id.enterfield);
        enterField.setText(enterField.getText() + (SECOND ? second: enter));
    }
}
