package es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao;

import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.dtos.CancionRow;

import java.util.List;
import java.util.Optional;

public interface Dao {
    List<CancionRow> findAll();
    Optional<CancionRow> findById(String id);
    List<CancionRow> findByIds(List<String> ids);
    CancionRow insert(CancionRow cancionRow);
    Optional<CancionRow> update(CancionRow cancionRow);
    int deleteById(String id);
    List<CancionRow> insertSeveral(List<CancionRow> cancionRows);
}
