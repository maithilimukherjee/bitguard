package com.tina.bitguard.modules;

public class BinaryUtils {
    
    public static String toBinary(String input)
    {
        StringBuilder binary = new StringBuilder();
        for(char c: input.toCharArray())
        {
            String binString = Integer.toBinaryString(c);
            binary.append(String.format("%8s", binString).replace(' ', '0'));
        }
        return binary.toString();
    }
}
