package uce.edu.ec.domain.model;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "reservaVehiculo")
public class ReservaVehiculo extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva_generador")
    @SequenceGenerator(name = "seq_reserva_generador", sequenceName = "seq_reserva", allocationSize = 1)
    @Column(name = "rvh_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;
    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;
    @Column(name = "rvh_fecha")
    private LocalDateTime fecha;
    public ReservaVehiculo() {
    }
    public ReservaVehiculo(Cliente cliente, Vehiculo vehiculo, Vendedor vendedor, LocalDateTime fecha) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.vendedor = vendedor;
        this.fecha = fecha;
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
    public Vendedor getVendedor() {
        return vendedor;
    }
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    

}
