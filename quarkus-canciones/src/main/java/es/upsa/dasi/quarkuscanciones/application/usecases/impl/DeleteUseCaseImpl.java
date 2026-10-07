package es.upsa.dasi.quarkuscanciones.application.usecases.impl;

import es.upsa.dasi.quarkuscanciones.application.usecases.DeleteUseCase;
import es.upsa.dasi.quarkuscanciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteUseCaseImpl implements DeleteUseCase {

    @Inject
    Repository repository;

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
