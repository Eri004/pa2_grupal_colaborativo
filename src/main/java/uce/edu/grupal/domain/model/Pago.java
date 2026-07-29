package uce.edu.grupal.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "pago")
@Entity
public class Pago extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pago_generador")
    @SequenceGenerator(name = "seq_pago_generador", sequenceName = "seq_pago", allocationSize = 1)
    @Column(name = "pago_id")
    private Integer id;

    @OneToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "rese_id")
    private ReservaVehiculo reserva;

    @Column(name = "pago_monto")
    private BigDecimal monto;

    @Column(name = "pago_fecha")
    private LocalDate fechaPago;

    @Column(name = "pago_metodo")
    private String metodoPago;

    @Column(name = "pago_estado")
    private String estadoPago;

    @Column(name = "pago_factura"   )
    private String numeroFactura;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservaVehiculo getReserva() {
        return reserva;
    }

    public void setReserva(ReservaVehiculo reserva) {
        this.reserva = reserva;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }



}
