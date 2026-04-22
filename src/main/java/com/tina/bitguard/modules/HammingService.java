package com.tina.bitguard.modules;

public class HammingService {

    public static String encode4Bits(String data)
    {
        int[] hamming = new int[7];

        //place data bits

        hamming[2]=data.charAt(0)-'0';
        hamming[4]=data.charAt(1)-'0';
        hamming[5]=data.charAt(2)-'0';
        hamming[6]=data.charAt(3)-'0';  

        //parity bits

        hamming[0]=hamming[2]^hamming[4]^hamming[6]; //p1
        hamming[1]=hamming[2]^hamming[5]^hamming[6]; //p2
        hamming[3]=hamming[4]^hamming[5]^hamming[6]; //p3

        StringBuilder result = new StringBuilder();

        for(int bit: hamming)
        {
            result.append(bit);
        }
        return result.toString();
    }
    
}
