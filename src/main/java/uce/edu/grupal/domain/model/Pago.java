package uce.edu.grupal.domain.model;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "pago")
@Entity
public class Pago extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pago_generador")
    @SequenceGenerator(name = "seq_pago_generador", sequenceName = "seq_pago", allocationSize = 1)
    @Column(name = "pago_id")
    private Integer id;

    @Column(name = "pago_monto")
    private BigDecimal monto;

    @Column(name = "pago_metodo")
    private String metodoPago;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }


    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }


    @Override
    public String toString() {
        return "Pago: \n\tMonto:" + monto + "\n\tMetodo:" + metodoPago + "\n---------";
    }

}
