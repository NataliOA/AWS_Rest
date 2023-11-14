package com.estudiantes.restControl.dto.Model;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Profesor {
    private int id;

    @Positive(message = "noEmpleado debe ser un número positivo")
    private int noEmpleado;

    @NotBlank
    @NotNull
    private String nombres;

    @NotBlank
    @NotNull
    private String apellidos;

    @Positive(message="horasClase debe ser un número positivo")
    private int horasClase;
}
