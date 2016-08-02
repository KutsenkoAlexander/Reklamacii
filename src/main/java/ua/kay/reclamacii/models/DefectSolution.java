package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "defect_solution", schema = "", catalog = "reclamacii")
public class DefectSolution implements Serializable {
    private static final long serialVersionUID = -956174201861163882L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_defect_solution")
    private long idDefectSolution;

    @Basic
    @Column(name = "measures", nullable = false)
    private String measures;

    @Basic
    @Column(name = "appointment_date", nullable = false)
    private String appointmentDate;

    @Basic
    @Column(name = "complete_date", nullable = true)
    private String completeDate;

    @ManyToOne
    @JoinColumn(name = "executor_id", nullable = false)
    private SprExecutor executor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    public long getIdDefectSolution() {
        return idDefectSolution;
    }

    public void setIdDefectSolution(long idDefectSolution) {
        this.idDefectSolution = idDefectSolution;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public SprExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(SprExecutor executor) {
        this.executor = executor;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDefectSolution, measures, appointmentDate, completeDate, executor, record);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final DefectSolution other = (DefectSolution) obj;
        return Objects.equals(this.idDefectSolution, other.idDefectSolution)
                && Objects.equals(this.measures, other.measures)
                && Objects.equals(this.appointmentDate, other.appointmentDate)
                && Objects.equals(this.completeDate, other.completeDate)
                && Objects.equals(this.executor, other.executor)
                && Objects.equals(this.record, other.record);
    }
}
