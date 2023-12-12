package com.estudiantes.restControl.dto.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="alumno")
public class Alumno {

    @Id
    @Positive(message = "El id debe ser positivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String fotoPerfilUrl;

    private String password;
}
