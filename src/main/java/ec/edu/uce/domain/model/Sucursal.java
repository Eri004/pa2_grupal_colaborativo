package ec.edu.uce.domain.model;

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
@Table(name = "sucursal")
@NamedQuery(name = "Sucursal.buscarPorNombre", query = "SELECT s FROM Sucursal s WHERE s.nombre LIKE :nombre")
public class Sucursal extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_sucursal_generator", sequenceName = "seq_sucursal", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sucursal_generator")
    private Integer id;

    @Column(name = "sucu_nombre")
    private String nombre;

    @Column(name = "sucu_direccion")
    private String direccion;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
