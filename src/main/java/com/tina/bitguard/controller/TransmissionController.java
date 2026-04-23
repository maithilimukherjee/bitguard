package com.tina.bitguard.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tina.bitguard.model.RequestDTO;
import com.tina.bitguard.service.TransmissionService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://bitguard.vercel.app")

public class TransmissionController {

    @Autowired

    private TransmissionService service;

    @PostMapping("/transmit")
public ResponseEntity<?> transmit(@RequestBody RequestDTO request) {
    return ResponseEntity.ok(service.process(request));
}
}

