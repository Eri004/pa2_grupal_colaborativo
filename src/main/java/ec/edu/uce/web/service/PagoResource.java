package ec.edu.uce.web.service;

import ec.edu.uce.application.service.PagoService;
import ec.edu.uce.domain.model.Pago;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/pago")
public class PagoResource {

    @Inject
    private PagoService ps;

    @Path("/guardar")
    @POST
    public void guardar(Pago c){

        this.ps.guardar(c);

    }

    @Path("/actualizar/{id}")
    @PUT
    public void actualizar(@PathParam("id") Integer id, Pago p){

        this.ps.actualizar(id, p);

    }

    @Path("/porId/{id}")
    @GET
    public Pago buscarPorId(@PathParam("id") Integer id){

        return this.ps.buscarPorId(id);

    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){

        this.ps.eliminar(id);

    }

}
