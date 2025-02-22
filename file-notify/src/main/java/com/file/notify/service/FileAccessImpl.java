package com.file.notify.service;


import com.file.notify.interfaces.FileAccess;
import com.file.notify.model.FileAccessModel;
import com.file.notify.repository.FileAccessRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileAccessImpl implements FileAccess {

    @Autowired
    FileAccessRepository fileAccessRepository;

    @Transactional
    public String insertFilePermission()
    {
        log.info("IN SERVICE LAYER....");
        FileAccessModel fileAccessModel = new FileAccessModel();
        fileAccessModel.setFileName("xyz.xml");
        fileAccessModel.setAccessLevel("W");
        fileAccessRepository.save(fileAccessModel);
        return "Record saved..";
    }
}
