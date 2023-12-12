package com.estudiantes.restControl.controller;

import com.estudiantes.restControl.dto.Model.Alumno;
import com.estudiantes.restControl.dto.Model.Sesion;
import com.estudiantes.restControl.Repository.AlumnoRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = {"*"})
public class AlumnoController {
    private final AlumnoRepository alumnoRep;

    @Autowired
    public AlumnoController(AlumnoRepository alumnoRep){
        this.alumnoRep = alumnoRep;
    }

    @GetMapping("")
    @Operation(summary = "Obtener todos los alumnos")
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        List<Alumno> alumnos = this.alumnoRep.getAllAlumnos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un alumno por su id")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable int id){
        Alumno Alumno = this.alumnoRep.getAlumnoById(id);
        if(Alumno!=null){
            return new ResponseEntity<>(Alumno, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Alumno, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping
    @Operation(summary = "Agregar un alumno.")
    public ResponseEntity<Alumno> createAlumno(@Valid @RequestBody Alumno AlumnoN){
        Alumno Alumno = this.alumnoRep.createAlumno(AlumnoN);
        return new ResponseEntity<>(Alumno, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un alumno por su id.")
    public ResponseEntity<Alumno> editAlumnoByid(@Valid @RequestBody Alumno AlumnoE, @PathVariable int id){
        Alumno Alumno = this.alumnoRep.actualizar(id, AlumnoE);
        if(Alumno!=null){
            return new ResponseEntity<>(Alumno, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Alumno, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un alumno por su id.")
    public ResponseEntity<Alumno> delete(@PathVariable int id){
        Alumno AlumnoB = this.alumnoRep.deleteAlumno(id);
        if(AlumnoB!=null){
            return new ResponseEntity<>(AlumnoB, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(AlumnoB, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/fotoPerfil")
    @Operation(summary = "Agregar una foto de perfil al alumno.")
    public ResponseEntity<Alumno> addFoto(@RequestPart("foto") MultipartFile foto, @PathVariable int id){
        Alumno AlumnoB = this.alumnoRep.subirFoto(id, foto);
        if(AlumnoB!=null){
            return new ResponseEntity<>(AlumnoB, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(AlumnoB, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/email")
    @Operation(summary = "Enviar un correo con la info del alumno.")
    public ResponseEntity<?> sendInfo(@PathVariable int id){
        Alumno AlumnoB = this.alumnoRep.enviarInfo(id);
        if(AlumnoB!=null){
            return new ResponseEntity<>(AlumnoB, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(AlumnoB, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/session/login")
    @Operation(summary = "Iniciar sesión en un alumno")
    public ResponseEntity<?> iniciarSesion(@PathVariable int id, @RequestBody Map<String, Object> usuario){
        String contra = (String) usuario.get("password");
        Sesion sesion = this.alumnoRep.iniciarSesion(id, contra);
        if(sesion != null){
            return new ResponseEntity<>(sesion, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/session/verify")
    @Operation(summary = "Verificar la sesión")
    public ResponseEntity<?> verify(@PathVariable int id, @RequestBody Sesion seString){
        Sesion sesion = this.alumnoRep.verifSesion(seString.getSessionString());
        if(sesion != null){
            return new ResponseEntity<>(sesion, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(seString,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/session/logout")
    @Operation(summary = "Cerrar sesion del alumno activo")
    public ResponseEntity<?> logOut(@PathVariable int id, @RequestBody Sesion seString){
        Sesion sesion = this.alumnoRep.cerrarSesion(seString.getSessionString());
        if(sesion != null){
            return new ResponseEntity<>(sesion, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(seString, HttpStatus.BAD_REQUEST);
        }
    }


}
