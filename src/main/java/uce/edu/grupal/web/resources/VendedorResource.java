package uce.edu.grupal.web.resources;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.grupal.application.service.VendedorService;
import uce.edu.grupal.domain.model.Vendedor;

@Path("/vendedor")
public class VendedorResource {


    @Inject
    private VendedorService vs;

    @Path("/guardar")
    @POST
    public void guardar(Vendedor v){
        this.vs.guardar(v);

    }

    @Path("/porId/{id}")
    @GET
    public Vendedor buscarPorId(@PathParam("id") Integer id){

        return this.vs.buscarPorId(id);

    }

    @Path("/actualizar/{id}")
    @PUT
    public void actualizar(@PathParam("id") Integer id, Vendedor v){
        //Requiere enviar el json completo del objeto Vendedor
        this.vs.actualizar(id, v);

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

    @Path("/buscarTodos")
    @GET
    public List<Vendedor> buscarTodos(){
        return this.vs.buscarTodos();
    }

}