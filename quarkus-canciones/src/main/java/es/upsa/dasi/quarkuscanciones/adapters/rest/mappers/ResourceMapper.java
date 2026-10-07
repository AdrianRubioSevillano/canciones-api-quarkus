package es.upsa.dasi.quarkuscanciones.adapters.rest.mappers;

import es.upsa.dasi.quarkuscanciones.adapters.rest.dtos.CancionFullResponse;
import es.upsa.dasi.quarkuscanciones.adapters.rest.dtos.CancionPostRequest;
import es.upsa.dasi.quarkuscanciones.adapters.rest.dtos.CancionPutRequest;
import es.upsa.dasi.quarkuscanciones.adapters.rest.dtos.CancionResponse;
import es.upsa.dasi.quarkuscanciones.domain.model.AddCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.model.ReplaceCancionCommand;
import jakarta.ws.rs.core.UriInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.net.URI;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface ResourceMapper {
    CancionResponse toCancionResponse(Cancion cancion);
    List<CancionResponse> toCancionResponse(List<Cancion> cancion);

    @Mapping(target = "uri", expression = "java(createUri(cancion, uriInfo))")
    CancionFullResponse toCancionFullResponse(Cancion cancion, UriInfo uriInfo);
    AddCancionCommand toAddCancionCommand(CancionPostRequest cancion);
    List<AddCancionCommand> toAddCancionCommand(List<CancionPostRequest> cancion);
    ReplaceCancionCommand toReplaceCancionCommand(CancionPutRequest cancion);

    default URI createUri(Cancion cancion, UriInfo uriInfo){

        return uriInfo.getBaseUriBuilder()
                .path("/canciones/{id}")
                .resolveTemplate("id", cancion.getId())
                .build();
    }
}
