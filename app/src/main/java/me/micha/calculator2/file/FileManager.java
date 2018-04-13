package me.micha.calculator2.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.micha.calculator2.MainActivity;
import me.micha.calculator2.calculation.Calculator;
import me.micha.calculator2.calculation.history.History;
import me.micha.calculator2.calculation.history.HistoryEntry;
import me.micha.calculator2.graph.Equation;
import me.micha.calculator2.graph.Graph;
import me.micha.calculator2.graph.GraphManager;
import me.micha.calculator2.graph.GraphWindow;

/**
 * Created by micha on 08.03.2018.
 */

public class FileManager {

    public static String PREFERENCE = "datastore";
    private static Map<String, Object> dataSore = new HashMap<>();

    public static void load(Bundle state) {
        loadDataStore(state);
        loadDataStoreFields();
    }

    private static void loadDataStore(Bundle state) {
        //HISTORY
        History history = new History();
        List<HistoryEntry> list = new ArrayList<>();
        for(int i = 0; i < 19; i++) {
            String s = state != null ? state.getString("HISTORY_ENTRY_" + i) : getString("history.entry." + i);
            if(s == null) return;
            String[] split = s.split("//");
            if(split.length != 2) return;
            list.add(new HistoryEntry(split[0], HistoryEntry.Type.valueOf(split[1])));
        }
        history.setHistory(list);
        dataSore.put("history", history);
        //GRAPHWINDOW
        double xMin = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MIN_X")) : Double.parseDouble(getString("graphWinodw.xMin"));
        double xMax = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MAX_X")) : Double.parseDouble(getString("graphWinodw.xMax"));
        double yMin = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MIN_Y")) : Double.parseDouble(getString("graphWinodw.yMin"));
        double yMax = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MAX_Y")) : Double.parseDouble(getString("graphWinodw.yMax"));
        dataSore.put("graphWindow", new GraphWindow(xMin, xMax, yMin, yMax));
        //GRAPHS
        Map<Integer, Graph> yplots = new HashMap<>(), yeq = new HashMap<>();
        for(int i = 0; i < 9; i++) {
            yplots.put(i, getGraph(i, state));
            yeq.put(i, getYGraph(i, state));
        }
        dataSore.put("yPlots", yplots);
        dataSore.put("yEquations", yeq);
    }

    private static void loadDataStoreFields() {
        for(DataField dataField : getAnnotatedFields()) {
            if(dataSore.containsKey(dataField.getDataStore().keyName())) {
                dataField.getField().setAccessible(true);
                try {
                    dataField.getField().set(dataSore.get(dataField.getDataStore().keyName()), dataField.getInstance());
                    dataSore.remove(dataField.getDataStore().keyName());
                } catch (IllegalAccessException e) {
                    //ERROR
                }
            }else {
                //ERROR
            }
        }
    }

    public static void save(Bundle state) {
        //HISTORY
        for(int i = 0; i < Calculator.getHistory().getHistory().size() - 1; i++) {
            HistoryEntry entry = Calculator.getHistory().getHistory().get(i);

            if(state != null) {
                state.putString("HISTORY_ENTRY_" + i, entry.getEntry() + "//" + entry.getType().name());
            }else {
                put("history.entry." + i, entry.getEntry() + "//" + entry.getType().name());
            }
        }
        //GRAPHWINDOW
        GraphWindow graphWindow = GraphManager.getGraphWindow();
        if(graphWindow != null) {
            if(state != null) {
                state.putString("GRAPHWINDOW_MIN_X", String.valueOf(graphWindow.getXMin()));
                state.putString("GRAPHWINDOW_MAX_X", String.valueOf(graphWindow.getXMax()));
                state.putString("GRAPHWINDOW_MIN_Y", String.valueOf(graphWindow.getYMin()));
                state.putString("GRAPHWINDOW_MAX_Y", String.valueOf(graphWindow.getYMax()));
            }else {
                put("graphWindow.xMin", String.valueOf(graphWindow.getXMin()));
                put("graphWindow.xMax", String.valueOf(graphWindow.getXMax()));
                put("graphWindow.yMin", String.valueOf(graphWindow.getYMin()));
                put("graphWindow.yMax", String.valueOf(graphWindow.getYMax()));
            }
        }

        //GRAPHS
        for(Map.Entry<Integer, Graph> entry : GraphManager.getYPlots().entrySet()) {
            putGraph(entry.getValue(), state);
        }

        for(Map.Entry<Integer, Graph> entry : GraphManager.getYEquations().entrySet()) {
            putYGraph(entry.getValue(), state);
        }
    }

