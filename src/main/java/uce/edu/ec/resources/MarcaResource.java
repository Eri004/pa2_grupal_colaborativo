package uce.edu.ec.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.ec.application.service.MarcaService;
import uce.edu.ec.domain.model.Marca;

@Path("/marcas")
public class MarcaResource {

    @Inject
    private MarcaService marcaService;
    
    @Path("/guardar")
    @POST
    public void guardar(Marca marca){
        this.marcaService.guardar(marca);
    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.marcaService.eliminar(id);
    }

    @Path("/actualizar/{id}")
    @PUT
    public void actualizar(Marca marca, @PathParam("id")Integer id){
        this.marcaService.actualizar(marca, id);
    }

    @Path("/buscarPorId/{id}")
    @GET
    public Marca buscarPorId(@PathParam("id") Integer id){
        return this.marcaService.buscarPorId(id);
    }


}
