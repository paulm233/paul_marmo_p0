package dev.marmo.daotests;

import dev.marmo.data.UserAccountDAO;
import dev.marmo.data.UserAccountDAOPostgresImpl;
import dev.marmo.entities.UserAccount;
import org.junit.jupiter.api.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAccountDAOTest {

    static UserAccountDAO UserAccountDAO = new UserAccountDAOPostgresImpl();
    static UserAccount testAccount = null;


    @Test
   @Order(1)
    void create_account_test(){
        // An entity that is created but not yet saved should have an id of 0
        //once saved that book should be some non-zero value


        UserAccount bobross = new UserAccount(0, "Bob", "Ross", "bobross123", "letsgopaint", 0);
        UserAccount savedAccount = UserAccountDAO.createUserAccount(bobross);
        UserAccountDAOTest.testAccount = savedAccount;
        Assertions.assertNotEquals(0, savedAccount.getUserId());


    }
//come back to this
    @Test
    @Order(2)
    void get_account_by_id(){
        UserAccount retrievedUser = UserAccountDAO.getAccountById(testAccount.getUserId());
       // UserAccount retrievedUser = UserAccountDAO.getAccountById(6);
        System.out.println(retrievedUser);
        Assertions.assertEquals("bobross123", retrievedUser.getUserName());
    }

    @Test
    @Order(3)
    void create_login(){
        UserAccountDAOTest.testAccount.setUserName("username1");
        testAccount.setPassword("passwordTesting");
        UserAccount bobross2 = UserAccountDAO.createLogin(testAccount);
        Assertions.assertEquals(bobross2.getUserName(),testAccount.getUserName());
    }

//    @Test
//    @Order(4)
//    void get_login_from_credential(){
//        String login = UserAccountDAO.getLogin(testAccount.getUserName(),testAccount.getPassword());
//        Assertions.assertNotEquals(null,login);
//    }


    @Test
    @Order(4)
    void update_account(){
        UserAccountDAOTest.testAccount.setFirstName("Jeff");
        UserAccountDAO.updateAccount(testAccount); //the new name should be saved to the

        UserAccount retrievedUser = UserAccountDAO.getAccountById(testAccount.getUserId());
        Assertions.assertEquals("Jeff", retrievedUser.getFirstName());

    }

    @Test
    @Order(5)
    void delete_account_by_id(){
        boolean result = UserAccountDAO.deleteAccountByID(testAccount.getUserId()); //true if successful
        Assertions.assertTrue(result);

    }

    @Test
    @Order(6)
    void get_all_accounts(){
        UserAccount a = new UserAccount(0,"A","B","C","D",0);
        UserAccount b = new UserAccount(0,"A","B","C","D",0);
        UserAccount c = new UserAccount(0,"A","B","C","D",0);
        UserAccountDAO.createUserAccount(a);
        UserAccountDAO.createUserAccount(b);
        UserAccountDAO.createUserAccount(c);
        List<UserAccount> accounts = UserAccountDAO.getAllAccounts();
        int totalAccounts = accounts.size();
        Assertions.assertTrue(totalAccounts>= 3);
        //System.out.println(accounts.get(12));

    }



}
