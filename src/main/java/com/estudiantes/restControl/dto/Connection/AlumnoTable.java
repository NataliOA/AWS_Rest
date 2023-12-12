package com.estudiantes.restControl.dto.Connection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estudiantes.restControl.dto.Model.Alumno;
import jakarta.validation.constraints.NotNull;


@Repository
public interface AlumnoTable extends JpaRepository<Alumno,Integer>{
    @NotNull
    List<Alumno> findAll();
    Alumno findById(int id);
    Alumno save(Alumno id);
    Alumno deleteById(int id);
} 