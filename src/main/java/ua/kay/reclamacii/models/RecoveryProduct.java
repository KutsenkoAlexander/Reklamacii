package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "recovery_product", schema = "", catalog = "reclamacii")
public class RecoveryProduct implements Serializable {
    private static final long serialVersionUID = 3284145354420290747L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_recovery_product")
    private long idRecoveryProduct;

    @Basic
    @Column(name = "appointment_recovery_date", nullable = true)
    private String appointmentRecoveryDate;

    @Basic
    @Column(name = "complete_recovery_date", nullable = true)
    private String completeRecoveryDate;

    @JsonIgnore
    @OneToMany(mappedBy = "recoveryProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Record> records;

    public long getIdRecoveryProduct() {
        return idRecoveryProduct;
    }

    public void setIdRecoveryProduct(long idRecoveryProduct) {
        this.idRecoveryProduct = idRecoveryProduct;
    }

    public String getAppointmentRecoveryDate() {
        return appointmentRecoveryDate;
    }

    public void setAppointmentRecoveryDate(String appointmentRecoveryDate) {
        this.appointmentRecoveryDate = appointmentRecoveryDate;
    }

    public String getCompleteRecoveryDate() {
        return completeRecoveryDate;
    }

    public void setCompleteRecoveryDate(String completeRecoveryDate) {
        this.completeRecoveryDate = completeRecoveryDate;
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void setRecords(Collection<Record> records) {
        this.records = records;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecoveryProduct, appointmentRecoveryDate, completeRecoveryDate, records);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RecoveryProduct other = (RecoveryProduct) obj;
        return Objects.equals(this.idRecoveryProduct, other.idRecoveryProduct)
                && Objects.equals(this.appointmentRecoveryDate, other.appointmentRecoveryDate)
                && Objects.equals(this.completeRecoveryDate, other.completeRecoveryDate)
                && Objects.equals(this.records, other.records);
    }
}
