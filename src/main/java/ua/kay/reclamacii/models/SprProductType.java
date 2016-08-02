package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "spr_product_type", schema = "", catalog = "reclamacii")
public class SprProductType implements Serializable {
    private static final long serialVersionUID = -8732103729792914573L;

    @Id
    @Column(name = "id_product_type")
    private long idProductType;

    @Basic
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "productType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Ctp> ctpSet;

    public long getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(long idProductType) {
        this.idProductType = idProductType;
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
        return Objects.hash(idProductType, name, ctpSet);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SprProductType other = (SprProductType) obj;
        return Objects.equals(this.idProductType, other.idProductType)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.ctpSet, other.ctpSet);
    }
}
