package me.micha.calculator2.page.pages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.TextView;

import me.micha.calculator2.R;
import me.micha.calculator2.button.ButtonManager;
import me.micha.calculator2.calculation.Calculator;
import me.micha.calculator2.page.Page;

public class CalculatorPage extends Page {

    public CalculatorPage() {
        setFragmentId(R.layout.fragment_calculator_page);
        setRotatable(false);
    }

    @Override
    public void onCreate() {
        ButtonManager.load();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String scanned = Calculator.getStringFromImage((Bitmap)data.getExtras().get("data"));
        ((TextView)findViewById(R.id.enterfield)).setText(scanned);
    }
}
