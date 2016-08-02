package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "defect", schema = "", catalog = "reclamacii")
public class Defect implements Serializable {
    private static final long serialVersionUID = -15808662715465017L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_defect")
    private long idDefect;

    @Basic
    @Column(name = "cause", nullable = false)
    private String cause;

    @ManyToOne
    @JoinColumn(name = "defect_class_id", nullable = false)
    private SprClassDefect sprClassDefect;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    public long getIdDefect() {
        return idDefect;
    }

    public void setIdDefect(long idDefect) {
        this.idDefect = idDefect;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public SprClassDefect getSprClassDefect() {
        return sprClassDefect;
    }

    public void setSprClassDefect(SprClassDefect sprClassDefect) {
        this.sprClassDefect = sprClassDefect;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDefect, cause, sprClassDefect, record);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Defect other = (Defect) obj;
        return Objects.equals(this.idDefect, other.idDefect)
                && Objects.equals(this.cause, other.cause)
                && Objects.equals(this.sprClassDefect, other.sprClassDefect)
                && Objects.equals(this.record, other.record);
    }

}
