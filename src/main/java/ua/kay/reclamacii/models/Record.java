package ua.kay.reclamacii.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "record", schema = "", catalog = "reclamacii")
public class Record implements Serializable {
    private static final long serialVersionUID = -4715540245341307761L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_record")
    private long idRecord;

    @Basic
    @Column(name = "entry_date")
    private String entryDate;

    @Basic
    @Column(name = "user")
    private String user;

    @Basic
    @Column(name = "product_name")
    private String productName;

    @Basic
    @Column(name = "number")
    private String number;

    @Basic
    @Column(name = "solutions")
    private String solutions;

    @Basic
    @Column(name = "act_number")
    private String actNumber;

    @Basic
    @Column(name = "cheked_reklamacia", nullable = true, length = 1)
    private Integer chekedReklamacia;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ctp_id")
    private Ctp ctp;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "recovery_product_id", nullable = true)
    private RecoveryProduct recoveryProduct;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "shipment_id", nullable = true)
    private Shipment shipment;

    @OneToMany(mappedBy = "record", cascade = CascadeType.REMOVE)
    private Collection<Defect> defects;

    @OneToMany(mappedBy = "record", cascade = CascadeType.REMOVE)
    private Collection<DefectSolution> defectSolutions;

    public long getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(long idRecord) {
        this.idRecord = idRecord;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSolutions() {
        return solutions;
    }

    public void setSolutions(String solutions) {
        this.solutions = solutions;
    }

    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }

    public Integer getChekedReklamacia() {
        return chekedReklamacia;
    }

    public void setChekedReklamacia(Integer chekedReklamacia) {
        this.chekedReklamacia = chekedReklamacia;
    }

    public Ctp getCtp() {
        return ctp;
    }

    public void setCtp(Ctp ctp) {
        this.ctp = ctp;
    }

    public RecoveryProduct getRecoveryProduct() {
        return recoveryProduct;
    }

    public void setRecoveryProduct(RecoveryProduct recoveryProduct) {
        this.recoveryProduct = recoveryProduct;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Collection<Defect> getDefects() {
        return defects;
    }

    public void setDefects(Collection<Defect> defects) {
        this.defects = defects;
    }

    public Collection<DefectSolution> getDefectSolutions() {
        return defectSolutions;
    }

    public void setDefectSolutions(Collection<DefectSolution> defectSolutions) {
        this.defectSolutions = defectSolutions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecord, entryDate, user, productName, number, solutions, actNumber, chekedReklamacia, ctp, recoveryProduct, shipment, defects, defectSolutions);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Record other = (Record) obj;
        return Objects.equals(this.idRecord, other.idRecord)
                && Objects.equals(this.entryDate, other.entryDate)
                && Objects.equals(this.user, other.user)
                && Objects.equals(this.productName, other.productName)
                && Objects.equals(this.number, other.number)
                && Objects.equals(this.solutions, other.solutions)
                && Objects.equals(this.actNumber, other.actNumber)
                && Objects.equals(this.chekedReklamacia, other.chekedReklamacia)
                && Objects.equals(this.ctp, other.ctp)
                && Objects.equals(this.recoveryProduct, other.recoveryProduct)
                && Objects.equals(this.shipment, other.shipment)
                && Objects.equals(this.defects, other.defects)
                && Objects.equals(this.defectSolutions, other.defectSolutions);
    }

    @Override
    public String toString() {
        return "Record{" +
                "idRecord=" + idRecord +
                ", entryDate='" + entryDate + '\'' +
                ", user='" + user + '\'' +
                ", productName='" + productName + '\'' +
                ", number='" + number + '\'' +
                ", solutions='" + solutions + '\'' +
                ", actNumber='" + actNumber + '\'' +
                ", chekedReklamacia=" + chekedReklamacia +
                ", ctp=" + ctp +
                ", recoveryProduct=" + recoveryProduct +
                ", shipment=" + shipment +
                ", defects=" + defects +
                ", defectSolutions=" + defectSolutions +
                '}';
    }
}
