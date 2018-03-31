package me.micha.calculator2;

import android.net.Uri;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.micha.calculator2.calculation.expression.ExpressionManager;
import me.micha.calculator2.file.FileManager;
import me.micha.calculator2.page.pages.CalculatorPage;
import me.micha.calculator2.page.swipe.PagerAdapter;

public class MainActivity extends AppCompatActivity implements CalculatorPage.OnFragmentInteractionListener {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        //FileManager.load();
        ExpressionManager.load();

        loadPages();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        FileManager.save();
    }

    private void loadPages() {
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.config_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.calculator_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.graph_icon);

        tabLayout.setScrollPosition(0,0f,true);
        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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

    public static MainActivity getInstance() {
        return instance;
    }
}
