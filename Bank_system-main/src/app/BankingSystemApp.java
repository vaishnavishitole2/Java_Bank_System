package app;


 
import java.util.*;

import model.Account;
import model.Beneficiary;
import model.Customer;
import model.Transaction;
import service.BankingService;
import service.BankingServiceImpl;
	
public class BankingSystemApp {
    public static void main(String[] args) {
        BankingService bankingService = new BankingServiceImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nBanking System");
            System.out.println("1. Add Customers");
            System.out.println("2. Add Accounts");
            System.out.println("3. Add Beneficiary");
            System.out.println("4. Add Transaction");
            System.out.println("5. Find Customer by Id");
            System.out.println("6. List all Accounts of specific Customer");
            System.out.println("7. List all Transactions of specific Account");
            System.out.println("8. List all Beneficiaries of specific Customer");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int customerID = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();
                    bankingService.addCustomer(new Customer(customerID, name, address, contact));
                    break;

                case 2:
                    System.out.print("Enter Account ID: ");
                    int accountID = sc.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int accountCustomerID = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Account Type (Saving/Current): ");
                    String accountType = sc.nextLine();
                    System.out.print("Enter Balance: ");
                    double balance = sc.nextDouble();
                    bankingService.addAccount(new Account(accountID, accountCustomerID, accountType, balance));
                    break;

                case 3:
                    System.out.print("Enter Beneficiary ID: ");
                    int beneficiaryID = sc.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int beneficiaryCustomerID = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Beneficiary Name: ");
                    String beneficiaryName = sc.nextLine();
                    System.out.print("Enter Beneficiary Account Number: ");
                    String beneficiaryAccountNumber = sc.nextLine();
                    System.out.print("Enter Beneficiary Bank Details: ");
                    String beneficiaryBankDetails = sc.nextLine();
                    bankingService.addBeneficiary(new Beneficiary(beneficiaryID, beneficiaryCustomerID, beneficiaryName, beneficiaryAccountNumber, beneficiaryBankDetails));
                    break;

                case 4:
                    System.out.print("Enter Account ID: ");
                    int transactionAccountID = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Transaction Type (Deposit/Withdrawal): ");
                    String transactionType = sc.nextLine();
                    System.out.print("Enter Transaction Amount: ");
                    double transactionAmount = sc.nextDouble();
                    bankingService.addTransaction(new Transaction(transactionAccountID, transactionType, transactionAmount));
                    break;

                case 5:
                    System.out.print("Enter Customer ID: ");
                    int searchCustomerID = sc.nextInt();
                    Customer customer = bankingService.findCustomerById(searchCustomerID);
                    if (customer != null) {
                        System.out.println(customer);
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 6:
                    System.out.print("Enter Customer ID: ");
                    int accountsCustomerID = sc.nextInt();
                    List<Account> accounts = bankingService.getAccountsByCustomerId(accountsCustomerID);
                    accounts.forEach(System.out::println);
                    break;

                case 7:
                    System.out.print("Enter Account ID: ");
                    int transactionsAccountID = sc.nextInt();
                    List<Transaction> transactions = bankingService.getTransactionsByAccountId(transactionsAccountID);
                    transactions.forEach(System.out::println);
                    break;

                case 8:
                    System.out.print("Enter Customer ID: ");
                    int beneficiariesCustomerID = sc.nextInt();
                    List<Beneficiary> beneficiaries = bankingService.getBeneficiariesByCustomerId(beneficiariesCustomerID);
                    beneficiaries.forEach(System.out::println);
                    break;

                case 9:
                    System.out.println("Thank you for using the Banking System!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
