package me.micha.calculator2.calculation.expression.expressions;


import me.micha.calculator2.calculation.expression.MathExpression;

public class PowerExpression extends MathExpression {

	public PowerExpression() {
		super("^", false, 3);
	}

	@Override
	public NumberExpression calc(NumberExpression... input) {
		return new NumberExpression(Math.pow(input[0].getValue(), input[1].getValue()));
	}

	@Override
	public int[] indexes() {
		return new int[]{-1, 1};
	}

	
	
}
