package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

/**
 * Created by micha on 27.03.2018.
 */

public class TangensExpression extends MathExpression {

    public TangensExpression() {
        super("tan", true, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Math.tan(Math.toRadians(input[0].getValue())));
    }

    @Override
    public int[] indexes() {
        return null;
    }
}
