package com.xujn.offer.recursiondp;

/**
 * 数值的整数次方。
 */
public class PowerFunction {

    public double power(double base, int exponent) {
        long exp = exponent;
        if (exp < 0) {
            base = 1 / base;
            exp = -exp;
        }
        double result = 1.0;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result *= base;
            }
            base *= base;
            exp >>= 1;
        }
        return result;
    }
}
