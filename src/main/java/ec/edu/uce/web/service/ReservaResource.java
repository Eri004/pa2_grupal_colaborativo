package ec.edu.uce.web.service;

import ec.edu.uce.application.service.ReservaService;
import ec.edu.uce.domain.model.Reserva;
import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.domain.model.Pago;
import java.time.LocalDate;
import java.util.List;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/reserva")
public class ReservaResource {

    @Inject
    private ReservaService rs;

    @Path("/guardar")
    @POST
    public void guardar(Reserva r){

        this.rs.guardar(r);

    }

    @Path("/actualizar/{id}")
    @PUT
    public void actualizar(@PathParam("id") Integer id, Reserva r){

        this.rs.actualizar(id, r);

    }

    @Path("/porId/{id}")
    @GET
    public Reserva buscarPorId(@PathParam("id") Integer id){

        return this.rs.buscarPorId(id);

    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){

        this.rs.eliminar(id);

    }

    @Path("/nueva")
    @POST
    public void nuevaReserva(Reserva r) {
        this.rs.nuevaReserva(r);
    }

    @Path("/porFecha/{fecha}")
    @GET
    public List<Reserva> buscarPorFecha(@PathParam("fecha") String fecha) {
        return this.rs.buscarPorFecha(LocalDate.parse(fecha));
    }

    @GET
    @Path("/buscar")
    public Reserva buscarPorPlacaCedulaFecha(@QueryParam("placaVehiculo") String placaVehiculo,
                                            @QueryParam("cedulaVendedor") String cedulaVendedor,
                                            @QueryParam("fecha") String fechaStr) {

        LocalDate fecha = LocalDate.parse(fechaStr);

        return this.rs.buscarPorPlacaCedulaFecha(placaVehiculo, cedulaVendedor, fecha);
    }

    @Path("/reactiva")
    @POST
    public Uni<String> realizarReservaReactiva(Reserva r) {

        String placaVehiculo   = r.getVehiculo().getPlaca();
        String cedulaVendedor  = r.getVendedor().getCedula();
        Cliente cliente        = r.getCliente();
        Pago    pago           = r.getPago();
        LocalDate fecha        = r.getFecha();

        return rs.realizarReservaReactiva(
                placaVehiculo,
                cedulaVendedor,
                cliente,
                pago,
                fecha);
    }

}
