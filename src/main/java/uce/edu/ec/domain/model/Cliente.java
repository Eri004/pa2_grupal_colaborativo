package uce.edu.ec.domain.model;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente_generador")
    @SequenceGenerator(name = "seq_cliente_generador", sequenceName = "seq_cliente", allocationSize = 1)
    @Column(name = "cli_id")
    private Integer id;
    @Column(name = "cli_nombre")
    private String nombre;
    @Column(name = "cli_cedula")
    private String cedula;
    @Column(name = "cli_correo")
    private String correo;
    @Column(name = "cli_ciudad")
    private String ciudad;
    @OneToMany(mappedBy = "cliente")
    private List<ReservaVehiculo> reservas;
    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;
    public Cliente() {
    }
    public Cliente(String nombre, String cedula, String correo, String ciudad, List<ReservaVehiculo> reservas,
            Vendedor vendedor) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.ciudad = ciudad;
        this.reservas = reservas;
        this.vendedor = vendedor;
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
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public List<ReservaVehiculo> getReservas() {
        return reservas;
    }
    public void setReservas(List<ReservaVehiculo> reservas) {
        this.reservas = reservas;
    }
    public Vendedor getVendedor() {
        return vendedor;
    }
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    

}
