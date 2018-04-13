package me.micha.calculator2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.micha.calculator2.global.Vars;

public class ListPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_picker);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.listsButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.dataSetsButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                List<String> list = Vars.getCustomDataSets();
                findViewById(R.id.add_dataSet).setVisibility(View.VISIBLE);
                if(!list.isEmpty()) {
                    RelativeLayout listLayout = findViewById(R.id.container);
                    removeAllViews(listLayout, R.id.add_dataSet);
                    for(String s : Vars.getCustomDataSets()) {
                        Button button = new Button(ListPickerActivity.this);
                        button.setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);
                        button.setText(s);
                        button.setBackgroundColor(Color.TRANSPARENT);
                        listLayout.addView(button);
                    }
                 }else {
                    TextView text = new TextView(ListPickerActivity.this);
                    text.setText("Es wurden keine Datensätze gefunden.");
                    RelativeLayout layout = findViewById(R.id.container);
                    removeAllViews(layout, R.id.add_dataSet);
                    layout.addView(text);
                }
            }
        });
    }

    public void removeAllViews(RelativeLayout layout, int apart) {
        for(int i = 0; i < layout.getChildCount(); i++) {
            if(layout.getChildAt(i).getId() != apart) {
                layout.removeViewAt(i);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) {
            setResult(ResultCodes.OK);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
