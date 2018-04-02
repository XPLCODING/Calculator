package me.micha.calculator2.page.swipe;

import android.support.design.widget.TabLayout;

import me.micha.calculator2.page.Page;

public class PageChangeListener extends TabLayout.TabLayoutOnPageChangeListener {

    private PagerAdapter pagerAdapter;
    private int currentPostition = 0;

    public PageChangeListener(TabLayout tabLayout, PagerAdapter pagerAdapter) {
        super(tabLayout);
        this.pagerAdapter = pagerAdapter;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        Page hide = pagerAdapter.getPage(currentPostition);
        if(hide != null) hide.onPausePage();
        Page show = pagerAdapter.getPage(position);
        if(show != null) show.onResumePage();
        currentPostition = position;
        System.out.println("currentPosition: " + currentPostition);
    }
}
