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
        
        this.alumnos.add(new AlumnoDTO(0,"Andrea Natalí", "Ortega Aguilar", 1060, 92.0));
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
        if (verifInfo(nuevo)){
            AlumnoDTO AlumnoN = new AlumnoDTO(alumnos.size(),nuevo.getNombres(), nuevo.getApellidos(), nuevo.getMatricula(), nuevo.getPromedio());
            alumnos.add(AlumnoN);
            return AlumnoN;
        }else{
            return null;
        }
    }

    private boolean verifInfo(Alumno info){
        boolean verificado = true;
        if((info.getNombres().isEmpty()||info.getNombres()==null)||!validarString(info.getNombres())){
            verificado = false;
            msg += "Los nombres deben ser cadena de texto y no estar vacíos. ";
        }
        if((info.getApellidos().isEmpty()||info.getApellidos()==null)||!validarString(info.getApellidos())){
            verificado = false;
            msg += "Los apellidos deben ser cadena de texto y no estar vacíos. ";
        }
        if(info.getMatricula()<=0){
            verificado = false;
            msg += "La matrícula debe ser un valor mayor a 0. ";
        }
        if(info.getPromedio()<0.00||info.getPromedio()>100.00){
            verificado = false;
            msg += "El promedio debe ser un valor entre 0.00 y 100.00. ";
        }
        return verificado;
    }

    private boolean validarString(String str){
        try {
            Integer.parseInt(str);
            return false;
        } catch (Exception e) {
            return !str.matches(".*\\d+.*");
        }
    }

    public AlumnoDTO actualizar(int id, Alumno alumnoAux){
        AlumnoDTO alumnoAEditar = null;
        alumnoAEditar = getAlumnoById(id);

        if (alumnoAEditar != null) {
            msg = new String();
            if(verifInfo(alumnoAux)){
                alumnoAEditar.setNombres(alumnoAux.getNombres());
                alumnoAEditar.setApellidos(alumnoAux.getApellidos());
                alumnoAEditar.setMatricula(alumnoAux.getMatricula());
                alumnoAEditar.setPromedio(alumnoAux.getPromedio());
                alumnos.set(id,alumnoAEditar);
                return alumnoAEditar;
            }
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
