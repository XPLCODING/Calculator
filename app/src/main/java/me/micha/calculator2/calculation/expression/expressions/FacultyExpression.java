package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

public class FacultyExpression extends MathExpression {

    public FacultyExpression() {
        super("!", false, 4);
    }

    @Override
    public NumberExpression calc(NumberExpression... input) {
        if(input[0].getValue() == Math.floor(input[0].getValue())) {
            return new NumberExpression(faculty(Double.valueOf(Math.floor(input[0].getValue())).intValue()));
        }else {
            throw new IllegalArgumentException("n is not an integer");
        }
    }

    public double faculty(int n) {
        if(n < 1) throw new IllegalArgumentException("n cannot be negative in faculties");
        double current = 1;
        for(int i = 2; i <= n; i++) {
            current *= i;
        }

        return current;
    }

    @Override
    public int[] indexes() {
        return new int[]{-1};
    }
}
