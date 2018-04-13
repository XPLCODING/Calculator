package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;
import me.micha.calculator2.page.Config;

/**
 * Created by micha on 28.03.2018.
 */

public class AtangensExpression extends MathExpression {

    public AtangensExpression() {
        super("atan", true, 3);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(Config.DEGREE ? Math.toDegrees(Math.atan(input[0].getValue())) : Math.atan(input[0].getValue()));
    }

    @Override
    public int[] indexes() {
        return null;
    }

}
