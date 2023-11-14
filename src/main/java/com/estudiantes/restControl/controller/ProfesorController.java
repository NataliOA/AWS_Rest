package com.estudiantes.restControl.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.estudiantes.restControl.Repository.ProfesorRepository;
import com.estudiantes.restControl.dto.ProfesorDTO;
import com.estudiantes.restControl.dto.Model.Profesor;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

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
        List<ProfesorDTO> profs = this.profesorRep.getProfs();
        return new ResponseEntity<>(profs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un profesor por su id.")
    public ResponseEntity<ProfesorDTO> getProfById(@PathVariable int id){
        ProfesorDTO Prof = this.profesorRep.getProfById(id);
        if(Prof != null){
            return new ResponseEntity<>(Prof, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Prof, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping
    @Operation(summary = "Agregar un profesor.")
    public ResponseEntity<ProfesorDTO> createProf(@Valid @RequestBody Profesor ProfesorN){
        ProfesorDTO Profesor = this.profesorRep.createProf(ProfesorN);
        return new ResponseEntity<>(Profesor, HttpStatus.CREATED);
        
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un profesor por su id.")
    public ResponseEntity<ProfesorDTO> editProfByid(@Valid @RequestBody Profesor ProfE, @PathVariable int id){
        ProfesorDTO Profesor = this.profesorRep.actualizar(id, ProfE);
        if(Profesor != null){
            return new ResponseEntity<>(Profesor, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Profesor, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un profesor por su id.")
    public ResponseEntity<ProfesorDTO> delete(@PathVariable int id){
        ProfesorDTO ProfesorB = this.profesorRep.deleteProf(id);
        if(ProfesorB != null){
            return new ResponseEntity<>(ProfesorB, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(ProfesorB, HttpStatus.NOT_FOUND);
        }
    }
}

