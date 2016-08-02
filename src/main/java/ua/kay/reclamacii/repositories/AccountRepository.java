package ua.kay.reclamacii.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kay.reclamacii.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByIdAccount(Long id);
    Account findByName(String name);
}
