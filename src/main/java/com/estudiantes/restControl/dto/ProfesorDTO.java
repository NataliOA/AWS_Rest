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

    @NotBlank
    @NotNull
    private int numeroEmpleado;

    @NotBlank
    @NotNull
    private String nombres;

    @NotBlank
    @NotNull
    private String apellidos;

    @NotBlank
    @NotNull
    private int horasClase;
}
