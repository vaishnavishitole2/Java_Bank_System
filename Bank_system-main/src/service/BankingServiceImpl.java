package service;

import dao.*;
import model.*;

import java.util.List;

public class BankingServiceImpl implements BankingService {
    private CustomerDAO customerDAO = new CustomerDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private BeneficiaryDAO beneficiaryDAO = new BeneficiaryDAO();

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    @Override
    public Customer findCustomerById(int customerID) {
        return customerDAO.getCustomerById(customerID);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public void addAccount(Account account) {
        accountDAO.addAccount(account);
    }

    @Override
    public List<Account> getAccountsByCustomerId(int customerID) {
        return accountDAO.getAccountsByCustomerId(customerID);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Account account = accountDAO.getAccountsByCustomerId(transaction.getAccountID())
                .stream()
                .findFirst()
                .orElse(null);

        if (account != null) {
            if (transaction.getType().equalsIgnoreCase("Withdrawal")) {
                if (account.getBalance() >= transaction.getAmount()) {
                    accountDAO.updateAccountBalance(transaction.getAccountID(),
                            account.getBalance() - transaction.getAmount());
                } else {
                    System.out.println("Insufficient Balance!");
                    return;
                }
            } else if (transaction.getType().equalsIgnoreCase("Deposit")) {
                accountDAO.updateAccountBalance(transaction.getAccountID(),
                        account.getBalance() + transaction.getAmount());
            }
            transactionDAO.addTransaction(transaction);
        } else {
            System.out.println("Account not found!");
        }
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountID) {
        return transactionDAO.getTransactionsByAccountId(accountID);
    }

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {
        beneficiaryDAO.addBeneficiary(beneficiary);
    }

    @Override
    public List<Beneficiary> getBeneficiariesByCustomerId(int customerID) {
        return beneficiaryDAO.getBeneficiariesByCustomerId(customerID);
    }
}
