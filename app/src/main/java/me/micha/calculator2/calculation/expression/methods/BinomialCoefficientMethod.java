package me.micha.calculator2.calculation.expression.methods;

import me.micha.calculator2.calculation.expression.ExpressionMethods;
import me.micha.calculator2.calculation.expression.MethodExpression;
import me.micha.calculator2.calculation.expression.expressions.NumberExpression;

/**
 * Created by micha on 09.04.2018.
 */

public class BinomialCoefficientMethod extends MethodExpression{

    public BinomialCoefficientMethod() {
        super("bin_c");
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        return new NumberExpression(ExpressionMethods.bin_c(Double.valueOf(input[0].getValue()).intValue(), Double.valueOf(input[1].getValue()).intValue()));
    }
}
