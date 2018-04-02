package me.micha.calculator2.graph;

import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.List;

import me.micha.calculator2.calculation.Calculator;

/**
 * Created by micha on 25.03.2018.
 */

public class Graph {

    private BaseSeries<DataPoint> graphSeries;
    private GraphType graphType;
    private Equation equation;
    private int id = -1;

    public Graph(Equation equation) {
        graphType = GraphType.LINE;
        this.equation = equation;
    }

    public Graph(List<Double> listX, List<Double> listY) {
        if(listX.size() == listY.size() && listX.size() > 0) {
            graphType = GraphType.POINTS;
            graphSeries = new PointsGraphSeries<>(toData(listX, listY));
        }
    }

    public void calculateLineData() {
       if(graphType == GraphType.LINE) {
           graphSeries = new LineGraphSeries<>();

           int maxPoints = (int) ((GraphManager.getGraphWindow().getXMax() - GraphManager.getGraphWindow().getXMin()) / 0.1);

           for(double d = GraphManager.getGraphWindow().getXMin(); d < GraphManager.getGraphWindow().getXMax(); d+=0.1) {
               graphSeries.appendData(new DataPoint(d, Calculator.singleCalc(equation.getCalcString().replace("x", String.valueOf(d)))), true, maxPoints);
           }
       }
    }

    public Equation getEquation() {
        return equation;
    }

    public void setColor(int color) {
        graphSeries.setColor(color);
    }

    public void setData(DataPoint[] data) {
        graphSeries = new LineGraphSeries<>(data);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public GraphType getGraphType() {
        return graphType;
    }

    public BaseSeries<DataPoint> getLineGraphSeries() {
        return graphSeries;
    }

    public void setGraphType(GraphType graphType) {
        this.graphType = graphType;
    }

    private DataPoint[] toData(List<Double> list1, List<Double> list2) {
        DataPoint[] dataPoints = new DataPoint[list1.size()];
        for(int i = 0; i < list1.size(); i++) {
            dataPoints[i] = new DataPoint(list1.get(i), list2.get(i));
        }

        return dataPoints;
    }


}
