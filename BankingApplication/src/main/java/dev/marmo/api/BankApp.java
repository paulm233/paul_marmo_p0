package dev.marmo.api;

import dev.marmo.data.UserAccountDAOPostgresImpl;
import dev.marmo.entities.UserAccount;
import dev.marmo.services.UserAccountService;
import dev.marmo.services.UserAccountServiceImpl;

import java.util.Scanner;

public class BankApp {

    public static UserAccountService userAccountService = new UserAccountServiceImpl(new UserAccountDAOPostgresImpl());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the United Bank Services (UBS) terminal");
        System.out.println("1. Create new Account \n2. login to existing account");
        System.out.println("Input Choice");
        int input = scanner.nextInt();
        scanner.nextLine();

        switch (input){
            case 1: {
                System.out.println("We are glad to hear you are joining the family!");
                System.out.println("Please enter your first name :");
                String fName = scanner.nextLine();
                System.out.println("Please enter your last name :");
                String lName = scanner.nextLine();
                System.out.println("Please enter your desired username :");
                String userName = scanner.nextLine();
                System.out.println("Please enter the password you would like to set");
                String password = scanner.nextLine();
                UserAccount account = new UserAccount(0, fName, lName, userName, password, 0);
                BankApp.userAccountService.createAccount(account);
                System.out.println("Congratulations! Your new account has been created!" + account);
            }break;

            case 2:{
                System.out.println("Login Terminal");
                System.out.println("--------------------------------");
                System.out.println("Enter your username: \n");
                String accountUserName = scanner.nextLine();
                System.out.println("Enter your password: \n");
                String accountPassword = scanner.nextLine();
                UserAccount b = BankApp.userAccountService.login(accountUserName,accountPassword);
                System.out.println("Your balance is: $" + b.getBalance()+ " . Your account number is " +  b.getUserId());

                System.out.println("1.Make deposit. \n 2. Withdrawal");
                int input2 = scanner.nextInt();
                scanner.nextLine();
                switch(input2){

                    case 1:
                        System.out.println("Enter the amount you'd like to deposit");
                        Double amount = scanner.nextDouble();
                        UserAccount a = userAccountService.makeDeposit(b,amount);
                        System.out.println("Your balance is now: " + a.getBalance() + " Thanks for your deposit.");
                        break;
                    case 2:
                        System.out.println("Enter the amount you'd like to withdraw");
                        Double amount1 = scanner.nextDouble();
                        UserAccount j = userAccountService.makeWithdrawal(b,amount1);
                        System.out.println("Your balance is now: " + j.getBalance() );
                        break;

            }}break;
            default:
                System.out.println("Invalid Choice");
        }



    }
}
