package es.upsa.dasi.quarkuscanciones.domain.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor

public class AddCancionCommand {
    private String titulo;
    private String artista;
    private String albukm;
    private int duracion;
    private LocalDate fechaEstreno;
}
