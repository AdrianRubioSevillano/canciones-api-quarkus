package es.upsa.dasi.quarkuscanciones.application.usecases;

import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.model.ReplaceCancionCommand;

public interface UpdateUseCase {
    Cancion update(ReplaceCancionCommand command, String id);
}
