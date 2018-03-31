package me.micha.calculator2.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by micha on 29.03.2018.
 */

public class ButtonFieldView extends RelativeLayout {

    public ButtonFieldView(Context context) {
        super(context);
        init();
    }

    public ButtonFieldView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ButtonFieldView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    /**
    android:id="@+id/dot"
    android:layout_width="95.677dp"
    android:layout_height="wrap_content"
    android:layout_below="@+id/three"
    android:layout_toEndOf="@+id/zero"
    android:layout_toRightOf="@+id/zero"
    android:background="@android:color/holo_blue_dark"
    android:text="•"
    android:textStyle="bold" />
     */

    public void init() {

    }

}
