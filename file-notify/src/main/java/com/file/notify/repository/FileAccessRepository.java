package com.file.notify.repository;

import com.file.notify.model.FileAccessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAccessRepository extends JpaRepository<FileAccessModel, Integer> {
}
