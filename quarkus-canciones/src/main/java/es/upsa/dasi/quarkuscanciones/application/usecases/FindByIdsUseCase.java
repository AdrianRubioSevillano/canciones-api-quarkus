package es.upsa.dasi.quarkuscanciones.application.usecases;

import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;

import java.util.List;

public interface FindByIdsUseCase {
    List<Cancion> execute(List<String> ids);
}
