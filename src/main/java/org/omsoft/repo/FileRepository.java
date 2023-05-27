package org.omsoft.repo;

import org.omsoft.entity.FileStorage;
import org.omsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository  extends JpaRepository<FileStorage,Integer> {

}
