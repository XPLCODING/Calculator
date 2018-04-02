package me.micha.calculator2.graph;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;
import me.micha.calculator2.file.DataStore;

/**
 * Created by micha on 25.03.2018.
 */

public class GraphManager {

    @DataStore(keyName = "graphWindow", priority = 4)
    private static GraphWindow graphWindow;
    public static boolean CHANGED = true;
    private static List<Graph> graphs = new ArrayList<>();

    public static void load() {
        graphWindow = new GraphWindow(0, 10, 0, 10);
        GraphView graphView = getGraphView();

        graphView.removeAllSeries();

        for(Graph graph : getGraphs()) {
            graph.calculateLineData();
            graphView.addSeries(graph.getLineGraphSeries());
        }

        setWindow();

        CHANGED = false;
    }

    public static void addGraph(Graph graph) {
        if(graph.getId() == -1) throw new IllegalArgumentException("Graph has no Id");
        graphs.add(graph);
        graph.calculateLineData();
        getGraphView().addSeries(graph.getLineGraphSeries());

    }

    private static void setWindow() {
        Viewport viewport = getGraphView().getViewport();
        viewport.setXAxisBoundsManual(true);
        viewport.setYAxisBoundsManual(true);
        viewport.setMinX(getGraphWindow().getXMin());
        viewport.setMaxX(getGraphWindow().getXMax());
        viewport.setMinY(getGraphWindow().getYMin());
        viewport.setMaxY(getGraphWindow().getYMax());
    }

    public static GraphView getGraphView() {
        return ((GraphView)MainActivity.getInstance().findViewById(R.id.graphView));
    }

    public static GraphWindow getGraphWindow() {
        return graphWindow;
    }

    public static List<Graph> getGraphs() {
        return graphs;
    }
}
