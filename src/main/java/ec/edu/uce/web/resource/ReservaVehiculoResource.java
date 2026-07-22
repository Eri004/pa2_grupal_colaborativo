package ec.edu.uce.web.resource;

import java.util.List;

import ec.edu.uce.application.service.ReservaVehiculoService;
import ec.edu.uce.domain.model.ReservaVehiculo;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/reservaVehiculo")
public class ReservaVehiculoResource {

    @Inject
    private ReservaVehiculoService reservaVehiculoService;

    @Path("/todos")
    @GET
    public List<ReservaVehiculo> buscarTodos() {
        return this.reservaVehiculoService.buscarTodos();
    }

    @Path("/porId/{id}")
    @GET
    public ReservaVehiculo buscarPorId(@PathParam("id") Integer id) {
        return this.reservaVehiculoService.buscarPorIdRes(id);
    }

    @Path("/guardarRes")
    @POST
    public void guardar(ReservaVehiculo reservaVehiculo) {
        this.reservaVehiculoService.guardarRes(reservaVehiculo);
    }

    @Path("/actualizarRes/{id}")
    @PUT
    public void actualizar(ReservaVehiculo reservaVehiculo, @PathParam("id") Integer id) {
        this.reservaVehiculoService.actualizarRes(reservaVehiculo, id);
    }

    @Path("/eliminarRes/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id) {
        this.reservaVehiculoService.eliminarPorIdRes(id);
    }

    @POST
    @Path("/nuevaReserva")
    public void nuevaReserva(ReservaVehiculo reservaVehiculo) {
        this.reservaVehiculoService.nuevaReserva(reservaVehiculo);
        System.out.println("Se guardo correctamente");
    }

    @GET
    @Path("/consultarReactivo/{cedulaCli}/{placa}")
    public String consultarReactivo(
            @PathParam("cedulaCli") String cedulaCli,
            @PathParam("placa") String placa) {
        return this.reservaVehiculoService.consultarReservaReactiva(cedulaCli, placa);
    }

}
