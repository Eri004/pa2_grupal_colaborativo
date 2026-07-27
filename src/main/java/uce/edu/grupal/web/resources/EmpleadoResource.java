package uce.edu.grupal.web.resources;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.grupal.application.service.EmpleadoService;
import uce.edu.grupal.domain.model.Empleado;

@Path("/empleados")
public class EmpleadoResource {

    @Inject
    private EmpleadoService es;

    @GET
    public List<Empleado> obtenerTodos() {
        return this.es.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public Empleado obtenerPorId(@PathParam("id") Integer id) {
        return this.es.buscarPorId(id);
    }

    @POST
    public void guardar(Empleado empleado) {
        this.es.guardar(empleado);
    }

    @PUT
    public void actualizar(Empleado empleado) {
        this.es.actualizar(empleado);
    }

    @DELETE
    @Path("/{cedula}")
    public void eliminar(@PathParam("cedula") String cedula) {
        this.es.eliminar(cedula);
    }
}