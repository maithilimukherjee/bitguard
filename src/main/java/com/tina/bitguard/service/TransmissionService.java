package com.tina.bitguard.service;

import com.tina.bitguard.model.RequestDTO;
import org.springframework.stereotype.Service;

@Service
public class TransmissionService {
    
    public String process(RequestDTO request)
    {
        return "processed: "+request.getMessage()+" | error probability: "+request.getErrorProbability();
    }
}
