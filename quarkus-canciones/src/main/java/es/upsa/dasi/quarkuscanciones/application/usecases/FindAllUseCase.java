package es.upsa.dasi.quarkuscanciones.application.usecases;

import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public interface FindAllUseCase {
    List<Cancion> findAll();
}
