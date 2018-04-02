package me.micha.calculator2.calculation.history;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
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
            final String text = entry.getEntry();
            final TextView textView = new TextView(MainActivity.getInstance());
            textView.setText(text);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(18);
            textView.setGravity(entry.getType() == HistoryEntry.Type.EXERCISE ? Gravity.LEFT : Gravity.RIGHT);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((TextView)MainActivity.getInstance().findViewById(R.id.enterfield)).setText(((TextView)MainActivity.getInstance().findViewById(R.id.enterfield)).getText() + text);
                }
            });
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

    public HistoryEntry lastEntry() {
        return history.size() > 0 ? history.get(history.size() - 1) : null;
    }

    public HistoryEntry lastEntry(int index) {
        return history.size() >= index ? history.get(history.size() - index) : null;
    }

    public List<HistoryEntry> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryEntry> history) {
        this.history = history;
    }
}
