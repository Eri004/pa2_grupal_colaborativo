package uce.edu.grupal.web.resources;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.grupal.application.service.VehiculoService;
import uce.edu.grupal.domain.model.Vehiculo;

@Path("/vehiculos")
public class VehiculoResource {

    @Inject
    private VehiculoService vs;

    @GET
    public List<Vehiculo> obtenerTodos() {
        return this.vs.buscarTodos();
    }

    @GET
    @Path("/{placa}")
    public Vehiculo obtenerPorPlaca(@PathParam("placa") String placa) {
        return this.vs.buscarPorPlaca(placa);
    }

    @POST
    public void guardar(Vehiculo vehiculo) {
        this.vs.guardar(vehiculo);
    }

    @PUT
    public void actualizar(Vehiculo vehiculo) {
        this.vs.actualizar(vehiculo);
    }

    @DELETE
    @Path("/{placa}")
    public void eliminar(@PathParam("placa") String placa) {
        this.vs.eliminar(placa);
    }
}