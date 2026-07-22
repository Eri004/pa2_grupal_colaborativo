package ec.edu.uce.domain.model;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio_adicional")
public class ServicioAdicional extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "sec_servicio_adicional_generator", sequenceName = "sec_servicio_adicional", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_servicio_adicional_generator")
    @Column(name = "serad_id")
    private Integer id;

    @Column(name = "serad_nombre")
    private String nombre;

    @Column(name = "serad_costo")
    private BigDecimal costo;

    @Column(name = "serad_descripcion")
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
