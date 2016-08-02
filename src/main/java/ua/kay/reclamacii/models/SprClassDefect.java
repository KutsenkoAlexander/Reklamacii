package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "spr_class_defect", schema = "", catalog = "reclamacii")
public class SprClassDefect implements Serializable {
    private static final long serialVersionUID = 2236678790971458745L;

    @Id
    @Column(name = "id_class_defect")
    private long idClassDefect;

    @Basic
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "sprClassDefect", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Defect> defects;

    public long getIdClassDefect() {
        return idClassDefect;
    }

    public void setIdClassDefect(long idClassDefect) {
        this.idClassDefect = idClassDefect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Defect> getDefects() {
        return defects;
    }

    public void setDefects(Collection<Defect> defects) {
        this.defects = defects;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClassDefect, name, defects);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SprClassDefect other = (SprClassDefect) obj;
        return Objects.equals(this.idClassDefect, other.idClassDefect)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.defects, other.defects);
    }
}
