package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

/**
 * Created by micha on 28.03.2018.
 */

public class AsinusExpression extends MathExpression {

    public AsinusExpression() {
        super("asin", true, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Math.toDegrees(Math.asin(input[0].getValue())));
    }

    @Override
    public int[] indexes() {
        return null;
    }

}
