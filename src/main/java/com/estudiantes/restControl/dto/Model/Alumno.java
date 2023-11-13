package com.estudiantes.restControl.dto.Model;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Alumno {
    private int id;
    private String nombres;
    private String apellidos;
    private int matricula;
    private double promedio;
}
