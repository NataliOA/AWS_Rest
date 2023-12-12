package com.estudiantes.restControl.dto.Connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estudiantes.restControl.dto.Model.Profesor;

@Repository
public interface ProfesorTable extends JpaRepository<Profesor, Integer> {
    
}
