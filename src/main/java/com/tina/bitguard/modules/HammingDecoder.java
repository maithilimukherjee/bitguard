package com.tina.bitguard.modules;

public class HammingDecoder {
    
     public static String decodeAndCorrect(String block)
     {
        int[] h = new int[7];

        for(int i=0; i<7; i++)
        {
            h[i]=block.charAt(i)-'0';
        }

        //parity checks

        int p1 = h[0]^h[2]^h[4]^h[6];
        int p2 = h[1]^h[2]^h[5]^h[6];
        int p3 = h[3]^h[4]^h[5]^h[6];

        int errorPosition = p1*1 + p2*2 + p3*4;

        if(errorPosition != 0)
        {
            //flip the erroneous bit
            h[errorPosition-1] ^= 1;
        }

        return "" + h[2] + h[4] + h[5] + h[6];
        
     }
}
