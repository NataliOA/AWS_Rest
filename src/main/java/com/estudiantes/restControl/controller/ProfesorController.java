package com.estudiantes.restControl.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudiantes.restControl.Repository.ProfesorRepository;
import com.estudiantes.restControl.dto.ProfesorDTO;
import com.estudiantes.restControl.dto.Model.Profesor;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/profesores")
@CrossOrigin(origins = {"*"})
public class ProfesorController {
    private final ProfesorRepository profesorRep;

    public ProfesorController(ProfesorRepository profRep){
        this.profesorRep = profRep;
    }

    @GetMapping("")
    @Operation(summary = "Obtener todos los profesores.")
    public ResponseEntity<List<ProfesorDTO>> getAllAlumnos() {
        List<ProfesorDTO> profs = this.profesorRep.getAllProfs();
        return new ResponseEntity<>(profs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un profesor por su id.")
    public ResponseEntity<ProfesorDTO> getProfById(@PathVariable int id){
        ProfesorDTO Prof = this.profesorRep.getProfById(id);
        return new ResponseEntity<>(Prof, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Agregar un profesor.")
    public ResponseEntity<ProfesorDTO> createProf(@RequestBody Profesor ProfesorN){
        ProfesorDTO Profesor = this.profesorRep.createProf(ProfesorN);
        return new ResponseEntity<>(Profesor, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un profesor por su id.")
    public ResponseEntity<ProfesorDTO> editProfByid(@RequestBody Profesor ProfE, @PathVariable int id){
        ProfesorDTO Profesor = this.profesorRep.actualizar(id, ProfE);
        return new ResponseEntity<>(Profesor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un profesor por su id.")
    public ResponseEntity<ProfesorDTO> delete(@PathVariable int id){
        ProfesorDTO ProfesorB = this.profesorRep.deleteProfesor(id);
        return new ResponseEntity<>(ProfesorB, HttpStatus.OK);
    }
}

