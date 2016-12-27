package fi.oispakaljaa.karhu.controller;

import fi.oispakaljaa.karhu.domain.Account;
import fi.oispakaljaa.karhu.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Account account) {        
        accountRepository.save(account);
        return "redirect:/index";
    }

}
