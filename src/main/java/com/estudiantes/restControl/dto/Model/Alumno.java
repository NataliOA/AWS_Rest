package com.estudiantes.restControl.dto.Model;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Alumno {
    @Positive(message = "El id debe ser positivo")
    private int id;

    @NotBlank
    @NotNull
    private String nombres;

    @NotBlank
    @NotNull
    private String apellidos;

    @Pattern(regexp = "^[a-zA-Z]+\\d+$", message = "La matrícula debe comenzar con letras seguidas de al menos un número")
    private String matricula;

    @DecimalMin(value = "0.00", message = "El promedio debe ser mayor o igual a 1")
    @DecimalMax(value = "100.00", message = "El promedio deber ser menor a 100")
    private double promedio;
}
