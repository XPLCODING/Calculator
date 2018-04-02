package me.micha.calculator2.calculation.expression.expressions;


import me.micha.calculator2.calculation.expression.MathExpression;

public class SquareRootExpression extends MathExpression {

	public SquareRootExpression() {
		super("√", true, 3);
	}

	@Override
	public NumberExpression calc(NumberExpression... input) {
		return new NumberExpression(Math.sqrt(input[0].getValue()));
	}

	@Override
	public int[] indexes() {
		return null;
	}

}
