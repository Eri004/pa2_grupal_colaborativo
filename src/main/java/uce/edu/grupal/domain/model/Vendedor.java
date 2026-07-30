package uce.edu.grupal.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "vendedor")
@Entity
public class Vendedor extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vendedor_generador")
    @SequenceGenerator(name = "seq_vendedor_generador", sequenceName = "seq_vendedor", allocationSize = 1)
    @Column(name = "vend_id")
    private Integer id;

    @Column(name = "vend_cedula", unique = true)
    private String cedula;

    @Column(name = "vend_nombres")
    private String nombres;

    @Column(name = "vend_apellidos")
    private String apellidos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Vendedor: \n\tCedula:" + cedula + "\n\tNombres:" + nombres
                + "\n\tApellidos:" + apellidos  + "\n---------";
    }

    
}
