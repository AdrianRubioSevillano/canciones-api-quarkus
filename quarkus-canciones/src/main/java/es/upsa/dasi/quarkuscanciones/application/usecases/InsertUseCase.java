package es.upsa.dasi.quarkuscanciones.application.usecases;

import es.upsa.dasi.quarkuscanciones.domain.model.AddCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;

public interface InsertUseCase {
    Cancion insert(AddCancionCommand command);
}
