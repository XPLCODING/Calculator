package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

/**
 * Created by micha on 04.04.2018.
 */

public class KommaExpression extends MathExpression {

    public KommaExpression() {
        super(",", false, 4);
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
