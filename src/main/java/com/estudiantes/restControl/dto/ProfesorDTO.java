package com.estudiantes.restControl.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProfesorDTO {
    private int id;

    private int numeroEmpleado;

    private String nombres;

    private String apellidos;

    private int horasClase;
}
