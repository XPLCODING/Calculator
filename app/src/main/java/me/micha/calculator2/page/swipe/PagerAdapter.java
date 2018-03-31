package me.micha.calculator2.page.swipe;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.page.Page;
import me.micha.calculator2.page.pages.CalculatorPage;
import me.micha.calculator2.page.pages.ConfigPage;
import me.micha.calculator2.page.pages.GraphPage;

/**
 * Created by micha on 24.03.2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int tabs;
    private Map<Integer, Page> pages = new HashMap<>();

    public PagerAdapter(FragmentManager fragmentManager, int tabs) {
        super(fragmentManager);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                ConfigPage page = new ConfigPage();
                pages.put(0, page);
                return page;
            case 1:
                CalculatorPage page1 = new CalculatorPage();
                pages.put(1, page1);
                return page1;
            case 2:
                GraphPage page2 = new GraphPage();
                pages.put(2, page2);
                return page2;
            default:
                return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        pages.remove(position);
    }

    public Page getPage(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return tabs;
    }

}
