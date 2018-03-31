package me.micha.calculator2.graph;

/**
 * Created by micha on 26.03.2018.
 */

public class GraphWindow {

    double xMin, xMax, yMin, yMax;

    public GraphWindow(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public double getXMax() {
        return xMax;
    }

    public double getXMin() {
        return xMin;
    }

    public double getYMax() {
        return yMax;
    }

    public double getYMin() {
        return yMin;
    }
}
