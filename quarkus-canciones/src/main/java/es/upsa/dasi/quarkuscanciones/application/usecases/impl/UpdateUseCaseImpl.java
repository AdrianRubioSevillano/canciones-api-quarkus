package es.upsa.dasi.quarkuscanciones.application.usecases.impl;

import es.upsa.dasi.quarkuscanciones.application.usecases.UpdateUseCase;
import es.upsa.dasi.quarkuscanciones.application.usecases.mappers.UseCasesMapper;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.model.ReplaceCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateUseCaseImpl implements UpdateUseCase {

    Repository repository;
    UseCasesMapper mapper;

    @Inject
    public UpdateUseCaseImpl(Repository repository, UseCasesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Cancion update(ReplaceCancionCommand command, String id) {
        Cancion cancion = mapper.toCancion(command, id);
        return repository.update(cancion);
    }
}
