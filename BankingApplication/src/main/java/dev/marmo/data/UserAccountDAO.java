package dev.marmo.data;

import dev.marmo.entities.UserAccount;

import java.util.List;

public interface UserAccountDAO {

    //create
    UserAccount createUserAccount(UserAccount account);

    //create login
    UserAccount createLogin(UserAccount account);

    //get login
    int getLogin(String userName,String password);

    //read
    UserAccount getAccountById(int id);

    List<UserAccount> getAllAccounts();


    //update
    UserAccount updateAccount(UserAccount account);


    //delete
    boolean deleteAccountByID(int id);

    boolean getLogin(String userName);





}
