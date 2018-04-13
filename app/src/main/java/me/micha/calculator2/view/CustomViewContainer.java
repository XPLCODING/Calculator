package me.micha.calculator2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by micha on 08.04.2018.
 */

public class CustomViewContainer extends RelativeLayout {

    private View root;
    private int xml;

    public CustomViewContainer(Context context, int xml) {
        super(context);
        this.xml = xml;
        init(context);
    }

    public CustomViewContainer(Context context, int xml, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.xml = xml;
        init(context);
    }

    private void init(Context context) {
        root = inflate(context, xml, this);
        onCreate();
    }

    public void onCreate() {}

    public View getRootView() {
        return root;
    }
}
