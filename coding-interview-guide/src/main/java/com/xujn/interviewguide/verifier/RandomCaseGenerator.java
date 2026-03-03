package com.xujn.interviewguide.verifier;

import java.util.Random;

/**
 * 对数器用的随机样本生成器。
 */
public class RandomCaseGenerator {

    private final Random random;

    public RandomCaseGenerator(long seed) {
        this.random = new Random(seed);
    }

    public int[] nextIntArray(int maxLength, int minValue, int maxValue) {
        int length = random.nextInt(maxLength + 1);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = minValue + random.nextInt(maxValue - minValue + 1);
        }
        return arr;
    }

    public int nextInt(int minValue, int maxValue) {
        return minValue + random.nextInt(maxValue - minValue + 1);
    }

    public String nextLowercaseString(int maxLength) {
        int length = random.nextInt(maxLength + 1);
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append((char) ('a' + random.nextInt(5)));
        }
        return builder.toString();
    }
}
