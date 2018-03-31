package me.micha.calculator2.graph;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.R;

/**
 * Created by micha on 25.03.2018.
 */

public class GraphManager {

    private static GraphWindow graphWindow;
    public static boolean CHANGED = true;
    private static List<Graph> graphs = new ArrayList<>();

    public static void load() {
        graphWindow = new GraphWindow(0, 10, 0, 10);

        //Graph g = new Graph(Arrays.asList(1d, 2d, 3d, 4d ,5d, 6d, 7d), Arrays.asList(2.6d, 5.5d, 4d, 9d, 2d, 10d, 7.5d));
        Graph g = new Graph(new Equation("y=2^x"));

        graphs.add(g);

        GraphView graphView = getGraphView();

        graphView.removeAllSeries();

        for(Graph graph : getGraphs()) {
            graph.calculateLineData();
            graphView.addSeries(graph.getLineGraphSeries());
        }

        setWindow();

        CHANGED = false;
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
