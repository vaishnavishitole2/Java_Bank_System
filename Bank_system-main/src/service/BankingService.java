package service;

import model.*;

import java.util.List;

public interface BankingService {
    void addCustomer(Customer customer);
    Customer findCustomerById(int customerID);
    List<Customer> listAllCustomers();
    
    void addAccount(Account account);
    List<Account> getAccountsByCustomerId(int customerID);
    
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByAccountId(int accountID);
    
    void addBeneficiary(Beneficiary beneficiary);
    List<Beneficiary> getBeneficiariesByCustomerId(int customerID);
}
