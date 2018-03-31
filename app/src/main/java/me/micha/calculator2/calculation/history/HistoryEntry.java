package me.micha.calculator2.calculation.history;

/**
 * Created by micha on 08.03.2018.
 */

public class HistoryEntry {

    private String entry;
    private Type type;

    public HistoryEntry(String entry, Type type) {
        this.entry = entry;
        this.type = type;
    }

    public String getEntry() {
        return entry;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        RESULT, EXERCISE;

    }
}
