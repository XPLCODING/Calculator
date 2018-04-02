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
import me.micha.calculator2.graph.Graph;
import me.micha.calculator2.graph.GraphManager;
import me.micha.calculator2.graph.GraphType;
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
            String s = state != null ? state.getString("HISTORY_ENTRY_" + i) : get("history.entry." + i);
            if(s == null) return;
            String[] split = s.split("//");
            if(split.length != 2) return;
            list.add(new HistoryEntry(split[0], HistoryEntry.Type.valueOf(split[1])));
        }
        history.setHistory(list);
        dataSore.put("history", history);
        //GRAPHWINDOW
        double xMin = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MIN_X")) : Double.parseDouble(get("graphWinodw.xMin"));
        double xMax = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MAX_X")) : Double.parseDouble(get("graphWinodw.xMax"));
        double yMin = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MIN_Y")) : Double.parseDouble(get("graphWinodw.yMin"));
        double yMax = state != null ? Double.parseDouble(state.getString("GRAPHWINDOW_MAX_Y")) : Double.parseDouble(get("graphWinodw.yMax"));
        dataSore.put("graphWindow", new GraphWindow(xMin, xMax, yMin, yMax));
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
        for(Graph graph : GraphManager.getGraphs()) {
            if(graph.getGraphType() == GraphType.LINE) {
                if(state != null) {
                    state.putString("GRAPH_Y" + graph.getId(), graph.getEquation().getEquation());
                }else {
                    put("graph.y." + graph.getId(), graph.getEquation().getEquation());
                }
            }else if(graph.getGraphType() == GraphType.POINTS) {

            }
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

    private static String get(String key) {
        SharedPreferences prefs = MainActivity.getInstance().getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        return prefs.getString(key, "");
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
