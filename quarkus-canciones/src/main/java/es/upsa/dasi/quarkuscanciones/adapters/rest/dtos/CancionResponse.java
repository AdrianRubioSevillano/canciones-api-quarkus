package es.upsa.dasi.quarkuscanciones.adapters.rest.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor

public class CancionResponse {
    private String titulo;
    private String artista;
    private int duracion;
}
