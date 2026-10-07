package es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao;

import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.repository.Repository;
import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.dtos.CancionRow;
import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.mappers.DaoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {



    Dao dao;
    DaoMapper daoMapper;

    @Inject
    public RepositoryImpl(Dao dao, DaoMapper daoMapper) {
        this.dao = dao;
        this.daoMapper = daoMapper;
    }

    @Override
    public List<Cancion> findAll() {
        return dao.findAll().stream()
                            .map(daoMapper::toCancion)
                            .toList();
    }

    @Override
    public Optional<Cancion> findById(String id) {
        return dao.findById(id).map(daoMapper::toCancion);
    }

    @Override
    public List<Cancion> findByIds(List<String> ids) {
        return dao.findByIds(ids).stream()
                                .map(daoMapper::toCancion)
                                .toList();
    }

    @Override
    public Cancion insert(Cancion cancion) {
        CancionRow cancionRow = daoMapper.toCancionRow(cancion);
        CancionRow newCancion = dao.insert(cancionRow);
        return daoMapper.toCancion(newCancion);
    }

    @Override
    public Cancion update(Cancion cancion) {
        CancionRow cancionRow = daoMapper.toCancionRow(cancion);
        Optional<CancionRow> optCancion = dao.update(cancionRow);
        if (optCancion.isEmpty()) throw new NotFoundException("No existe ninguna persona con ese %s".formatted(cancion.getId()));
        return daoMapper.toCancion(optCancion.get());
    }

    @Override
    public void deleteById(String id) {
        int i = dao.deleteById(id);
        if (i==0) throw new NotFoundException("No existe ninguna persona con ese %s".formatted(id));
    }

    @Override
    public List<Cancion> insertSeveral(List<Cancion> cancions) {

        List<CancionRow> cancionesRow = daoMapper.toCancionRow(cancions);
        List<CancionRow> newCancionRows = dao.insertSeveral(cancionesRow);
        return daoMapper.toCancion(newCancionRows);
    }
}
