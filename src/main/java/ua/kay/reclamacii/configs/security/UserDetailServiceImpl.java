package ua.kay.reclamacii.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ua.kay.reclamacii.models.Account;
import ua.kay.reclamacii.repositories.AccountRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository service;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = service.findByName(name);
        if(account == null) {
            throw new UsernameNotFoundException("no user found with " + name);
        }
        return new AccountUserDetails(account);
    }

}
