package es.upsa.dasi.quarkuscanciones.application.usecases;

import es.upsa.dasi.quarkuscanciones.domain.model.AddCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;

import java.util.List;

public interface InsertSeveralUseCase {
    List<Cancion> insertSeveral(List<AddCancionCommand> cancions);
}
