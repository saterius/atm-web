package th.ac.ku.atm.service;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    private List<BankAccount> bankAccountsList;

    @PostConstruct
    public void postContruct() {
        this.bankAccountsList = new ArrayList<>();
    }

    public void createBankAccount(BankAccount bankAccount) {
        bankAccountsList.add(bankAccount);
    }

    public List<BankAccount> getBankAccount() {
        return new ArrayList<>(this.bankAccountsList);
    }
}
