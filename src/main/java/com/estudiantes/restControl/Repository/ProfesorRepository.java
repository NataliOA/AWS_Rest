package com.estudiantes.restControl.Repository;

import com.estudiantes.restControl.dto.ProfesorDTO;
import com.estudiantes.restControl.dto.Model.Profesor;

import lombok.Getter;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ProfesorRepository {
    private List<ProfesorDTO> profs = new ArrayList<>();
    String msg;

    public ProfesorRepository() {
        this.profs = new ArrayList<>();
        
        //this.profs.add(new ProfesorDTO(0, 101, "Eduardo", "Rodriguez", 4));
    }

    public ProfesorDTO getProfById(int id){
        for (ProfesorDTO profesor : profs) {
            if (profesor.getId() == id) {
                return profesor;
            }
        }
        msg = "No se encontró el id solicitado";
        return null;
    }

    public ProfesorDTO createProf(Profesor nuevo){
        msg = new String();
        ProfesorDTO profesorN = new ProfesorDTO(nuevo.getId(), nuevo.getNoEmpleado(),nuevo.getNombres(), nuevo.getApellidos(),nuevo.getHorasClase());
        profs.add(profesorN);
        return profesorN;
    }

    public ProfesorDTO actualizar(int id, Profesor profAux){
        ProfesorDTO profAEditar = null;
        profAEditar = getProfById(id);

        if (profAEditar != null) {
            msg = new String();
            profAEditar.setId(profAux.getId());
            profAEditar.setNumeroEmpleado(profAux.getNoEmpleado());
            profAEditar.setNombres(profAux.getNombres());
            profAEditar.setApellidos(profAux.getApellidos());
            profAEditar.setHorasClase(profAux.getHorasClase());
            profs.set(id,profAEditar);
            return profAEditar;
        } else {
            msg = "No se encontró el id solicitado.";
        }
        
        return null;
    }

    public ProfesorDTO deleteProf(int id){
        ProfesorDTO profAEliminar = null;
        profAEliminar = getProfById(id);

        if (profAEliminar != null) {
            profs.remove(profAEliminar);
        } else {
            msg = "No se encontró el id solicitado.";
        }

        return profAEliminar;
    }
}
