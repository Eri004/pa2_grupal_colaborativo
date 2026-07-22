package ec.edu.uce.domain.model;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "garantia")
public class Garantia extends PanacheEntityBase{

    @Id
    @SequenceGenerator(name = "sec_garantia_generador", sequenceName = "sec_garantia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_garantia_generador")
    @Column(name ="gara_id")
    private Integer id;

    @Column(name = "gara_estado")
    private String estado;

    @Column(name = "gara_metodo_pago")
    private String metodoPago;

    @Column(name = "gara_monto_deposito")
    private BigDecimal montoDeposito;

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

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getMontoDeposito() {
        return montoDeposito;
    }

    public void setMontoDeposito(BigDecimal montoDeposito) {
        this.montoDeposito = montoDeposito;
    }

    
}

