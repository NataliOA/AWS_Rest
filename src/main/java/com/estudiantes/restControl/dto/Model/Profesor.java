package com.estudiantes.restControl.dto.Model;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="profesor")
public class Profesor {
    @Id
    @Positive(message = "El id debe ser positivo.")
    @GeneratedValue(str)
    private int id;

    @Positive(message = "noEmpleado debe ser un número positivo.")
    private int numeroEmpleado;

    @NotBlank
    @NotNull
    private String nombres;

    @NotBlank
    @NotNull
    private String apellidos;

    @Positive(message="horasClase debe ser un número positivo.")
    private int horasClase;
}
