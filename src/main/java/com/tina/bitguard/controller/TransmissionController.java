package com.tina.bitguard.controller;
 
import com.tina.bitguard.model.RequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin

public class TransmissionController {

    @Autowired

    private TransmissionService service;

    @PostMapping("/transmit")
    public String transmit(@RequestBody RequestDTO request)
    {
        return service.process(request);
    }
}
