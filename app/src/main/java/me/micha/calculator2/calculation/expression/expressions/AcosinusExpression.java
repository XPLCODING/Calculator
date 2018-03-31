package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

/**
 * Created by micha on 28.03.2018.
 */

public class AcosinusExpression extends MathExpression {

    public AcosinusExpression() {
        super("acos", true, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Math.toDegrees(Math.acos(input[0].getValue())));
    }

    @Override
    public int[] indexes() {
        return null;
    }

}
