package ec.edu.uce.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendedor")
public class Vendedor extends PanacheEntityBase{

    @Id
    @SequenceGenerator(name = "seq_vendedor_generador", sequenceName = "sec_vendedor", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vendedor_generador")
    @Column(name = "vend_id")
    private Integer id;
    @Column(name = "vend_cedula")
    private String cedula;

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

    @Override
    public String toString() {
        return "Vendedor [id=" + id + ", cedula=" + cedula + "]";
    }

}
