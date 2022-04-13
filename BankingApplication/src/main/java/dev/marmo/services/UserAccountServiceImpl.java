package dev.marmo.services;

import dev.marmo.data.UserAccountDAO;
import dev.marmo.entities.UserAccount;

import java.util.List;

public class UserAccountServiceImpl implements UserAccountService {

    private UserAccountDAO userAccountDAO;

    //dependency injection. Building an object that uses another object within it
    public UserAccountServiceImpl(UserAccountDAO userAccountDAO ){
        this.userAccountDAO = userAccountDAO;
    }


    @Override
    public UserAccount createAccount(UserAccount account) {
        return this.userAccountDAO.createUserAccount(account);
    }

    @Override
    public UserAccount makeDeposit(UserAccount account, double moneyToDeposit) {


        account.setBalance(account.getBalance() + moneyToDeposit);

        this.userAccountDAO.updateAccount(account);
        return account;
    }

    @Override
    public UserAccount makeWithdrawal(UserAccount account, double amountToWithdraw) {
        account.setBalance(account.getBalance()- amountToWithdraw);
        this.userAccountDAO.updateAccount(account);

        return account;
    }

    @Override
    public UserAccount login(String userName, String password) {
      return userAccountDAO.getAccountById(userAccountDAO.getLogin(userName, password));

//        String login = UserAccountDAO.getLogin(testAccount.getUserName(),testAccount.getPassword());

    }

//    @Override
//    public boolean login(String userName) {
//        return userAccountDAO.getLogin(userName);
//    }

    @Override
    public List<UserAccount> allAccounts() {
        return this.userAccountDAO.getAllAccounts();
    }

    @Override
    public void printBalance(UserAccount account) {
        System.out.println("Your balance is " + account.getBalance() + " and your account ID is "+ account.getUserId());

    }
}
