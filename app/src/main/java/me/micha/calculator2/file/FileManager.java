package me.micha.calculator2.file;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;
import me.micha.calculator2.MainActivity;
import me.micha.calculator2.calculation.Calculator;
import me.micha.calculator2.calculation.history.History;
import me.micha.calculator2.calculation.history.HistoryEntry;

/**
 * Created by micha on 08.03.2018.
 */

public class FileManager {

    public static String PREFERENCE = "datastore";
    private static Map<String, Object> dataSore = new HashMap<>();

    public static void load() {
        loadDataStore();
        loadDataStoreFields();
    }

    private static void loadDataStore() {
        //HISTORY
        History history = new History();
        List<HistoryEntry> list = new ArrayList<>();
        for(int i = 0; i < 19; i++) {
            String s = get("history.entry." + i);
            if(s == null) return;
            String[] split = s.split("//");
            list.add(new HistoryEntry(split[0], HistoryEntry.Type.valueOf(split[1])));
        }
        history.setHistory(list);
        dataSore.put("history", history);
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

    public static void save() {
        //HISTORY
        for(int i = 0; i < Calculator.getHistory().getHistory().size() - 1; i++) {
            HistoryEntry entry = Calculator.getHistory().getHistory().get(i);
            put("history.entry." + i, entry.getEntry() + "//" + entry.getType().name());
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
