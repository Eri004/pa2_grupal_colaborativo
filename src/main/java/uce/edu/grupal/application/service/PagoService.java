package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Pago;
import uce.edu.grupal.domain.model.ReservaVehiculo;
import uce.edu.grupal.infrastructure.repository.PagoRepositoryImpl;
import uce.edu.grupal.web.resources.request.PagoRequestDTO;

@ApplicationScoped
@Transactional
public class PagoService {

    @Inject
    private PagoRepositoryImpl pr;

    @Inject
    private ReservaVehiculoService rs;

    public void guardar(PagoRequestDTO dto) {

        ReservaVehiculo reserva = this.rs.buscarPorCodigo(dto.getCodigoReserva());

        if (reserva == null) {
            throw new RuntimeException("No existe la reserva: " + dto.getCodigoReserva());
        }

        if (!reserva.getEstado().equals("CONFIRMADA")) {
            throw new RuntimeException("La reserva no está confirmada");
        }

        Pago existe = this.pr.buscarPorReserva(reserva.getId());

        if (existe != null) {
            throw new RuntimeException("La reserva ya tiene un pago registrado");
        }

        Pago pago = new Pago();

        pago.setReserva(reserva);
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMetodoPago(dto.getMetodoPago());

        pago.setNumeroFactura(generarNumeroFactura());
        pago.setEstadoPago("PAGADO");

        this.pr.persist(pago);
    }

    public Pago buscarPorId(Integer id) {
        return this.pr.findById(id);
    }

    public Pago buscarPorReserva(Integer idReserva) {
        return this.pr.buscarPorReserva(idReserva);
    }

    public List<Pago> buscarTodos() {
        return this.pr.listAll();
    }

    public void eliminar(Integer id) {
        this.pr.deleteById(id);
    }

    public void actualizar(String factura, PagoRequestDTO dto) {

        Pago pago = this.pr.buscarPorFactura(factura);

        pago.setMonto(dto.getMonto());
        pago.setFechaPago(dto.getFechaPago());
        pago.setMetodoPago(dto.getMetodoPago());
        pago.setEstadoPago(dto.getEstadoPago());
    }

    private String generarNumeroFactura() {

        long numero = System.currentTimeMillis();

        return "FAC-" + numero;
    }

}
