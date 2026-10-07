package es.upsa.dasi.quarkuscanciones.adapters.rest.dtos;

import jakarta.json.bind.annotation.JsonbTransient;
import lombok.*;
import java.net.URI;
import java.time.LocalDate;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor

public class CancionFullResponse {
    private String id;
    private String titulo;
    private String artista;
    private String albukm;
    private int duracion;
    private LocalDate fechaEstreno;

    @JsonbTransient
    private URI uri;
}
