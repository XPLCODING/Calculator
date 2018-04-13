package me.micha.calculator2.calculation.expression;

import java.util.ArrayList;
import java.util.List;

import me.micha.calculator2.calculation.expression.expressions.AcosinusExpression;
import me.micha.calculator2.calculation.expression.expressions.AsinusExpression;
import me.micha.calculator2.calculation.expression.expressions.AtangensExpression;
import me.micha.calculator2.calculation.expression.expressions.CosinusExpression;
import me.micha.calculator2.calculation.expression.expressions.DivisionExpression;
import me.micha.calculator2.calculation.expression.expressions.EulerExpression;
import me.micha.calculator2.calculation.expression.expressions.FacultyExpression;
import me.micha.calculator2.calculation.expression.expressions.KommaExpression;
import me.micha.calculator2.calculation.expression.expressions.LeftParantheseExpression;
import me.micha.calculator2.calculation.expression.expressions.LogarithmExpression;
import me.micha.calculator2.calculation.expression.expressions.MultiplicationExpression;
import me.micha.calculator2.calculation.expression.expressions.NaturalLogarithmExpression;
import me.micha.calculator2.calculation.expression.expressions.PiExpression;
import me.micha.calculator2.calculation.expression.expressions.PowerExpression;
import me.micha.calculator2.calculation.expression.expressions.RightParantheseExpression;
import me.micha.calculator2.calculation.expression.expressions.SinusExpression;
import me.micha.calculator2.calculation.expression.expressions.SquareRootExpression;
import me.micha.calculator2.calculation.expression.expressions.SquaredExpression;
import me.micha.calculator2.calculation.expression.expressions.SubtractionExpression;
import me.micha.calculator2.calculation.expression.expressions.SummationExpression;
import me.micha.calculator2.calculation.expression.expressions.TangensExpression;
import me.micha.calculator2.calculation.expression.methods.BinomialCoefficientMethod;


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
    	new PiExpression();
    	new EulerExpression();
    	new FacultyExpression();
    	new SquaredExpression();
    	new SquareRootExpression();
    	new LogarithmExpression();
    	new NaturalLogarithmExpression();
    	new SubtractionExpression();
    	new SummationExpression();
    	new BinomialCoefficientMethod();
    	new KommaExpression();
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
