package es.upsa.dasi.quarkuscanciones.application.usecases;

import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.dtos.CancionRow;

import java.util.Optional;

public interface FindByIdUseCase {
    Optional<Cancion> execute(String id);
}
