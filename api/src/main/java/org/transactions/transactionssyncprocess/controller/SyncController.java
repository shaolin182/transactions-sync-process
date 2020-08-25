package org.transactions.transactionssyncprocess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.transactions.sync.ISyncService;

@RestController
public class SyncController {

    @Autowired
    ISyncService syncService;

    @PostMapping(value = "/sync")
    public ResponseEntity syncDatabase() {

        syncService.syncDatabase();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
