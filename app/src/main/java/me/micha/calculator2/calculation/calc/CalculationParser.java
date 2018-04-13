package me.micha.calculator2.calculation.calc;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.calculation.Calculator;
import me.micha.calculator2.calculation.expression.Expression;
import me.micha.calculator2.calculation.expression.ExpressionManager;
import me.micha.calculator2.calculation.expression.MathExpression;
import me.micha.calculator2.calculation.expression.Stack;
import me.micha.calculator2.calculation.expression.expressions.NumberExpression;

/**
 * Created by micha on 13.03.2018.
 */

public class CalculationParser {

    private String input;
    private Stack output;

    public CalculationParser(String input) {
        this.input = input;
    }
    
    public String getInput() {
        return input;
    }

    public void parse() {
    	output = new Stack(splitExpressions());
    }
    
    private List<Expression> splitExpressions() {
        List<Expression> expressions = new ArrayList<>();

        String inputClone = input;

        while(inputClone.length() > 0) {
            for(MathExpression expression : ExpressionManager.getMathExpressions()) {
                String sub = inputClone.substring(0, expression.getSymbolLength() >= inputClone.length() ? inputClone.length() : expression.getSymbolLength());
                if(sub.equals(expression.getSymbol())) {
                    if(expression.indexes() != null && expression.indexes().length == 2 && !expression.isParanthesed()) {
                        if(!expressions.isEmpty()) {
                            expressions.add(expression);
                            inputClone = inputClone.substring(expression.getSymbolLength());
                        }else {
                            expressions.add(new NumberExpression(Calculator.getHistory().lastEntry(2).getEntryAsDouble()));
                            expressions.add(expression);
                            inputClone = inputClone.substring(expression.getSymbolLength());
                        }
                    }else {
                        expressions.add(expression);
                        inputClone = inputClone.substring(expression.getSymbolLength());
                    }
                }
            }
            
            String number = "";

            for(char c : inputClone.toCharArray()) {
                if(Character.isDigit(c) || c == '.' || c == '-') {
                    number += c;
                    inputClone = inputClone.substring(1);
                }else {
                	break;
                }
            }

            if(!number.equalsIgnoreCase("")) {
            	expressions.add(new NumberExpression(Double.parseDouble(number)));
                number = "";
            }

        }
        
        return expressions;
    }
    
    public Stack getOutput() {
		return output;
	}
    
}
