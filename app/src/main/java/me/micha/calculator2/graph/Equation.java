package me.micha.calculator2.graph;

/**
 * Created by micha on 25.03.2018.
 */

public class Equation {

    private String equation;

    public Equation(String equation) {
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    public String getCalcString() {
        return equation.replace("y=", "");
    }
}
