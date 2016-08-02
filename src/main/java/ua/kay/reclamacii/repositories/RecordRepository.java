package ua.kay.reclamacii.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import ua.kay.reclamacii.models.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    @Async
    Record findByIdRecord(Long idRecord);

    @Async
    @Query("select max(r.entryDate) from Record r")
    String findByEntryDateMax();

    @Async
    @Query("select min(r.entryDate) from Record r")
    String findByEntryDateMin();

    @Async
    @Query(" select r from Record r " +
            "join r.ctp c " +
            "join c.productType p " +
            "join r.defects d " +
            "join d.sprClassDefect s " +
            "join c.consumer cn " +
            "where (p.idProductType = ?1 or ?1 is null) " +
            "and (s.idClassDefect = ?2 or ?2 is null) " +
            "and (r.user = ?3 or ?3 = '' or ?3 is null) " +
            "and r.entryDate between ?4 and ?5 " +
            "and (cn.name = ?6 or ?6 = '' or ?6 is null) " +
            "and (r.productName like ?7% or ?7 = '' or ?7 is null)" +
            "and (r.number like ?8% or ?8 = '' or ?8 is null) group by r.idRecord ")
    Page<Record> sortRecord(Long idProductType,
                            Long idClassDefect,
                            String user,
                            String dateFrom,
                            String dateTo,
                            String consumer,
                            String productName,
                            String number,
                            Pageable pageable);
}
