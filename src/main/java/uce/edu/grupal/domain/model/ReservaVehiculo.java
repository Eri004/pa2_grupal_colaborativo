package uce.edu.grupal.domain.model;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "reserva_vehiculo")
@Entity
public class ReservaVehiculo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva_generador")
    @SequenceGenerator(name = "seq_reserva_generador", sequenceName = "seq_reserva", allocationSize = 1)
    @Column(name = "rese_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "clie_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vehi_placa")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "empl_id")
    private Empleado empleado;

    @Column(name = "rese_fecha")
    private LocalDate fecha;

    @Column(name = "rese_estado")
    private String estado;

    @Column(name = "rese_codigo")
    private String codigoReserva;

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
