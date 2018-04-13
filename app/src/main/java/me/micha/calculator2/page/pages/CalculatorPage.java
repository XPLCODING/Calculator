package me.micha.calculator2.page.pages;

import me.micha.calculator2.R;
import me.micha.calculator2.button.ButtonManager;
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
}
