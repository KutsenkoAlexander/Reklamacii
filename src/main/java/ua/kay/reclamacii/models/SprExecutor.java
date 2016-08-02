package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "spr_executor", schema = "", catalog = "reclamacii")
public class SprExecutor implements Serializable {
    private static final long serialVersionUID = -8567572567943833382L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_executor")
    private long idExecutor;

    @Basic
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "executor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<DefectSolution> defectSolutions;

    public long getIdExecutor() {
        return idExecutor;
    }

    public void setIdExecutor(long idExecutor) {
        this.idExecutor = idExecutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<DefectSolution> getDefectSolutions() {
        return defectSolutions;
    }

    public void setDefectSolutions(Collection<DefectSolution> defectSolutions) {
        this.defectSolutions = defectSolutions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExecutor, name, defectSolutions);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SprExecutor other = (SprExecutor) obj;
        return Objects.equals(this.idExecutor, other.idExecutor)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.defectSolutions, other.defectSolutions);
    }
}
