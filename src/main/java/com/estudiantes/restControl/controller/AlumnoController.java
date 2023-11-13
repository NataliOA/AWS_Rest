package com.estudiantes.restControl.controller;

import com.estudiantes.restControl.dto.AlumnoDTO;
import com.estudiantes.restControl.dto.responseDTO;
import com.estudiantes.restControl.dto.Model.Alumno;
import com.estudiantes.restControl.Repository.AlumnoRepository;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = {"*"})
public class AlumnoController {
    private final AlumnoRepository alumnoRep;

    public AlumnoController(AlumnoRepository alumnoRep){
        this.alumnoRep = alumnoRep;
    }

    @GetMapping("")
    @Operation(summary = "Obtener todos los alumnos")
    public ResponseEntity<List<AlumnoDTO>> getAllAlumnos() {
        List<AlumnoDTO> alumnos = this.alumnoRep.getAlumnos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un alumno por su id")
    public ResponseEntity<responseDTO> getAlumnoById(@PathVariable int id){
        AlumnoDTO Alumno = this.alumnoRep.getAlumnoById(id);
        responseDTO resp;
        if(Alumno!=null){
            resp = new responseDTO(null, Alumno);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else{
            resp = new responseDTO(alumnoRep.getMsg(), Alumno);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping
    @Operation(summary = "Agregar un alumno.")
    public ResponseEntity<responseDTO> createAlumno(@RequestBody Alumno AlumnoN){
        AlumnoDTO Alumno = this.alumnoRep.createAlumno(AlumnoN);
        responseDTO resp;
        if(Alumno!=null){
            resp = new responseDTO(null, Alumno);
            return new ResponseEntity<>(resp, HttpStatus.CREATED);
        }else{
            resp = new responseDTO(alumnoRep.getMsg(), Alumno);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un alumno por su id.")
    public ResponseEntity<responseDTO> editAlumnoByid(@RequestBody Alumno AlumnoE, @PathVariable int id){
        AlumnoDTO Alumno = this.alumnoRep.actualizar(id, AlumnoE);
        responseDTO resp;
        if(Alumno!=null){
            resp = new responseDTO(null, Alumno);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else{
            resp = new responseDTO(alumnoRep.getMsg(), Alumno);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un alumno por su id.")
    public ResponseEntity<responseDTO> delete(@PathVariable int id){
        AlumnoDTO AlumnoB = this.alumnoRep.deleteAlumno(id);
        responseDTO resp;
        if(AlumnoB!=null){
            resp = new responseDTO(null, AlumnoB);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else{
            resp = new responseDTO(alumnoRep.getMsg(), AlumnoB);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
    }

}
