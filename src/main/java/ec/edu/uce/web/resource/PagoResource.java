package ec.edu.uce.web.resource;

import java.util.List;

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
    private PagoService pagoService;

    @Path("/todos")
    @GET
    public List<Pago> buscarTodos(){
        return this.pagoService.buscarTodos();
    }

    @Path("/porId/{id}")
    @GET
    public Pago buscarPorId(@PathParam("id") Integer id){
        return this.pagoService.buscarPorIdPago(id);
    }
    
    @Path("/guardarCli")
    @POST
    public void guardar(Pago pago){
        this.pagoService.guardarPago(pago);
    }

    @Path("/actualizarCli/{id}")
    @PUT
    public void actualizar(Pago pago, @PathParam("id") Integer id){
        this.pagoService.actualizarPago(pago, id);
    }

    @Path("/eliminarCli/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.pagoService.eliminarPorId(id);
    }
 

}
