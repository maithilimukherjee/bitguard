package com.tina.bitguard.service;

import com.tina.bitguard.model.RequestDTO;
import com.tina.bitguard.modules.Encryptor;
import com.tina.bitguard.modules.BinaryUtils;
import com.tina.bitguard.modules.HammingService;
import com.tina.bitguard.modules.ErrorSimulator;
import com.tina.bitguard.modules.HammingDecoder;
import com.tina.bitguard.modules.CRCUtils;
import org.springframework.stereotype.Service;

@Service
public class TransmissionService {

    public String process(RequestDTO request) {

        String original = request.getMessage();

        // step 1: encryption
        String encrypted = Encryptor.xorEncrypt(original, 'K');

        // step 2: binary conversion
        String binary = BinaryUtils.toBinary(encrypted);

        // step 3: hamming encoding
        String hamming = applyHamming(binary);

        String crc = CRCUtils.generateCRC(hamming.replace(" ", ""));
String crcAppended = hamming.replace(" ", "") + crc;

        // step 4: channel noise (ONLY ONCE)
        String received = ErrorSimulator.injectErrors(crcAppended, request.getErrorProbability());
        boolean isValid = CRCUtils.checkCRC(received);

        // step 5: receiver decoding + correction
        String correctedBinary = decodeHamming(received);

        return "original: " + original + "\n" +
       "encrypted: " + encrypted + "\n" +
       "binary: " + binary + "\n" +
       "hamming: " + hamming + "\n" +
       "crc: " + crc + "\n" +
       "received: " + received + "\n" +
       "crc valid: " + isValid + "\n" +
       "corrected binary: " + correctedBinary;
    }

    private String applyHamming(String binary) {

        StringBuilder hammingEncoded = new StringBuilder();

        binary = binary.replace(" ", "");

        for (int i = 0; i < binary.length(); i += 4) {

            String chunk = binary.substring(i, Math.min(i + 4, binary.length()));

            if (chunk.length() < 4) continue;

            hammingEncoded
                    .append(HammingService.encode4Bits(chunk))
                    .append(" ");
        }

        return hammingEncoded.toString().trim();
    }

    private String decodeHamming(String received) {

        StringBuilder binary = new StringBuilder();

        received = received.replace(" ", "");

        for (int i = 0; i < received.length(); i += 7) {

            String block = received.substring(i, Math.min(i + 7, received.length()));

            if (block.length() < 7) continue;

            binary.append(HammingDecoder.decodeAndCorrect(block));
        }

        return binary.toString();
    }
}