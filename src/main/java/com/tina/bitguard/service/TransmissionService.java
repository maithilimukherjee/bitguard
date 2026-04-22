package com.tina.bitguard.service;

import com.tina.bitguard.model.RequestDTO;
import com.tina.bitguard.modules.Encryptor;
import com.tina.bitguard.modules.BinaryUtils;
import com.tina.bitguard.modules.HammingService;
import org.springframework.stereotype.Service;

@Service
public class TransmissionService {
    
    public String process(RequestDTO request)
    {
        String original = request.getMessage();
        String encrypted = Encryptor.xorEncrypt(original, 'K');

        String binary = BinaryUtils.toBinary(encrypted);
        String hamming = applyHamming(binary);

        return "original: " + original + "\n" +
       "encrypted: " + encrypted + "\n" +
       "binary: " + binary + "\n" +
       "hamming: " + hamming;
    }

    private String applyHamming(String binary)
    {
        StringBuilder hammingEncoded = new StringBuilder();

        binary = binary.replace(" ", ""); // Remove spaces if any

        for(int i=0; i<binary.length(); i+=4)
        {
            String chunk = binary.substring(i, Math.min(i+4, binary.length()));
            if(chunk.length() < 4)
            continue;
            
            hammingEncoded.append(HammingService.encode4Bits(chunk)).append(" ");
            
            }
        
        return hammingEncoded.toString().trim();
    }
}
