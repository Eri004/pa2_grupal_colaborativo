package ec.edu.uce.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo extends PanacheEntityBase{

    @Id
    @SequenceGenerator(name = "seq_vehiculo_generador", sequenceName = "sec_vehiculo", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehiculo_generador")
    @Column(name = "vehi_id")
    private Integer id;
     @Column(name = "vehi_placa")
    private String placa;

    
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
     @Override
     public String toString() {
        return "Vehiculo [id=" + id + ", placa=" + placa + "]";
     }

}
