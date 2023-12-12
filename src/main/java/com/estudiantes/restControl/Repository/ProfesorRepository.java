package com.estudiantes.restControl.Repository;

import com.estudiantes.restControl.dto.Model.Profesor;
import com.estudiantes.restControl.dto.Connection.ProfesorTable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        return profesorT.findById(id);
    }

    public Profesor createProf(Profesor nuevo){
        profesorT.save(nuevo);
        return nuevo;
    }

    public Profesor actualizar(int id, Profesor profAux){
        Profesor profAEditar = getProfById(id);

        if (profAEditar != null) {
            profAEditar.setNumeroEmpleado(profAux.getNumeroEmpleado());
            profAEditar.setNombres(profAux.getNombres());
            profAEditar.setApellidos(profAux.getApellidos());
            profAEditar.setHorasClase(profAux.getHorasClase());
            profesorT.save(profAEditar);
            return profAEditar;
        }
        
        return null;
    }

    public Profesor deleteProf(int id){
        Profesor profAEliminar = getProfById(id);
        profesorT.deleteById(id);
        return profAEliminar;
    }
}
