package com.santiago.webRosa.Repository;

import com.santiago.webRosa.Entities.PageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<PageText, Integer> {
}
