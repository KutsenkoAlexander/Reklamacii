package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ctp", schema = "", catalog = "reclamacii")
public class Ctp implements Serializable {
    private static final long serialVersionUID = 6077033314999247066L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_ctp")
    private long idCtp;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private SprProductType productType;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private SprConsumer consumer;

    @JsonIgnore
    @OneToMany(mappedBy = "ctp", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Record> records;

    public long getIdCtp() {
        return idCtp;
    }

    public void setIdCtp(long idCtp) {
        this.idCtp = idCtp;
    }

    public SprProductType getProductType() {
        return productType;
    }

    public void setProductType(SprProductType productType) {
        this.productType = productType;
    }

    public SprConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(SprConsumer consumer) {
        this.consumer = consumer;
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void setRecords(Collection<Record> records) {
        this.records = records;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCtp, productType, consumer, records);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Ctp other = (Ctp) obj;
        return Objects.equals(this.idCtp, other.idCtp)
                && Objects.equals(this.productType, other.productType)
                && Objects.equals(this.consumer, other.consumer)
                && Objects.equals(this.records, other.records);
    }
}
