package uce.edu.grupal.web.resources;

import java.time.LocalDate;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.grupal.application.service.ReservaVehiculoService;
import uce.edu.grupal.domain.model.ReservaVehiculo;
import uce.edu.grupal.web.resources.request.ReservaRequestDTO;

@Path("/reservas")
public class ReservaVehiculoResource {

    @Inject
    private ReservaVehiculoService rs;

    @GET
    public List<ReservaVehiculo> obtenerTodos() {
        return this.rs.buscarTodos();
    }

    @GET
    @Path("/{id}")
    public ReservaVehiculo obtenerPorId(@PathParam("id") Integer id) {
        return this.rs.buscarPorId(id);
    }

    @GET
    @Path("/fecha/{fecha}")
    public List<ReservaVehiculo> buscarPorFecha(@PathParam("fecha") LocalDate fecha) {
        return this.rs.buscarPorFecha(fecha);
    }

    @GET
    @Path("/cliente/{cedula}")
    public List<ReservaVehiculo> buscarPorCliente(@PathParam("cedula") String cedula) {
        return this.rs.buscarPorCedulaCliente(cedula);
    }

    @GET
    @Path("/vehiculo/{placa}")
    public List<ReservaVehiculo> buscarPorVehiculo(@PathParam("placa") String placa) {
        return this.rs.buscarPorPlaca(placa);
    }

    @POST
    public void guardar(ReservaRequestDTO dto) {
        this.rs.guardar(dto);
    }

    @PUT
    @Path("/{codigo}")
    public void actualizar(
            @PathParam("codigo") String codigo,
            ReservaRequestDTO dto) {

        rs.actualizar(codigo, dto);
    }

    @DELETE
    @Path("/{codigo}")
    public void eliminar(@PathParam("codigo") String codigo) {
        this.rs.eliminar(codigo);
    }
}
