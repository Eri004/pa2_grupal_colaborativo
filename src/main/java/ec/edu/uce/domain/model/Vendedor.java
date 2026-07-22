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
@Table(name = "vendedor")
@NamedQuery(name = "Vendedor.buscarPorCedula", query = "SELECT v FROM Vendedor v WHERE v.cedula LIKE :cedula")
public class Vendedor extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_vendedor_generator", sequenceName = "seq_vendedor", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vendedor_generator")
    private Integer id;

    @Column(name = "vend_cedula")
    private String cedula;

    @Column(name = "vend_nombre")
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
