package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

public class NaturalLogarithmExpression extends MathExpression {

    public NaturalLogarithmExpression() {
        super("ln", true, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Math.log(input[0].getValue()));
    }

    @Override
    public int[] indexes() {
        return null;
    }
}
