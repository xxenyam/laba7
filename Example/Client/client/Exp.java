package client;

import compute.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Exp implements Task<BigDecimal> {

    private BigDecimal accuracy;
    private BigDecimal value;


    public Exp(double accuracy, double value) {
        this.value = BigDecimal.valueOf(value);
        this.accuracy = BigDecimal.valueOf(accuracy);
    }

    @Override
    public BigDecimal execute() {
        BigDecimal exp = BigDecimal.ZERO;
        BigDecimal index = BigDecimal.ZERO;
        while (true) {
            BigDecimal tmp = calculate(index, value);
            if (tmp.compareTo(accuracy) < 0) {
                break;
            }
            exp = exp.add(tmp);
            System.out.println(exp);
            index = index.add(BigDecimal.ONE);
        }

        return exp;
    }

    private BigDecimal calculate(BigDecimal n, BigDecimal x) {
        BigDecimal fct = BigDecimal.ONE;
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(n) <= 0; i = i.add(BigDecimal.ONE)) {
            fct = fct.multiply(i);
        }

        for (BigDecimal i = BigDecimal.ONE; i.compareTo(n) <= 0; i = i.add(BigDecimal.ONE)) {
            x = x.multiply(x);
        }

        return x.divide(fct, 99, RoundingMode.HALF_UP);
    }

}