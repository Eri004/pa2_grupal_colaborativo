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
@Table(name = "marca")
public class Marca extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca_generador")
    @SequenceGenerator(name = "seq_marca_generador", sequenceName = "seq_marca", allocationSize = 1)
    @Column(name = "mar_id")
    private Integer id;
    @Column(name = "mar_nombre")
    private String nombre;
    @Column(name = "mar_pais")
    private String pais;
    @Column(name = "mar_empresa")
    private String empresa;
    @OneToMany(mappedBy = "marca")
    private List<Vehiculo> vehiculos;
    
    public Marca() {
    }

    public Marca(String nombre, String pais, String empresa, List<Vehiculo> vehiculos) {
        this.nombre = nombre;
        this.pais = pais;
        this.empresa = empresa;
        this.vehiculos = vehiculos;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    
}
