package org.transactions.transactionssyncprocess.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncController {

    @PostMapping(value = "/sync")
    public ResponseEntity syncDatabase() {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
