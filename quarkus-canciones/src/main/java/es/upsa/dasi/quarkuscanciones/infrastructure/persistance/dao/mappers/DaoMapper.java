package es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.mappers;

import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.infrastructure.persistance.dao.dtos.CancionRow;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface DaoMapper {
    Cancion toCancion(CancionRow cancionRow);
    CancionRow toCancionRow(Cancion cancion);

    List<Cancion> toCancion(List<CancionRow> cancionRow);
    List<CancionRow> toCancionRow(List<Cancion> cancion);
}
