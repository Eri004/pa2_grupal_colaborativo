package uce.edu.grupal.domain.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "cliente")
@Entity
public class Cliente extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente_generador")
    @SequenceGenerator(name = "seq_cliente_generador", sequenceName = "seq_cliente", allocationSize = 1)
    @Column(name = "clie_id")
    private Integer id;

    @Column(name = "clie_cedula", nullable = false, unique = true)
    private String cedula;

    @Column(name = "clie_nombres")
    private String nombres;

    @Column(name = "clie_apellidos")
    private String apellidos;

    @Column(name = "clie_telefono")
    private String telefono;

    @Column(name = "clie_correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "clie_estado")
    private String estado;

    @OneToMany(mappedBy = "cliente",
            cascade = CascadeType.REMOVE)
    private List<ReservaVehiculo> reservas;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente [cedula=" + cedula + ", nombres=" + nombres
                + ", apellidos=" + apellidos + ", telefono=" + telefono
                + ", correo=" + correo + "]";
    }

}
