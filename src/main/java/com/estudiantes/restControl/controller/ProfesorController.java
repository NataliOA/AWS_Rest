package com.estudiantes.restControl.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.estudiantes.restControl.Repository.ProfesorRepository;
import com.estudiantes.restControl.dto.ProfesorDTO;
import com.estudiantes.restControl.dto.responseDTO;
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
    public ResponseEntity<responseDTO> getProfById(@PathVariable int id){
        ProfesorDTO Prof = this.profesorRep.getProfById(id);
        responseDTO resp;
        if(Prof != null){
            resp = new responseDTO(null, Prof);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else{
            resp = new responseDTO(profesorRep.getMsg(), Prof);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping
    @Operation(summary = "Agregar un profesor.")
    public ResponseEntity<responseDTO> createProf(@Valid @RequestBody Profesor ProfesorN){
        ProfesorDTO Profesor = this.profesorRep.createProf(ProfesorN);
        responseDTO resp = new responseDTO(null, Profesor);
            return new ResponseEntity<>(resp, HttpStatus.CREATED);
        
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un profesor por su id.")
    public ResponseEntity<responseDTO> editProfByid(@Valid @RequestBody Profesor ProfE, @PathVariable int id){
        ProfesorDTO Profesor = this.profesorRep.actualizar(id, ProfE);
        responseDTO resp;
        if(Profesor != null){
            resp = new responseDTO(null, Profesor);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else{
            resp = new responseDTO(profesorRep.getMsg(), Profesor);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un profesor por su id.")
    public ResponseEntity<responseDTO> delete(@PathVariable int id){
        ProfesorDTO ProfesorB = this.profesorRep.deleteProf(id);
        responseDTO resp;
        if(ProfesorB != null){
            resp = new responseDTO(null, ProfesorB);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }
        else{
            resp = new responseDTO(profesorRep.getMsg(), ProfesorB);
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
    }
}

