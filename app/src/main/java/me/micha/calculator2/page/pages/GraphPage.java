package me.micha.calculator2.page.pages;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import me.micha.calculator2.GraphActivity;
import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;
import me.micha.calculator2.graph.Graph;
import me.micha.calculator2.graph.GraphManager;
import me.micha.calculator2.page.Page;
import me.micha.calculator2.view.YEquationView;
import me.micha.calculator2.view.YPlotView;

public class GraphPage extends Page {

    public GraphPage() {
        setFragmentId(R.layout.fragment_graph_page);
    }

    @Override
    public void onCreate() {
        LinearLayout list = (LinearLayout) findViewById(R.id.yPlotsList);

        for(int i = 0; i < GraphManager.getYPlots().size() - 1; i++) {
            Graph graph = GraphManager.getYPlot(i);
            list.addView(new YPlotView(MainActivity.getInstance(), graph.isActive(), graph.getColor(), graph.getXList(), graph.getYList()));
        }

        LinearLayout yList = (LinearLayout) findViewById(R.id.yEqList);

        for(int i = 0; i < GraphManager.getYPlots().size() - 1; i++) {
            Graph graph = GraphManager.getYEquation(i);
            yList.addView(new YEquationView(MainActivity.getInstance(), graph.isActive(), graph.getEquation().getEquation()));
        }

        for(int i = 0; i < GraphManager.getYEquations().size() - 1; i++) {

        }

        findViewById(R.id.graphPage_displayGraph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getInstance().startActivity(new Intent(MainActivity.getInstance(), GraphActivity.class));
            }
        });
    }
}
