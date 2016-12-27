package fi.oispakaljaa.karhu.service;

import fi.oispakaljaa.karhu.domain.Account;
import fi.oispakaljaa.karhu.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAuthenticatedPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return accountRepository.findByUsername(authentication.getPrincipal().toString());
    }
}