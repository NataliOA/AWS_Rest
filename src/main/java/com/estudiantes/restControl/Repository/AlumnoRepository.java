package com.estudiantes.restControl.Repository;

import com.estudiantes.restControl.dto.AlumnoDTO;
import com.estudiantes.restControl.dto.Model.Alumno;

import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class AlumnoRepository {
    private List<AlumnoDTO> alumnos = new ArrayList<>();

    public AlumnoRepository() {
        this.alumnos = new ArrayList<>();
        
        this.alumnos.add(new AlumnoDTO(0,"Andrea Natalí", "Ortega Aguilar", 1060, 92.0));
    }

    public List<AlumnoDTO> getAlumnos() {
        return alumnos;
    }

    public AlumnoDTO getAlumnoById(int id){
        for (AlumnoDTO alumno : alumnos) {
            if (alumno.getId() == id) {
                return alumno;
            }
        }
        return null;
    }

    public AlumnoDTO createAlumno(Alumno nuevo){
        AlumnoDTO AlumnoN = new AlumnoDTO(nuevo.getId(), nuevo.getNombres(), nuevo.getApellidos(), nuevo.getMatricula(), nuevo.getPromedio());

        alumnos.add(AlumnoN);
        return AlumnoN;
    }

    public AlumnoDTO actualizar(int id, Alumno alumnoAux){
        Optional<AlumnoDTO> alumnoExistente = alumnos.stream().filter(a -> a.getId() == id).findFirst();
        alumnoExistente.ifPresent(value -> {
            value.setNombres(alumnoAux.getNombres());
            value.setApellidos(alumnoAux.getApellidos());
            value.setMatricula(alumnoAux.getMatricula());
            value.setPromedio(alumnoAux.getPromedio());
        });
        return null;
    }

    public AlumnoDTO deleteAlumno(int id){
        AlumnoDTO alumnoAEliminar = null;
        for (AlumnoDTO alumno : alumnos) {
            if (alumno.getId() == id) {
                alumnoAEliminar = alumno;
                break; // Salir del bucle una vez que se encuentre el alumno con el ID buscado
            }
        }

        if (alumnoAEliminar != null) {
            alumnos.remove(alumnoAEliminar);
            System.out.println("Alumno con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún alumno con el ID: " + id);
        }

        return alumnoAEliminar;
    }

}
