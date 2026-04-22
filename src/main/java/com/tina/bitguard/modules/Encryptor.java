package com.tina.bitguard.modules;

public class Encryptor {
    
    public static String xorEncrypt(String data, char key)
    {
        StringBuilder result = new StringBuilder();

        for(char c: data.toCharArray())
        {
            result.append((char)(c^key));
        }
        return result.toString();
    }
}
