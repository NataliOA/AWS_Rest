package com.estudiantes.restControl.controller;

import com.estudiantes.restControl.dto.AlumnoDTO;
import com.estudiantes.restControl.dto.Model.Alumno;
import com.estudiantes.restControl.Repository.AlumnoRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

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
    public ResponseEntity<AlumnoDTO> getAlumnoById(@PathVariable int id){
        AlumnoDTO Alumno = this.alumnoRep.getAlumnoById(id);
        if(Alumno!=null){
            return new ResponseEntity<>(Alumno, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Alumno, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping
    @Operation(summary = "Agregar un alumno.")
    public ResponseEntity<AlumnoDTO> createAlumno(@Valid @RequestBody Alumno AlumnoN){
        AlumnoDTO Alumno = this.alumnoRep.createAlumno(AlumnoN);
        return new ResponseEntity<>(Alumno, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un alumno por su id.")
    public ResponseEntity<AlumnoDTO> editAlumnoByid(@Valid @RequestBody Alumno AlumnoE, @PathVariable int id){
        AlumnoDTO Alumno = this.alumnoRep.actualizar(id, AlumnoE);
        if(Alumno!=null){
            return new ResponseEntity<>(Alumno, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Alumno, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un alumno por su id.")
    public ResponseEntity<AlumnoDTO> delete(@PathVariable int id){
        AlumnoDTO AlumnoB = this.alumnoRep.deleteAlumno(id);
        if(AlumnoB!=null){
            return new ResponseEntity<>(AlumnoB, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(AlumnoB, HttpStatus.NOT_FOUND);
        }
    }

}
