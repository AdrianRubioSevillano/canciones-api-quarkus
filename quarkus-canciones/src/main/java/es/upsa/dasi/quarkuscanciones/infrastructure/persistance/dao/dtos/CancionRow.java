package es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.dtos;

import lombok.*;

import java.time.LocalDate;
@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor

public class CancionRow {
    private String id;
    private String titulo;
    private String artista;
    private String albukm;
    private int duracion;
    private LocalDate fechaEstreno;
}
