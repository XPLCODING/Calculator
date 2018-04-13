package me.micha.calculator2.global;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.micha.calculator2.file.DataStore;

/**
 * Created by micha on 05.04.2018.
 */

public class Vars {

    @DataStore(keyName = "vars", priority = 3)
    private static Map<String, Var> vars = new HashMap<>();
    private static List<List<Double>> lists = new ArrayList<>();

    public static void setVar(String key, Var var) {
        vars.remove(key);
        vars.put(key, var);
    }

    public static List<List<Double>> getLists() {
        return lists;
    }

    public static List<Double> getList(int index) {
        if(lists.size() > index) {
            return lists.get(index);
        }else {
            return new ArrayList<>();
        }
    }

    public static void load() {
        new File(Environment.getExternalStorageDirectory(), "datasets").mkdirs();
    }

    public static List<String> getCustomDataSets() {
        File dir = new File(Environment.getExternalStorageDirectory(), "datasets");
        List<String> list = new ArrayList<>();

        if(dir.exists()) {
            for(String k : dir.list()) {
                if(k.contains(".datasets")) {
                    list.add(k.replace(".dataset", ""));
                }
            }
        }

        return list;
    }

}
