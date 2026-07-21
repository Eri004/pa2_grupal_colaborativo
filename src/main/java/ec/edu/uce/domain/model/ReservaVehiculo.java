package ec.edu.uce.domain.model;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva_vehiculo")
@NamedQuery(name = "ReservaVehiculo.buscarPorCedula", query = "SELECT rv FROM ReservaVehiculo rv WHERE re.cedulaVendedor LIKE :cedulaVendedor")
public class ReservaVehiculo extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_reserva_vehiculo_generador", sequenceName = "seq_reserva_vehiculo", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva_vehiculo_generador")
    private Integer id;

    @Column(name = "reve_cedula_vendedor")
    private String cedulaVendedor;

    @Column(name = "reve_placa_vehiculo")
    private String placaVehiculo;

    @Column(name = "reve_fecha")
    private LocalDate fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedulaVendedor() {
        return cedulaVendedor;
    }

    public void setCedulaVendedor(String cedulaVendedor) {
        this.cedulaVendedor = cedulaVendedor;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
