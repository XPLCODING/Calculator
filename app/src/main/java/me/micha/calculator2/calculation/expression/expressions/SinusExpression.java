package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;
import me.micha.calculator2.page.Config;

/**
 * Created by micha on 27.03.2018.
 */

public class SinusExpression extends MathExpression {

    public SinusExpression() {
        super("sin", true, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Math.sin(Config.DEGREE ? Math.toRadians(input[0].getValue()) : input[0].getValue()));
    }

    @Override
    public int[] indexes() {
        return null;
    }
}
