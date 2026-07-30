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

    @Path("/eliminarPorPlaca/{placa}")
    @DELETE
    public void eliminarPorPlaca(@PathParam("placa") String placa) {
        this.vs.eliminarPorPlaca(placa);
    }

    @Path("/buscarPorPlaca/{placa}")
    @GET
    public Vehiculo buscarPorPlaca(@PathParam("placa") String placa) {
        return this.vs.buscarPorPlaca(placa);
    }

    @Path("/actualizarPorPlaca/{placa}")
    @PUT
    public void actualizarPorPlaca(@PathParam("placa") String placa, Vehiculo c) {
        this.vs.actualizarPorPlaca(placa, c);

    }

    @Path("/buscarTodos")
    @GET
    public List<Vehiculo> buscarTodos(){
        return this.vs.buscarTodos();
    }

    
}