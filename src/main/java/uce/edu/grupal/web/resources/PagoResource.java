package uce.edu.grupal.web.resources;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.grupal.application.service.PagoService;
import uce.edu.grupal.domain.model.Pago;
import uce.edu.grupal.web.resources.request.PagoRequestDTO;

@Path("/pagos")
public class PagoResource {

    @Inject
    private PagoService ps;

    @GET
    public List<Pago> obtenerTodos() {
        return this.ps.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public Pago obtenerPorId(@PathParam("id") Integer id) {
        return this.ps.buscarPorId(id);
    }

    @GET
    @Path("/reserva/{idReserva}")
    public Pago obtenerPorReserva(@PathParam("idReserva") Integer idReserva) {
        return this.ps.buscarPorReserva(idReserva);
    }

    @POST
    public void guardar(PagoRequestDTO dto) {
        this.ps.guardar(dto);
    }

    @PUT
    @Path("/{factura}")
    public void actualizar(
            @PathParam("factura") String factura,
            PagoRequestDTO dto) {

        this.ps.actualizar(factura, dto);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        this.ps.eliminar(id);
    }
}
