package es.upsa.dasi.quarkuscanciones.adapters.rest;

import es.upsa.dasi.quarkuscanciones.adapters.rest.dtos.*;
import es.upsa.dasi.quarkuscanciones.adapters.rest.mappers.ResourceMapper;
import es.upsa.dasi.quarkuscanciones.application.usecases.*;
import es.upsa.dasi.quarkuscanciones.domain.model.AddCancionCommand;
import es.upsa.dasi.quarkuscanciones.domain.model.Cancion;
import es.upsa.dasi.quarkuscanciones.domain.model.ReplaceCancionCommand;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Path("/canciones")
public class CancionesResource {

    FindAllUseCase findAllUseCase;
    FindByIdUseCase findByIdUseCase;
    FindByIdsUseCase findByIdsUseCase;
    InsertUseCase insertUseCase;
    UpdateUseCase updateUseCase;
    DeleteUseCase deleteUseCase;
    InsertSeveralUseCase insertSeveralUseCase;

    ResourceMapper resourceMapper;

    @Inject
    public CancionesResource(FindAllUseCase findAllUseCase, FindByIdUseCase findByIdUseCase, FindByIdsUseCase findByIdsUseCase, InsertUseCase insertUseCase, UpdateUseCase updateUseCase, DeleteUseCase deleteUseCase, InsertSeveralUseCase insertSeveralUseCase, ResourceMapper resourceMapper) {
        this.findAllUseCase = findAllUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findByIdsUseCase = findByIdsUseCase;
        this.insertUseCase = insertUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.insertSeveralUseCase = insertSeveralUseCase;
        this.resourceMapper = resourceMapper;
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findAll() {
//
//        List<CancionResponse> canciones = findAllUseCase.findAll().stream()
//                                                            .map(resourceMapper::toCancionResponse)
//                                                            .toList();
//        return Response.ok()
//                .entity(canciones)
//                .build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCanciones(@DefaultValue("")@QueryParam("id") Ids ids){

        List<Cancion> canciones = ids.isEmpty()? findAllUseCase.findAll() : findByIdsUseCase.execute(ids.getIds());
        List<CancionResponse> list = canciones.stream().map(resourceMapper::toCancionResponse).toList();

        return Response.ok()
                .entity(list)
                .build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id, @Context UriInfo uriInfo) {

        Optional<Cancion> optCancion = findByIdUseCase.execute(id);
        return optCancion.map(cancion -> resourceMapper.toCancionFullResponse(cancion, uriInfo))
                                                                .map(cancionFullResponse -> Response.ok()
                                                                        .entity(cancionFullResponse)
                                                                        .build())
                                                                .orElseGet(()->Response.status(Response.Status.NOT_FOUND).build());
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(CancionPostRequest cancionPostRequest, @Context UriInfo uriInfo) {

        AddCancionCommand command = resourceMapper.toAddCancionCommand(cancionPostRequest);
        Cancion cancion = insertUseCase.insert(command);
        CancionFullResponse cancionFullResponse = resourceMapper.toCancionFullResponse(cancion, uriInfo);

        return Response.created(cancionFullResponse.getUri()).entity(cancionFullResponse).build();

    }


    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(CancionPutRequest cancionPutRequest, @PathParam("id") String id){

        ReplaceCancionCommand command = resourceMapper.toReplaceCancionCommand(cancionPutRequest);
        Cancion cancion = updateUseCase.update(command, id);
        CancionResponse cancionResponse = resourceMapper.toCancionResponse(cancion);

        return Response.ok()
                .entity(cancionResponse)
                .build();

    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") String id){

        deleteUseCase.deleteById(id);

        return Response.noContent().build();

    }

    @POST
    @Path("several")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertSeveral(List<CancionPostRequest> cancions, @Context UriInfo uriInfo){

        List<AddCancionCommand> addCancionCommand = resourceMapper.toAddCancionCommand(cancions);
        List<Cancion> canciones = insertSeveralUseCase.insertSeveral(addCancionCommand);
        List<CancionResponse> cancionResponse = resourceMapper.toCancionResponse(canciones);

        return Response.ok()
                .entity(cancionResponse)
                .build();

    }
}
