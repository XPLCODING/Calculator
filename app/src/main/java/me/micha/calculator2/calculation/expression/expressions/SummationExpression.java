package me.micha.calculator2.calculation.expression.expressions;


import me.micha.calculator2.calculation.expression.MathExpression;

/**
 * Created by micha on 13.03.2018.
 */

public class SummationExpression extends MathExpression {

    public SummationExpression() {
        super("+", false, 1);
    }

    @Override
	public NumberExpression calc(NumberExpression... input) {
		return new NumberExpression((input[0].getValue() + input[1].getValue()));
	}

	@Override
	public int[] indexes() {
		return new int[]{-1, 1};
	}
}
