package uce.edu.ec.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name = "vehiculo")
public class Vehiculo extends PanacheEntityBase{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehiculo_generador")
    @SequenceGenerator(name = "seq_vehiculo_generador", sequenceName = "seq_vehiculo", allocationSize = 1)
    @Column(name = "veh_id")
    private Integer id;
    @Column(name = "veh_placa")
    private String placa;
    @Column(name = "veh_años")
    private Integer años;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    @Column(name = "veh_esReservado")
    private Boolean esResevado;
    @Column(name = "veh_valor")
    private Double valor;
    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;
    public Vehiculo() {
    }
    public Vehiculo(String placa, Integer años, Marca marca, Boolean esResevado, Double valor) {
        this.placa = placa;
        this.años = años;
        this.marca = marca;
        this.esResevado = esResevado;
        this.valor = valor;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public Integer getAños() {
        return años;
    }
    public void setAños(Integer años) {
        this.años = años;
    }
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public Boolean getEsResevado() {
        return esResevado;
    }
    public void setEsResevado(Boolean esResevado) {
        this.esResevado = esResevado;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Vendedor getVendedor() {
        return vendedor;
    }
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    


}
