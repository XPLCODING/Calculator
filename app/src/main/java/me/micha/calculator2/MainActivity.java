package me.micha.calculator2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import me.micha.calculator2.button.ButtonManager;
import me.micha.calculator2.calculation.expression.ExpressionManager;
import me.micha.calculator2.file.FileManager;
import me.micha.calculator2.global.Vars;
import me.micha.calculator2.graph.GraphManager;
import me.micha.calculator2.ocr.OCRManager;
import me.micha.calculator2.page.pages.CalculatorPage;
import me.micha.calculator2.page.swipe.PageChangeListener;
import me.micha.calculator2.page.swipe.PagerAdapter;

public class MainActivity extends AppCompatActivity implements CalculatorPage.OnFragmentInteractionListener {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        FileManager.load(savedInstanceState);

        Vars.load();

        OCRManager.copyFile();

        ExpressionManager.load();

        loadPages();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FileManager.save(outState);
        outState.putBoolean("BUTTON_SECOND", ButtonManager.second());
        outState.putString("ENTERFIELD",(String) ((TextView)findViewById(R.id.enterfield)).getText());

    }

    private void loadPages() {
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.config_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.calculator_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.graph_icon);

        tabLayout.setScrollPosition(1,0f,true);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new PageChangeListener(tabLayout, pagerAdapter));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(pagerAdapter.getPage(tab.getPosition()) != null) {
                    pagerAdapter.getPage(tab.getPosition()).onSelected();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 21) {
            if(data != null) {

            }
        }else {
            if(data != null) {
                OCRManager ocr = new OCRManager();
                String scanned = ocr.recognize((Bitmap) data.getExtras().get("data"));
                ((TextView)findViewById(R.id.enterfield)).setText(scanned);
            }
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }
}
