package me.micha.calculator2.button;

import android.view.View;
import android.widget.TextView;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;

/**
 * Created by micha on 08.03.2018.
 */

public class ButtonClickListener implements View.OnClickListener {

    private String enter, second;

    public ButtonClickListener(String enter, String second) {
        this.enter = enter;
        this.second = second;
    }

    @Override
    public void onClick(View view) {
        TextView enterField = ButtonManager.getEnterfield();
        enterField.setText(enterField.getText() + ((second != null && !second.isEmpty()) ? (ButtonManager.second() ? second : enter) : enter));
    }
}
