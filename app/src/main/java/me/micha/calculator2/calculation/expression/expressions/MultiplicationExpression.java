package me.micha.calculator2.calculation.expression.expressions;

import me.micha.calculator2.calculation.expression.MathExpression;

public class MultiplicationExpression extends MathExpression {

	public MultiplicationExpression() {
		super("*", false, 2);
	}

	@Override
	public NumberExpression calc(NumberExpression... input) {
		return new NumberExpression((input[0].getValue() * input[1].getValue()));
	}

	@Override
	public int[] indexes() {
		return new int[]{-1, 1};
	}

}
