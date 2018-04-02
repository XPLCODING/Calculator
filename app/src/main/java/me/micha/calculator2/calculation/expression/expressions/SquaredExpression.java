package me.micha.calculator2.calculation.expression.expressions;


import me.micha.calculator2.calculation.expression.MathExpression;

public class SquaredExpression extends MathExpression {

	public SquaredExpression() {
		super("²", false, 3);
	}

	@Override
	public NumberExpression calc(NumberExpression... input) {
		return new NumberExpression(Math.pow(input[0].getValue(), 2));
	}

	@Override
	public int[] indexes() {
		return new int[]{-1};
	}

	
	
}
