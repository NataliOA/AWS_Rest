package com.estudiantes.restControl.dto.Connection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estudiantes.restControl.dto.Model.Profesor;
import jakarta.validation.constraints.NotNull;

@Repository
public interface ProfesorTable extends JpaRepository<Profesor, Integer> {
    @NotNull
    List<Profesor> findAll();
    Profesor findById(int id);
    Profesor save(Profesor id);
    Profesor deleteById(int id);
}
