package me.micha.calculator2.calculation.expression;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.calculation.expression.expressions.AcosinusExpression;
import me.micha.calculator2.calculation.expression.expressions.AsinusExpression;
import me.micha.calculator2.calculation.expression.expressions.AtangensExpression;
import me.micha.calculator2.calculation.expression.expressions.CosinusExpression;
import me.micha.calculator2.calculation.expression.expressions.DivisionExpression;
import me.micha.calculator2.calculation.expression.expressions.LeftParantheseExpression;
import me.micha.calculator2.calculation.expression.expressions.LogarithmExpression;
import me.micha.calculator2.calculation.expression.expressions.MultiplicationExpression;
import me.micha.calculator2.calculation.expression.expressions.PowerExpression;
import me.micha.calculator2.calculation.expression.expressions.RightParantheseExpression;
import me.micha.calculator2.calculation.expression.expressions.SinusExpression;
import me.micha.calculator2.calculation.expression.expressions.SubtractionExpression;
import me.micha.calculator2.calculation.expression.expressions.SummationExpression;
import me.micha.calculator2.calculation.expression.expressions.TangensExpression;


/**
 * Created by micha on 13.03.2018.
 */

public class ExpressionManager {

    private static List<MathExpression> mathExpressions = new ArrayList<>();
    
    public static void load() {
    	new PowerExpression();
    	new SinusExpression();
    	new AsinusExpression();
    	new CosinusExpression();
    	new AcosinusExpression();
    	new TangensExpression();
    	new AtangensExpression();
    	new LogarithmExpression();
    	new SubtractionExpression();
    	new SummationExpression();
    	new MultiplicationExpression();
    	new DivisionExpression();
    	new LeftParantheseExpression();
    	new RightParantheseExpression();
    }
    
    public static void add(MathExpression mathExpression) {
        mathExpressions.add(mathExpression);
    }

    public static List<MathExpression> getMathExpressions() {
        return mathExpressions;
    }
}
