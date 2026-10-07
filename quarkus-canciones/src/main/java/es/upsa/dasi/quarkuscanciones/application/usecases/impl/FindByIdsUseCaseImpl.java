package es.upsa.dasi.quarkuscanciones.application.usecases.impl;

import es.upsa.dasi.quarkuscanciones.application.usecases.FindByIdsUseCase;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FindByIdsUseCaseImpl implements FindByIdsUseCase {

    @Inject
    Repository repository;

    @Override
    public List<Cancion> execute(List<String> ids) {
        return repository.findByIds(ids);
    }
}
