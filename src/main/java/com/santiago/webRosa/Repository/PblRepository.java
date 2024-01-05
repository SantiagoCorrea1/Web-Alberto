package com.santiago.webRosa.Repository;

import com.santiago.webRosa.Entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PblRepository extends JpaRepository<Publication, Integer> {

}
