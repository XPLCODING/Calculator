package me.micha.calculator2.calculation.expression;


import me.micha.calculator2.calculation.expression.expressions.NumberExpression;

/**
 * Created by micha on 13.03.2018.
 */

public abstract class MathExpression extends Expression {

    private String symbol;
    private boolean paranthesed;
    private int priority;
    
    public MathExpression(String symbol, boolean paranthesed, int priority) {
        this.symbol = symbol;
        this.paranthesed = paranthesed;
        ExpressionManager.add(this);
    }

    public abstract NumberExpression calc(NumberExpression... input);
    
    public abstract int[] indexes();

    public String getSymbol() {
        return symbol;
    }

    public int getSymbolLength() {
        return symbol.length();
    }
    
    public boolean isParanthesed() {
		return paranthesed;
	}
    
    public boolean isBasicOperation() {
    	return symbol == "+" || symbol == "-" || symbol == "*" || symbol == "/";
    }
    
    public boolean hasPriority() {
    	return symbol == "*" || symbol == "/";
    }
    
    public int getPriority() {
		return priority;
	}
}
