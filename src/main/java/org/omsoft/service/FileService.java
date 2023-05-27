package org.omsoft.service;

import org.omsoft.entity.FileStorage;
import org.omsoft.repo.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    FileRepository fileRepo;

    public FileStorage save(FileStorage fileStorage){
       return fileRepo.save(fileStorage);
    }


}
