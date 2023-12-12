package com.estudiantes.restControl.Repository;

import com.estudiantes.restControl.dto.Model.Alumno;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.*;
import com.estudiantes.restControl.dto.Connection.AlumnoTable;

@Service
public class AlumnoRepository {
    private final AlumnoTable alumnoT;
    
    @Autowired
    public AlumnoRepository(AlumnoTable alumno){
        alumnoT = alumno;
    }

    public List<Alumno> getAllAlumnos(){
        return alumnoT.findAll();
    }

    public Alumno getAlumnoById(int id){
        return alumnoT.findById(id)
                      .orElse(null);
    }

    public void createAlumno(Alumno nuevo){
        alumnoT.save(Alumno);
    }

    public Alumno actualizar(int id, Alumno alumnoAux){
        Alumno alumnoAEditar = getAlumnoById(id);

        if (alumnoAEditar != null) {
            msg = new String();
            alumnoAEditar.setNombres(alumnoAux.getNombres());
            alumnoAEditar.setApellidos(alumnoAux.getApellidos());
            alumnoAEditar.setMatricula(alumnoAux.getMatricula());
            alumnoAEditar.setPromedio(alumnoAux.getPromedio());
            alumnoT.save(alumnoAEditar);
            return alumnoAEditar;
        } else {
            msg = "No se encontró el id solicitado.";
        }

        return null;
    }

    public Alumno deleteAlumno(int id){
        alumnoT.deleteById(id);
    }

}
