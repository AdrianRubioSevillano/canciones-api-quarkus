package es.upsa.dasi.quarkuscanciones.application.usecases.impl;

import es.upsa.dasi.quarkuscanciones.application.usecases.FindAllUseCase;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class FindAllUseCaseImpl implements FindAllUseCase {

    @Inject
    Repository repository;

    @Override
    public List<Cancion> findAll() {
        return repository.findAll();
    }
}
