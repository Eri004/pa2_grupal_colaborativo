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

@Path("/reservas")
public class ReservaVehiculoResource {

    @Inject
    private ReservaVehiculoService rs;

    @Path("/guardar")
    @POST
    public void guardar(ReservaVehiculo r){
        this.rs.guardar(r);
    }

    @Path("/actualizar/{id}")
    @PUT
    public void actualizar(@PathParam("id") Integer id, ReservaVehiculo r){
        this.rs.actualizar(id, r);

    }

    @Path("/porId/{id}")
    @GET
    public ReservaVehiculo buscarPorId(@PathParam("id") Integer id){
        return this.rs.buscarPorId(id);
    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.rs.eliminar(id);
    }

    @Path("/nueva")
    @POST
    public void nuevaReserva(ReservaVehiculo r) {
        this.rs.nuevaReserva(r);
    }

    @Path("/porFecha/{fecha}")
    @GET
    public List<ReservaVehiculo> buscarPorFecha(@PathParam("fecha") String fecha) {
        return this.rs.buscarPorFecha(LocalDate.parse(fecha));
    }

    @GET
    @Path("/buscar/{placaVehiculo}/{cedulaVendedor}/{fecha}")
    public ReservaVehiculo buscarPorPlacaCedulaFecha(@PathParam("placaVehiculo") String placaVehiculo,
                                            @PathParam("cedulaVendedor") String cedulaVendedor,
                                            @PathParam("fecha") String fechaStr) {

        LocalDate fecha = LocalDate.parse(fechaStr);

        return this.rs.buscarPorPlacaCedulaFecha(placaVehiculo, cedulaVendedor, fecha);
    }

    @Path("/buscarTodos")
    @GET
    public List<ReservaVehiculo> buscarTodos(){
        return this.rs.buscarTodos();
    }

    @GET
    @Path("/vehiculo/{placa}")
    public List<ReservaVehiculo> buscarPorPlaca(@PathParam("placa") String placa) {
        return this.rs.buscarPorPlaca(placa);
    }

    @GET
    @Path("/vendedor-cedula/{cedula}")
    public List<ReservaVehiculo> buscarPorCedulaVendedor(@PathParam("cedula") String cedula) {
        return this.rs.buscarPorCedulaVendedor(cedula);
    }
    

}
