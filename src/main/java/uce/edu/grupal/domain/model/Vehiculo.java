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

@Table(name = "vehiculo")
@Entity
public class Vehiculo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehiculo_generador")
    @SequenceGenerator(name = "seq_vehiculo_generador", sequenceName = "seq_vehiculo", allocationSize = 1)
    @Column(name = "vehi_id")
    private Integer id;

    @Column(name = "vehi_placa", unique = true, nullable = false)
    private String placa;

    @Column(name = "vehi_marca")
    private String marca;

    @Column(name = "vehi_modelo")
    private String modelo;

    @Column(name = "vehi_color")
    private String color;

    @Column(name = "vehi_anio")
    private Integer anio;

    @Column(name = "vehi_estado")
    private String estado;

    @OneToMany(mappedBy = "vehiculo",
            cascade = CascadeType.REMOVE)
    private List<ReservaVehiculo> reservas;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vehiculo [placa=" + placa + ", marca=" + marca
                + ", modelo=" + modelo + ", color=" + color
                + ", anio=" + anio + ", estado=" + estado + "]";
    }
}