    private static void putGraph(Graph graph, Bundle state) {
        if(state != null) {
            String path = "GRAPH_" + graph.getId();
            state.putInt(path + "_COLOR", graph.getColor());
            state.putBoolean(path + "_ACTIVE", graph.isActive());
            state.putInt(path + "_XLIST", graph.getXList());
            state.putInt(path + "_YLIST", graph.getYList());
        }else {
            String path = "graph." + graph.getId();
            put(path + ".color", graph.getColor());
            put(path + ".active", graph.isActive());
            put(path + ".xlist", graph.getXList());
            put(path + ".ylist", graph.getYList());
        }
    }

    private static Graph getGraph(int index, Bundle state) {
        if(state != null) {
            String path = "GRAPH_" + index;
            int color = state.getInt(path + "_COLOR");
            boolean active = state.getBoolean(path + "_ACTIVE");
            int xlist = state.getInt(path + "_XLIST");
            int ylist = state.getInt(path + "_YLIST");

            return new Graph(index, active, color, xlist, ylist);
        }else {
            String path = "graph." + index;
            int color = getInt(path + ".color");
            boolean active = getBoolean(path + ".active");
            int xlist = getInt(path + ".xlist");
            int ylist = getInt(path + ".ylist");

            return new Graph(index, active, color, xlist, ylist);
        }
    }

    private static void putYGraph(Graph graph, Bundle state) {
        if(state != null) {
            String path = "GRAPH_Y_" + graph.getId();
            state.putInt(path + "_COLOR", graph.getColor());
            state.putBoolean(path + "_ACTIVE", graph.isActive());
            state.putString(path + "_EQUATION", graph.getEquation().getCalcString());
        }else {
            String path = "graph.y." + graph.getId();
            put(path + ".color", graph.getColor());
            put(path + ".active", graph.isActive());
            put(path + ".xlist", graph.getXList());
            put(path + ".ylist", graph.getYList());
        }
    }

    private static Graph getYGraph(int index, Bundle state) {
        if(state != null) {
            String path = "GRAPH_Y_" + index;
            int color = state.getInt(path + "_COLOR");
            boolean active = state.getBoolean(path + "_ACTIVE");
            String eq = getString(path + "_EQUATION");

            return new Graph(index, active, color, new Equation(eq));
        }else {
            String path = "graph.y." + index;
            int color = state.getInt(path + ".color");
            boolean active = state.getBoolean(path + ".active");
            String eq = getString(path + ".equation");

            return new Graph(index, active, color, new Equation(eq));
        }
    }

    private static void put(String key, Object o) {
        SharedPreferences.Editor editor = MainActivity.getInstance().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE).edit();

        if(o instanceof String) {
            editor.putString(key, (String) o);
        }else if(o instanceof Boolean) {
            editor.putBoolean(key, (boolean) o);
        }else if(o instanceof Float) {
            editor.putFloat(key, (float) o);
        }else if(o instanceof Integer) {
            editor.putInt(key, (int) o);
        }else if(o instanceof Long) {
            editor.putLong(key, (long) o);
        }else return;

        editor.apply();
    }

    private static String getString(String key) {
        SharedPreferences prefs = MainActivity.getInstance().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    private static int getInt(String key) {
        SharedPreferences prefs = MainActivity.getInstance().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return prefs.getInt(key, -1);
    }

    private static boolean getBoolean(String key) {
        SharedPreferences prefs = MainActivity.getInstance().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }



    public static List<DataField> getAnnotatedFields() {
        List<DataField> dataFields = new ArrayList<>();

        for(Class clazz : getClasses()) {
            for(Field field : clazz.getDeclaredFields()) {
                if(field.isAnnotationPresent(DataStore.class)) {
                    try {
                        dataFields.add(new DataField(field, field.getAnnotation(DataStore.class), clazz.newInstance()));
                    } catch (InstantiationException e) {
                    } catch (IllegalAccessException e) {
                    }
                }
            }
        }

        return dataFields;
    }

    public static List<Class> getClasses() {
        
        return new ArrayList<>();
    }


}
