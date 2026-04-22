package com.tina.bitguard.service;

import com.tina.bitguard.model.RequestDTO;
import com.tina.bitguard.modules.Encryptor;
import com.tina.bitguard.modules.BinaryUtils;
import org.springframework.stereotype.Service;

@Service
public class TransmissionService {
    
    public String process(RequestDTO request)
    {
        String original = request.getMessage();
        String encrypted = Encryptor.xorEncrypt(original, 'K');

        String binary = BinaryUtils.toBinary(encrypted);
        return "original: " + original + ", encrypted: " + encrypted + ", binary: " + binary;
    }
}
