package uce.edu.ec.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.ec.application.service.VendedorService;

import uce.edu.ec.domain.model.Vendedor;

@Path("vendedores")
public class VendedorResource {

    @Inject
    private VendedorService vendedorService;

    @Path("/guardar")
    @POST
    public void guardar(Vendedor vendedor){
        this.vendedorService.guardar(vendedor);;
    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id")Integer id){
        this.vendedorService.eliminar(id);
    }

    @Path("/actualizar/{cedula}")
    @PUT
    public void actualizar(Vendedor vendedor, @PathParam("cedula")String cedula){
        this.vendedorService.actualizar(vendedor, cedula);
    }

    @Path("/buscarPorCedula/{cedula}")
    @GET
    public Vendedor buscarPorCedula(@PathParam("cedula") String cedula){
        return this.vendedorService.buscarPorCedula(cedula);
    }

}
