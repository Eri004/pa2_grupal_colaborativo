package ec.edu.uce.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva_vehiculo")
public class ReservaVehiculo extends PanacheEntityBase{

    @Id
    @SequenceGenerator(name = "sec_reserva_vehiculo_generador", sequenceName = "sec_reserva_vehiculo", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_reserva_vehiculo_generador")
    @Column(name = "resev_id")
    private Integer id;

    @Column(name = "resev_placa_vehiculo")
    private String placaVehiculo;

    @Column(name = "resev_cedula_vendedor")
    private String cedula;

    @Column(name = "resev_fecha_reserva")
    private LocalDate fechaReserva;

    @ManyToOne()
    @JoinColumn(name = "resev_cliente_id")
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "resev_vehiculo_id")
    private Vehiculo vehiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resev_garantia_id")
    public Garantia garantia;

    @ManyToMany
    @JoinTable(
        name = "reserva_servicio", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "reserva_id"), // FK de esta entidad (Reserva)
        inverseJoinColumns = @JoinColumn(name = "servicio_id") // FK de la otra entidad (ServicioAdicional)
    )
    public List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
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

    public Garantia getGarantia() {
        return garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    
}
