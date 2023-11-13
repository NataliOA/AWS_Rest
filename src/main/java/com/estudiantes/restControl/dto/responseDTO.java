package com.estudiantes.restControl.dto;
import lombok.*;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class responseDTO {
    private String mensaje;
    private Object data;
}
