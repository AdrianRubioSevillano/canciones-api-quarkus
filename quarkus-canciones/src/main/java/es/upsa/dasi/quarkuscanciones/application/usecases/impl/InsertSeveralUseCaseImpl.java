package es.upsa.dasi.quarkuscanciones.application.usecases.impl;

import es.upsa.dasi.quarkuscanciones.application.usecases.InsertSeveralUseCase;
import es.upsa.dasi.quarkuscanciones.application.usecases.mappers.UseCasesMapper;
import es.upsa.dasi.quarkuscanciones.domain.model.AddCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class InsertSeveralUseCaseImpl implements InsertSeveralUseCase {

    Repository repository;
    UseCasesMapper mapper;

    @Inject
    public InsertSeveralUseCaseImpl(Repository repository, UseCasesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Cancion> insertSeveral(List<AddCancionCommand> commands) {
        List<Cancion> cancion = mapper.toCancion(commands);
        return repository.insertSeveral(cancion);
    }
}
