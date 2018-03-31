package me.micha.calculator2.calculation.history;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;

/**
 * Created by micha on 08.03.2018.
 */

public class History {

    private List<HistoryEntry> history = new ArrayList<>();

    public History() {

    }

    public void add(HistoryEntry entry) {
        if(history.size() > 19) {
            history.remove(0);
        }

        history.add(entry);
        update();
    }

    public void update() {
        LinearLayout content = (LinearLayout) MainActivity.getInstance().findViewById(R.id.historyContent);
        content.removeAllViews();

        for(HistoryEntry entry : history) {
            TextView textView = new TextView(MainActivity.getInstance());
            textView.setText(entry.getEntry());
            textView.setTextSize(18);
            textView.setGravity(entry.getType() == HistoryEntry.Type.EXERCISE ? Gravity.LEFT : Gravity.RIGHT);
            content.addView(textView);
        }

        final ScrollView scrollView = MainActivity.getInstance().findViewById(R.id.history);

        scrollView.post(new Runnable() {

            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }

    public List<HistoryEntry> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEntry> history) {
        this.history = history;
    }
}
