package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.Expression;

/**
 * Created by micha on 10.04.2018.
 */

public class MethodContainerExpression extends Expression {

    private NumberExpression[] array;

    public MethodContainerExpression(NumberExpression[] array) {
        this.array = array;
    }

    public NumberExpression[] getArray() {
        return array;
    }
}
