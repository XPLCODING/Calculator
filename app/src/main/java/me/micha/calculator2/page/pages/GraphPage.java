package me.micha.calculator2.page.pages;


import me.micha.calculator2.R;
import me.micha.calculator2.graph.GraphManager;
import me.micha.calculator2.page.Page;


public class GraphPage extends Page {

    public GraphPage() {
        setFragmentId(R.layout.fragment_graph_page);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onSelected() {
        super.onSelected();
        if(GraphManager.CHANGED) GraphManager.load();
    }
}
