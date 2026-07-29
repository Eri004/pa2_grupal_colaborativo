package ec.edu.uce.web.service;

import ec.edu.uce.application.service.VendedorService;
import ec.edu.uce.domain.model.Vendedor;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/vendedor")
public class VendedorResource {

    @Inject
    private VendedorService vs;

    @Path("/guardar")
    @POST
    public void guardar(Vendedor v){

        this.vs.guardar(v);

    }

    @Path("/actualizar/{id}")
    @PUT
    public void actualizar(@PathParam("id") Integer id, Vendedor v){

        this.vs.actualizar(id, v);

    }

    @Path("/porId/{id}")
    @GET
    public Vendedor buscarPorId(@PathParam("id") Integer id){

        return this.vs.buscarPorId(id);

    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){

        this.vs.eliminar(id);

    }

    @Path("/porCedula/{cedula}")
    @GET
    public Vendedor buscarPorCedula(@PathParam("cedula") String cedula) {
        return this.vs.buscarPorCedula(cedula);
    }

    @Path("/porCedulaPromesa/{cedula}")
    @GET
    public Uni<Vendedor> buscarPorCedulaPromesa(@PathParam("cedula") String cedula) {
        return this.vs.buscarPorCedulaPromesa(cedula);
    }

}
