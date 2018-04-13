package me.micha.calculator2.page;

import me.micha.calculator2.file.DataStore;

/**
 * Created by micha on 11.04.2018.
 */

public class Config {

    @DataStore(keyName = "angleType", priority = 4)
    public static boolean DEGREE = true;

}
