package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "spr_consumer", schema = "", catalog = "reclamacii")
public class SprConsumer implements Serializable {
    private static final long serialVersionUID = 7980724202426983214L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_consumer")
    private long idConsumer;

    @Basic
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Ctp> ctpSet;

    public long getIdConsumer() {
        return idConsumer;
    }

    public void setIdConsumer(long idConsumer) {
        this.idConsumer = idConsumer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Ctp> getCtpSet() {
        return ctpSet;
    }

    public void setCtpSet(Collection<Ctp> ctpSet) {
        this.ctpSet = ctpSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsumer, name, ctpSet);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SprConsumer other = (SprConsumer) obj;
        return Objects.equals(this.idConsumer, other.idConsumer)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.ctpSet, other.ctpSet);
    }
}
