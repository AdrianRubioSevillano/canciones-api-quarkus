package es.upsa.dasi.quarkuscanciones.domain.repository;

import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.dtos.CancionRow;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<Cancion> findAll();
    Optional<Cancion> findById(String id);
    List<Cancion> findByIds(List<String> ids);
    Cancion insert(Cancion cancion);
    Cancion update(Cancion cancion);
    void deleteById(String id);
    List<Cancion> insertSeveral(List<Cancion> cancions);
}
