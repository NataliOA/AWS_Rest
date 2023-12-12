package com.estudiantes.restControl.Repository;

import com.estudiantes.restControl.dto.Model.Profesor;
import com.estudiantes.restControl.dto.Connection.ProfesorTable;
import lombok.Getter;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ProfesorRepository {
    private final ProfesorTable profesorT;

    @Autowired
    public ProfesorRepository(ProfesorTable profesor) {
        profesorT = profesor;
    }

    public List<Profesor> getAllProfesores(){
        return profesorT.findAll();
    }

    public Profesor getProfById(int id){
        return profesorT.findById(id)
                        .orElse(null);
    }

    public void createProf(Profesor nuevo){
        profesorT.save(nuevo);
    }

    public void actualizar(int id, Profesor profAux){
        Profesor profAEditar = getProfById(id);

        if (profAEditar != null) {
            msg = new String();
            profAEditar.setNumeroEmpleado(profAux.getNumeroEmpleado());
            profAEditar.setNombres(profAux.getNombres());
            profAEditar.setApellidos(profAux.getApellidos());
            profAEditar.setHorasClase(profAux.getHorasClase());
            profesorT.save(profAEditar);
            return profAEditar;
        } else {
            msg = "No se encontró el id solicitado.";
        }
        
        return null;
    }

    public void deleteProf(int id){
        profesorT.deleteById(id);
    }
}
