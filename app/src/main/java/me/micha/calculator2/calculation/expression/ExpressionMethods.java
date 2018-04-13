package me.micha.calculator2.calculation.expression;

/**
 * Created by micha on 09.04.2018.
 */

public class ExpressionMethods {

    public static double faculty(int n) {
        if(n < 1) throw new IllegalArgumentException("n cannot be negative in faculties");
        double current = 1;
        for(int i = 2; i <= n; i++) {
            current *= i;
        }

        return current;
    }

    public static double bin_c(int n, int k) {
        return faculty(n)/(faculty(k) * faculty(n - k));
    }

}
