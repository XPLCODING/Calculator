package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

public class EulerExpression extends MathExpression {

    public EulerExpression() {
        super("e", false, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Math.E);
    }

    @Override
    public int[] indexes() {
        return null;
    }
}
