package ec.edu.uce.web.resource;

import java.util.List;

import ec.edu.uce.application.service.ServicioAdicionalService;
import ec.edu.uce.domain.model.ServicioAdicional;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/servicio-adicional")
public class ServicioAdicionalResource {

    @Inject
    private ServicioAdicionalService servicioAdicionalService;

    @POST
    @Path("/guardar")
    public void guardar(ServicioAdicional servicioAdicional) {
        this.servicioAdicionalService.guardar(servicioAdicional);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(ServicioAdicional servicioAdicional, @PathParam("id") Integer id) {
        this.servicioAdicionalService.actualizar(id, servicioAdicional);
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.servicioAdicionalService.eliminar(id);
    }

    @POST
    @Path("/buscarPorId")
    public ServicioAdicional buscarPorId(@PathParam("id") Integer id) {     
        return this.servicioAdicionalService.buscarPorId(id);
    }

    @GET
    @Path("/buscarTodos")
    public List<ServicioAdicional> buscarTodos() {
        return this.servicioAdicionalService.buscarTodos();
    }

}
