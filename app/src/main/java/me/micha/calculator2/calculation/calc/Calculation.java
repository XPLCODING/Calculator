package me.micha.calculator2.calculation.calc;


import me.micha.calculator2.calculation.expression.MathExpression;
import me.micha.calculator2.calculation.expression.MethodExpression;
import me.micha.calculator2.calculation.expression.Stack;
import me.micha.calculator2.calculation.expression.expressions.MethodContainerExpression;
import me.micha.calculator2.calculation.expression.expressions.NumberExpression;

public class Calculation {
	
	private Stack stack;
	private double result;
	
	public Calculation(Stack stack) {
		this.stack = stack;
		calc();
	}
	
	public Stack getStack() {
		return stack; 
	}
	
	public double getResult() {
		return result;
	}
	
	public void calc() {
		while(stack.hasNextParanthese()) {
			calcNextParanthese();
		}

		calcMethods();
		
		calcBasicOperations();
		
		result = ((NumberExpression) stack.get(0)).getValue();
	}
	
	private void calcNextParanthese() {
		Stack paranthese = stack.firstParantheseSection();

		if(paranthese.toString().contains(",")) {
			stack.remove(stack.getStartParantheses(), stack.getEndPrantheses());
			String[] split =  paranthese.toString().trim().split(",");
			NumberExpression[] array = new NumberExpression[split.length];
			for(int i = 0; i < split.length; i++) {
				array[i] = new NumberExpression(Double.parseDouble(split[i]));
			}

			stack.add(stack.getStartParantheses(), new MethodContainerExpression(array));
		}else if(!paranthese.isEmpty()) {
			stack.remove(stack.getStartParantheses(), stack.getEndPrantheses());
			stack.add(stack.getStartParantheses(), new NumberExpression(new Calculation(paranthese).getResult()));
		}
		
	}
	
	private void calcMethods() {
		for(int i = 0; i < stack.getExpressions().size(); i++) {
			if(stack.get(i) instanceof MathExpression && !((MathExpression)stack.get(i)).isBasicOperation()) {
				MathExpression expression = (MathExpression) stack.get(i);
				if(expression instanceof MethodExpression) {
					stack.add(i, expression.calc(((MethodContainerExpression)stack.get(i + 1)).getArray()));
					stack.remove(i + 1, i + 2);
				}else if(expression.isParanthesed()) {
					if(expression.indexes() == null) {
						stack.add(i, expression.calc(((NumberExpression)stack.get(i + 1))));
						stack.remove(i + 1, i + 2);
					}
				}else if(expression.indexes() == null) {
					stack.add(i, expression.calc(null));
					stack.remove(i + 1, i + 1);
				}else if(expression.indexes().length == 1) {
				    NumberExpression[] input = stack.getByIndexes(expression.indexes(), i);
                    stack.remove(i - 1, i);
				    stack.add(i - 1, expression.calc(input));
                }
			}
		}
		
		for(int i = 0; i < stack.getExpressions().size(); i++) {
			if(stack.get(i) instanceof MathExpression && !((MathExpression)stack.get(i)).isBasicOperation()) {
				MathExpression expression = (MathExpression) stack.get(i);
				if(!expression.isParanthesed()) {
					NumberExpression[] array = stack.getByIndexes(expression.indexes(), i);
					stack.remove(i + min(expression.indexes()), i + max(expression.indexes()));
					stack.add(i + min(expression.indexes()), expression.calc(array));
				}
			}
		}
	}
	
	private int min(int[] array) {
		int low = Integer.MAX_VALUE;
		for(int i : array) {
			if(i < low) {
				low = i;
			}
		}
		
		return low;
	}
	
	private int max(int[] array) {
		int max = Integer.MIN_VALUE;
		for(int i : array) {
			if(i > max) {
				max = i;
			}
		}
		
		return max;
	}
	
	
	
	private void calcBasicOperations() {
		for(int i = 0; i < stack.getExpressions().size(); i++) {
			if(stack.get(i) instanceof MathExpression && ((MathExpression)stack.get(i)).isBasicOperation() && ((MathExpression)stack.get(i)).hasPriority()) {
				MathExpression expression = (MathExpression) stack.get(i);
				NumberExpression[] array = stack.getByIndexes(expression.indexes(), i);
				stack.remove(i - 1, i + 1);
				stack.add(i - 1, expression.calc(array));
				i--;
			}
		}
		
		for(int i = 0; i < stack.getExpressions().size(); i++) {
			if(stack.get(i) instanceof MathExpression && ((MathExpression)stack.get(i)).isBasicOperation() && !((MathExpression)stack.get(i)).hasPriority()) {
				MathExpression expression = (MathExpression) stack.get(i);
				NumberExpression[] array = stack.getByIndexes(expression.indexes(), i);
				stack.remove(i - 1, i + 1);
				stack.add(i - 1, expression.calc(array));
				i--;
			}
		}
	}
	
}
