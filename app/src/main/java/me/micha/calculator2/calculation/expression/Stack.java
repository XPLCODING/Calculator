package me.micha.calculator2.calculation.expression;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.calculation.expression.expressions.LeftParantheseExpression;
import me.micha.calculator2.calculation.expression.expressions.NumberExpression;
import me.micha.calculator2.calculation.expression.expressions.RightParantheseExpression;


public class Stack {

	private List<Expression> expressions = new ArrayList<>();
	private int start, end;
	
	public Stack(List<Expression> expressions) {
		this.expressions = expressions;
	}
	
	public List<Expression> getExpressions() {
		return expressions;
	}
	
	public int getStartParantheses() {
		return start;
	}
	
	public int getEndPrantheses() {
		return end;
	}
	
	public boolean isEmpty() {
		return expressions.isEmpty();
	}
	
	public void remove(int from, int to) {
		int t = to - from + 1;
		for(int i = 0; i < t; i++) {
			expressions.remove(from);
		}
	}
	
	public void add(int i, Expression expression) {
		expressions.add(i, expression);
	}

	public Expression get(int i) {
		return expressions.get(i);
	}
	
	public Expression[] get(int[] i) {
		Expression[] array = new Expression[i.length];
		int c = 0;
		for(int index : i) {
			array[c] = get(index);
			c++;
		}
		
		return array;
	}
	
	public NumberExpression[] getByIndexes(int[] indexes, int i) {
		NumberExpression[] array = new NumberExpression[indexes.length];
		int c = 0;
		for(int index : indexes) {
			array[c] = (NumberExpression) get(i + index);
			c++;
		}
		
		return array;
	}
	
	public boolean hasNextParanthese() {
		for(Expression expression : expressions) {
			if(expression instanceof LeftParantheseExpression) {
				return true;
			}
		}
		
		return false;
	}
	
	public Stack firstParantheseSection() {
		List<Expression> list = new ArrayList<>();
		start = -1;
		end = -1;
		int bracketUnbalancing = 0;
		for(int i = 0; i < expressions.size(); i++) {
			if(start != -1) {
				if(expressions.get(i) instanceof LeftParantheseExpression) {
					bracketUnbalancing += 1;
				}else if(expressions.get(i) instanceof RightParantheseExpression) {
					if(bracketUnbalancing == 0) {
						end = i;
						break;
					}
					bracketUnbalancing -= 1;
				}
				list.add(expressions.get(i));
			}else {
				if(expressions.get(i) instanceof LeftParantheseExpression) {
					start = i;
				}
			}
		}
		
		return new Stack(list);
	}
	
	public String toString() {
		String res = "";
		for(Expression expression : expressions) {
			if(expression instanceof MathExpression) {
				res += ((MathExpression)expression).getSymbol();
			}else {
				res += ((NumberExpression)expression).getValue();
			}
		}
		
		return res;
	}
	
}
