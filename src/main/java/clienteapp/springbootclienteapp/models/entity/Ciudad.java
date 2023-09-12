package clienteapp.springbootclienteapp.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="ciudades")
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Ciudad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "id=" + id +
                ", Ciudad='" + Ciudad + '\'' +
                '}';
    }
}
