package me.micha.calculator2.calculation.expression.expressions;


import me.micha.calculator2.calculation.expression.MathExpression;

public class RightParantheseExpression extends MathExpression {

	public RightParantheseExpression() {
		super(")", false, 4);
	}

	@Override
	public NumberExpression calc(NumberExpression... input) {
		return null;
	}

	@Override
	public int[] indexes() {
		return new int[]{};
	}

}
