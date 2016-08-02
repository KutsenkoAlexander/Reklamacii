package ua.kay.reclamacii.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "shipment", schema = "", catalog = "reclamacii")
public class Shipment implements Serializable {
    private static final long serialVersionUID = 2825697165515784643L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_shipment")
    private long idShipment;

    @Basic
    @Column(name = "appointment_date", nullable = true)
    private String appointmentDate;

    @Basic
    @Column(name = "complete_date", nullable = true)
    private String completeDate;

    @Basic
    @Column(name = "waybill", nullable = true)
    private String waybill;

    @JsonIgnore
    @OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Record> records;

    public long getIdShipment() {
        return idShipment;
    }

    public void setIdShipment(long idShipment) {
        this.idShipment = idShipment;
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

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public Collection<Record> getRecords() {
        return records;
    }

    public void setRecords(Collection<Record> records) {
        this.records = records;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idShipment, appointmentDate, completeDate, waybill, records);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Shipment other = (Shipment) obj;
        return Objects.equals(this.idShipment, other.idShipment)
                && Objects.equals(this.appointmentDate, other.appointmentDate)
                && Objects.equals(this.completeDate, other.completeDate)
                && Objects.equals(this.waybill, other.waybill)
                && Objects.equals(this.records, other.records);
    }
}
