package uce.edu.grupal.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "empleado")
@Entity
public class Empleado extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_empleado_generador")
    @SequenceGenerator(name = "seq_empleado_generador", sequenceName = "seq_empleado", allocationSize = 1)
    @Column(name = "empl_id")
    private Integer id;

    @Column(name = "empl_cedula", unique = true)
    private String cedula;

    @Column(name = "empl_nombres")
    private String nombres;

    @Column(name = "empl_apellidos")
    private String apellidos;

    @Column(name = "empl_cargo")
    private String cargo;

    @Column(name = "empl_telefono")
    private String telefono;

    @Column(name = "empl_estado")
    private String estado;

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

        public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombres=" + nombres
                + ", apellidos=" + apellidos + ", cargo=" + cargo
                + ", telefono=" + telefono + "]";
    }

}
