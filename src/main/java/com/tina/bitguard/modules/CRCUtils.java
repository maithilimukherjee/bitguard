package com.tina.bitguard.modules;

public class CRCUtils {

    // simple generator: "1011" (x^3 + x + 1)
    private static final String GENERATOR = "1011";

    public static String generateCRC(String data) {

        String padded = data + "000"; // degree = 3 (GENERATOR length - 1)
        char[] dividend = padded.toCharArray();
        char[] divisor = GENERATOR.toCharArray();

        for (int i = 0; i <= dividend.length - GENERATOR.length(); i++) {

            if (dividend[i] == '1') {
                for (int j = 0; j < GENERATOR.length(); j++) {
                    dividend[i + j] =
                            (dividend[i + j] == divisor[j]) ? '0' : '1';
                }
            }
        }

        StringBuilder crc = new StringBuilder();
        for (int i = dividend.length - 3; i < dividend.length; i++) {
            crc.append(dividend[i]);
        }

        return crc.toString();
    }

    public static boolean checkCRC(String dataWithCRC) {

        char[] bits = dataWithCRC.toCharArray();
        char[] divisor = GENERATOR.toCharArray();

        for (int i = 0; i <= bits.length - GENERATOR.length(); i++) {

            if (bits[i] == '1') {
                for (int j = 0; j < GENERATOR.length(); j++) {
                    bits[i + j] =
                            (bits[i + j] == divisor[j]) ? '0' : '1';
                }
            }
        }

        for (int i = bits.length - 3; i < bits.length; i++) {
            if (bits[i] == '1') return false;
        }

        return true;
    }
}