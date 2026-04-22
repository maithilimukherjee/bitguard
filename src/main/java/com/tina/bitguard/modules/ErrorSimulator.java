package com.tina.bitguard.modules;

import java.util.Random;

public class ErrorSimulator {

    private static final Random random = new Random();

    // error probability between 0 and 1
    public static String injectErrors(String hammingData, double probability) {

        StringBuilder corrupted = new StringBuilder();

        for (char bit : hammingData.toCharArray()) {

            if (bit == ' ') {
                corrupted.append(" ");
                continue;
            }

            double chance = random.nextDouble();

            if (chance < probability) {
                // flip bit
                corrupted.append(bit == '0' ? '1' : '0');
            } else {
                corrupted.append(bit);
            }
        }

        return corrupted.toString();
    }
}