package me.micha.calculator2.file;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by micha on 11.03.2018.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface DataStore {

    String keyName();

    int priority();

}
