package ec.edu.uce.web.service;

import ec.edu.uce.application.service.VehiculoService;
import ec.edu.uce.domain.model.Vehiculo;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/vehiculo")
public class VehiculoResource {

    @Inject
    private VehiculoService vs;

    @Path("/guardar")
    @POST
    public void guardar(Vehiculo c) {

        this.vs.guardar(c);

    }

    @Path("/actualizar/{id}")
    @PUT
    public void actualizar(@PathParam("id") Integer id, Vehiculo c) {

        this.vs.actualizar(id, c);

    }

    @Path("/porId/{id}")
    @GET
    public Vehiculo buscarPorId(@PathParam("id") Integer id) {

        return this.vs.buscarPorId(id);

    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id) {

        this.vs.eliminar(id);

    }

    @Path("/porPlaca/{placa}")
    @GET
    public Vehiculo buscarPorPlaca(@PathParam("placa") String placa) {
        return this.vs.buscarPorPlaca(placa);
    }

    @Path("/porPlacaPromesa/{placa}")
    @GET
    public Uni<Vehiculo> buscarPorPlacaPromesa(@PathParam("placa") String placa) {
        return this.vs.buscarPorPlacaPromesa(placa);
    }

}
