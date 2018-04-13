package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.ExpressionMethods;
import me.micha.calculator2.calculation.expression.MathExpression;

public class FacultyExpression extends MathExpression {

    public FacultyExpression() {
        super("!", false, 4);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        if(input[0].getValue() == Math.floor(input[0].getValue())) {
            return new NumberExpression(ExpressionMethods.faculty(Double.valueOf(Math.floor(input[0].getValue())).intValue()));
        }else {
            throw new IllegalArgumentException("n is not an integer");
        }
    }

    @Override
    public int[] indexes() {
        return new int[]{-1};
    }
}
