package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.Expression;

/**
 * Created by micha on 13.03.2018.
 */

public class NumberExpression extends Expression {

    private double value;

    public NumberExpression(double value) {
        this.value = value;

    }

    public double getValue() {
        return value;
    }
}
