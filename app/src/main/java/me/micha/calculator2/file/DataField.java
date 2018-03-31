package me.micha.calculator2.file;

import java.lang.reflect.Field;

/**
 * Created by micha on 11.03.2018.
 */

public class DataField {

    private Field field;
    private DataStore dataStore;
    private Object instance;

    public DataField(Field field, DataStore dataStore, Object instance) {
        this.field = field;
        this.dataStore = dataStore;
        this.instance = instance;
    }

    public Field getField() {
        return field;
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    public Object getInstance() {
        return instance;
    }
}
