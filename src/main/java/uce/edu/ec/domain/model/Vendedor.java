package uce.edu.ec.domain.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendedor")
public class Vendedor extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vendedor_generador")
    @SequenceGenerator(name = "seq_vendedor_generador", sequenceName = "seq_vendedor", allocationSize = 1)
    @Column(name = "ven_id")
    private Integer id;
    @Column(name = "ven_nombre")
    private String nombre;
    @Column(name = "ven_cedula")
    private String cedula;
    @Column(name = "ven_correo")
    private String correo;
    @Column(name = "ven_telefono")
    private String telefono;
    @OneToMany(mappedBy = "vendedor")
    private List<Cliente> clientes;
    @OneToMany(mappedBy = "vendedor")
    private List<Vehiculo> vehiculos;
    public Vendedor() {
    }
    public Vendedor(String nombre, String cedula, String correo, String telefono, List<Cliente> clientes) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.clientes = clientes;
    }
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
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public List<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    
    

}
