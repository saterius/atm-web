package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService){
        this.bankAccountService=bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model){
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-edit";
    }

    @PostMapping("/edit/{id}")
    public String selectTransaction(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model, double inputAmount, String transaction) {
        BankAccount record = bankAccountService.getBankAccount(bankAccount.getId());
        double currentBalance = record.getBalance();
        if (transaction.equals("deposit")) {
            if(inputAmount <= 0){
                return "redirect:/bankaccount";
            }
            record.setBalance(record.getBalance()+inputAmount);
        }
        else {
            if(inputAmount > currentBalance || inputAmount <= 0){
                return "redirect:/bankaccount";
            }
            record.setBalance(record.getBalance()-inputAmount);
        }
        bankAccountService.editBankAccount(record);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {
        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("bankaccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

//    @PostMapping
//    public String regisBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
//        bankAccountService.createBankAccount(bankAccount);
//        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
//        return "redirect:bankaccount";
//    }
}