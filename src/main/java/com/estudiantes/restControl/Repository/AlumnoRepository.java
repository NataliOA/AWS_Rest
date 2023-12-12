package com.estudiantes.restControl.Repository;

import com.estudiantes.restControl.dto.Model.Alumno;
import com.estudiantes.restControl.dto.Model.Sesion;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import com.estudiantes.restControl.dto.Connection.AlumnoTable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlumnoRepository {
    private final AlumnoTable alumnoT;
    private final SesionRepository sesionRep;
    private final BucketRepository bucketRep;
    private final NotificacionRepository snsRep;
    
    @Autowired
    public AlumnoRepository(AlumnoTable alumno,SesionRepository Rep, BucketRepository Rep2, NotificacionRepository Rep3){
        alumnoT = alumno;
        sesionRep = Rep;
        bucketRep = Rep2;
        snsRep = Rep3;
    }

    public List<Alumno> getAllAlumnos(){
        return alumnoT.findAll();
    }

    public Alumno getAlumnoById(int id){
        return alumnoT.findById(id);
    }

    public Alumno createAlumno(Alumno nuevo){
        return alumnoT.save(nuevo);
    }

    public Alumno actualizar(int id, Alumno alumnoAux){
        Alumno alumnoAEditar = getAlumnoById(id);

        if (alumnoAEditar != null) {
            alumnoAEditar.setNombres(alumnoAux.getNombres());
            alumnoAEditar.setApellidos(alumnoAux.getApellidos());
            alumnoAEditar.setMatricula(alumnoAux.getMatricula());
            alumnoAEditar.setPromedio(alumnoAux.getPromedio());
            alumnoT.save(alumnoAEditar);
            return alumnoAEditar;
        } 

        return null;
    }

    public Alumno deleteAlumno(int id){
        Alumno alumnoAEliminar = getAlumnoById(id);
        alumnoT.deleteById(id);
        return alumnoAEliminar;
    }

    public Sesion iniciarSesion(int id, String info){
        Alumno alumno = getAlumnoById(id);
        Sesion sesion = new Sesion();

        if(alumno != null){
            if(info.equals(alumno.getPassword())){
                String uuid = UUID.randomUUID().toString();
                sesion.setId(uuid);
                sesion.setAlumnoId(id);
                sesion.setActive(Boolean.TRUE);
                sesion.setFecha(System.currentTimeMillis());
                sesion.setSessionString(getRandomString(128));
                sesionRep.iniciarSesion(sesion);
                return sesion;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public Sesion verifSesion(String seString){
        Sesion sesion = sesionRep.getSesionByString(seString);
        if(sesion != null && sesion.getActive()){
            return sesion;
        }
        return null;
    }

    public Sesion cerrarSesion(String seString){
        Sesion sesion = sesionRep.getSesionByString(seString);
        if(sesion != null && sesion.getActive()){
            sesion.setActive(false);
            sesionRep.cerrarSesion(seString);
            return sesion;
        }
        return null;
    }

    private static String getRandomString(int length) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return  random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public Alumno subirFoto(int id, MultipartFile archivo){
        String nombre = archivo.getOriginalFilename();
        try {
            File tempFile = convertMultiPartToFile(archivo);
            Alumno alumnoAux = getAlumnoById(id);
            if (alumnoAux != null) {
                Alumno alumno = new Alumno();
                alumno.setNombres(alumnoAux.getNombres());
                alumno.setApellidos(alumnoAux.getApellidos());
                alumno.setMatricula(alumnoAux.getMatricula());
                alumno.setPromedio(alumnoAux.getPromedio());;
                alumno.setFotoPerfilUrl("https://s3.amazonaws.com/proyectoimgao.com/"+archivo);
                Alumno alumnoActualizado = alumnoT.save(alumno);
                bucketRep.uploadFileToS3( "proyectoimgao.com", nombre, tempFile);
                return alumnoActualizado;
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public Alumno enviarInfo(int id){
        Alumno alumnoAux = getAlumnoById(id);
        if(alumnoAux!= null){
            Alumno alumno = new Alumno();
            alumno.setNombres(alumnoAux.getNombres());
            alumno.setApellidos(alumnoAux.getApellidos());
            alumno.setMatricula(alumnoAux.getMatricula());
            alumno.setPromedio(alumnoAux.getPromedio());
            snsRep.enviarCorreo("A continuación se despliega la información del alumno "+alumno.getId()
            +"\nNombre(s): "+alumno.getNombres()
            +"\nApellidos: "+alumno.getApellidos(), "AWS - API Rest");
            return alumno;
        }else{
            return null;
        }
    }
}
