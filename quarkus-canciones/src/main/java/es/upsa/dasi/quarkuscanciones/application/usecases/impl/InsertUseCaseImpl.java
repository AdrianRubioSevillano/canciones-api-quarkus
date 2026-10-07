package es.upsa.dasi.quarkuscanciones.application.usecases.impl;

import es.upsa.dasi.quarkuscanciones.application.usecases.InsertUseCase;
import es.upsa.dasi.quarkuscanciones.application.usecases.mappers.UseCasesMapper;
import es.upsa.dasi.quarkuscanciones.domain.model.AddCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class InsertUseCaseImpl implements InsertUseCase {


    Repository repository;
    UseCasesMapper mapper;

    @Inject
    public InsertUseCaseImpl(Repository repository, UseCasesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Cancion insert(AddCancionCommand command) {
        Cancion cancion = mapper.toCancion(command);
        return repository.insert(cancion);
    }
}
