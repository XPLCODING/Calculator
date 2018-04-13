package me.micha.calculator2.calculation.expression;

import me.micha.calculator2.calculation.expression.expressions.NumberExpression;

/**
 * Created by micha on 09.04.2018.
 */

public class MethodExpression extends MathExpression {

    public MethodExpression(String name) {
        super(name, true, 4);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return null;
    }

    @Override
    public int[] indexes() {
        return null;
    }
}
