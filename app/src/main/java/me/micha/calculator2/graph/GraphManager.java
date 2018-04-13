package me.micha.calculator2.graph;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.micha.calculator2.GraphActivity;
import me.micha.calculator2.R;
import me.micha.calculator2.file.DataStore;
import me.micha.calculator2.global.Vars;

/**
 * Created by micha on 25.03.2018.
 */

public class GraphManager {

    @DataStore(keyName = "graphWindow", priority = 4)
    private static GraphWindow graphWindow;
    public static boolean CHANGED = true;
    @DataStore(keyName = "yPlots", priority = 4)
    private static Map<Integer, Graph> y_plots = new HashMap<>();
    @DataStore(keyName = "yEquations", priority = 4)
    private static Map<Integer, Graph> y_eq = new HashMap<>();

    public static void reload() {
        graphWindow = new GraphWindow(0, 10, 0, 10);
        GraphView graphView = getGraphView();

        for(Map.Entry<Integer, Graph> entry : y_plots.entrySet()) {
            if(entry.getValue().isActive() && entry.getValue().hasChanged()) {
                entry.getValue().calculateLineData();
                graphView.addSeries(entry.getValue().getLineGraphSeries());
            }
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
        return GraphActivity.INSTANCE.findViewById(R.id.graphView);
    }

    public static Graph getYPlot(int index) {
        return y_plots.get(index);
    }

    public static Graph getYEquation(int index) {
        return y_eq.get(index);
    }

    public static GraphWindow getGraphWindow() {
        return graphWindow;
    }

    public static Map<Integer, Graph> getYPlots() {
        return y_plots;
    }

    public static Map<Integer, Graph> getYEquations() {
        return y_eq;
    }
}
