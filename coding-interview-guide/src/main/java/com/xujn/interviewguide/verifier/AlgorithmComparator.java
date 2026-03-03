package com.xujn.interviewguide.verifier;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 对数器：随机生成样本，对比暴力解与正式解。
 */
public class AlgorithmComparator {

    public static <T, R> void verify(Supplier<T> caseSupplier,
                                     Function<T, R> bruteForce,
                                     Function<T, R> optimized,
                                     int times) {
        for (int i = 0; i < times; i++) {
            T sample = caseSupplier.get();
            R expected = bruteForce.apply(sample);
            R actual = optimized.apply(sample);
            if (!expected.equals(actual)) {
                throw new IllegalStateException("Mismatch detected. expected=" + expected + ", actual=" + actual);
            }
        }
    }
}
