package me.micha.calculator2.calculation.expression.expressions;


import me.micha.calculator2.calculation.expression.MathExpression;

public class LogarithmExpression extends MathExpression {

	public LogarithmExpression() {
		super("log", true, 3);
	}

	@Override
	public NumberExpression calc(NumberExpression... input) {
		return new NumberExpression(Math.log10(input[0].getValue()));
	}

	@Override
	public int[] indexes() {
		return null;
	}

}
