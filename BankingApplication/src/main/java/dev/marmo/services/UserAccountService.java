package dev.marmo.services;

import dev.marmo.entities.UserAccount;

import java.util.List;

//Business logic methods
//More Bussinessy than pure CRUD operations
//Will use the DAO directly
public interface UserAccountService {


    UserAccount createAccount(UserAccount account);


//do not necessarily need to return anything, could be void
    UserAccount makeDeposit(UserAccount account , double moneyToDeposit);


    UserAccount makeWithdrawal(UserAccount account, double amountToWithdraw);


    UserAccount login(String userName,String password);

   // boolean login(String userName);


    List<UserAccount> allAccounts();

    public void printBalance(UserAccount account);







    //print balance (toString or whatever)


    //transfer

    //might not need
    // boolean updateAccount(Customer customer,String firstName,String lastName);




}
