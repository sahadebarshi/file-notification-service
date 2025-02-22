package com.file.notify.controller;

import com.file.notify.constants.SuccessResponse;
import com.file.notify.interfaces.FileAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileAccessController {

    @Autowired
    FileAccess fileAccess;

    @GetMapping(path = "/save-permission")
    public ResponseEntity<SuccessResponse> insertFilePermission()
    {
        fileAccess.insertFilePermission();
        return new ResponseEntity<>(new SuccessResponse("Record Saved", 200), HttpStatus.OK);
    }

}
