package me.micha.calculator2.global;

import me.micha.calculator2.graph.Equation;

/**
 * Created by micha on 05.04.2018.
 */

public class Var {

    private String inner;

    public Var(double value) {
        inner = "" + value;
    }

    public Var(String inner) {
        this.inner = inner;
    }

    public Var(Equation equation) {
        this.inner = equation.getEquation();
    }

    public String getInner() {
        return inner;
    }
}
