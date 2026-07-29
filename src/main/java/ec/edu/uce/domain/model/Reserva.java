package ec.edu.uce.domain.model;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva extends PanacheEntityBase{

    @Id
    @SequenceGenerator(name = "seq_reserva_generador", sequenceName = "sec_reserva", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva_generador")
    @Column(name = "rese_id")
    private Integer id;
   @Column(name = "reve_fecha")
    private LocalDate fecha;

    @Column(name = "reve_estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "clie_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vend_id")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "vehi_id")
    private Vehiculo vehiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id")
    private Pago pago;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", fecha=" + fecha + ", estado=" + estado + ", cliente=" + cliente + ", vendedor="
                + vendedor + ", vehiculo=" + vehiculo + ", pago=" + pago + "]";
    }

    
    

}
