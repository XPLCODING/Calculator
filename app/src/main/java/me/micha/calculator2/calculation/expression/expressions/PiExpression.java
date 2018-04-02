package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

public class PiExpression extends MathExpression {

    public PiExpression() {
        super("π", false, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Math.PI);
    }

    @Override
    public int[] indexes() {
        return null;
    }
}
