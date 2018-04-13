package me.micha.calculator2.calculation;

import me.micha.calculator2.calculation.calc.Calculation;
import me.micha.calculator2.calculation.calc.CalculationParser;
import me.micha.calculator2.calculation.history.History;
import me.micha.calculator2.calculation.history.HistoryEntry;
import me.micha.calculator2.file.DataStore;

/**
 * Created by micha on 08.03.2018.
 */

public class Calculator {

    @DataStore(keyName = "history", priority = 3)
    private static History history = new History();

    public static void calc(String toCalc) {
        history.add(new HistoryEntry(toCalc, HistoryEntry.Type.EXERCISE));
        CalculationParser calculationParser = new CalculationParser(toCalc);
        calculationParser.parse();
        Calculation calculation = new Calculation(calculationParser.getOutput());
        history.add(new HistoryEntry("" + calculation.getResult(), HistoryEntry.Type.RESULT));

    }

    public static double round(double value, int places) {
        if (places < 0 || places > 16) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double singleCalc(String toCalc) {
        CalculationParser calculationParser = new CalculationParser(toCalc);
        calculationParser.parse();
        return new Calculation(calculationParser.getOutput()).getResult();
    }

    public static History getHistory() {
        return history;
    }
}

