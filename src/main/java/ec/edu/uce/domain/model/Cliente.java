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
@Table(name = "cliente")
public class Cliente extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_cliente_generator", sequenceName = "seq_cliente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente_")
    private Integer id;

    @Column(name = "clie_identificacion")
    private String identificaion;

    @Column(name = "clie_nombre")
    private String nombre;

    @Column(name = "clie_telefono")
    private String telefono;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificaion() {
        return identificaion;
    }

    public void setIdentificaion(String identificaion) {
        this.identificaion = identificaion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    

}
