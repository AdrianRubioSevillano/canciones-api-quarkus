package es.upsa.dasi.quarkuscanciones.application.usecases.mappers;

import es.upsa.dasi.quarkuscanciones.domain.model.AddCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.model.ReplaceCancionCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface UseCasesMapper {
    Cancion toCancion(AddCancionCommand command);

    @Mapping(target = "id", source = "id")
    @Mapping(target = ".", source = "command")
    Cancion toCancion(ReplaceCancionCommand command, String id);

    List<Cancion> toCancion(List<AddCancionCommand> command);

}
