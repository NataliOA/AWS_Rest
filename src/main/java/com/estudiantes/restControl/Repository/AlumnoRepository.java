package com.estudiantes.restControl.Repository;

import com.estudiantes.restControl.dto.AlumnoDTO;
import com.estudiantes.restControl.dto.Model.Alumno;

import lombok.Getter;

import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Getter
public class AlumnoRepository {
    private List<AlumnoDTO> alumnos = new ArrayList<>();
    String msg;

    public AlumnoRepository() {
        this.alumnos = new ArrayList<>();
        
        //this.alumnos.add(new AlumnoDTO(0,"Andrea Natalí", "Ortega Aguilar", "a1060", 92.0));
    }

    public AlumnoDTO getAlumnoById(int id){
        for (AlumnoDTO alumno : alumnos) {
            if (alumno.getId() == id) {
                return alumno;
            }
        }
        msg = "No se encontró el id solicitado";
        return null;
    }

    public AlumnoDTO createAlumno(Alumno nuevo){
        msg = new String();
        AlumnoDTO AlumnoN = new AlumnoDTO(nuevo.getId(),nuevo.getNombres(), nuevo.getApellidos(), nuevo.getMatricula(), nuevo.getPromedio());
        alumnos.add(AlumnoN);
        return AlumnoN;
    }

    public AlumnoDTO actualizar(int id, Alumno alumnoAux){
        AlumnoDTO alumnoAEditar = null;
        alumnoAEditar = getAlumnoById(id);

        if (alumnoAEditar != null) {
            msg = new String();
            alumnoAEditar.setId(alumnoAux.getId());
            alumnoAEditar.setNombres(alumnoAux.getNombres());
            alumnoAEditar.setApellidos(alumnoAux.getApellidos());
            alumnoAEditar.setMatricula(alumnoAux.getMatricula());
            alumnoAEditar.setPromedio(alumnoAux.getPromedio());
            alumnos.set(id,alumnoAEditar);
            return alumnoAEditar;
        } else {
            msg = "No se encontró el id solicitado.";
        }

        return null;
    }

    public AlumnoDTO deleteAlumno(int id){
        AlumnoDTO alumnoAEliminar = null;
        alumnoAEliminar = getAlumnoById(id);

        if (alumnoAEliminar != null) {
            alumnos.remove(alumnoAEliminar);
        } else {
            msg = "No se encontró el id solicitado.";
        }

        return alumnoAEliminar;
    }

}
