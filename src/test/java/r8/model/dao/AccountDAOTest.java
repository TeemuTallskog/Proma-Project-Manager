package r8.model.dao;

import org.junit.jupiter.api.*;
import r8.model.Account;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountDAOTest {

    private static AccountDAO accountDAO;
    private static Account account1, account2, account3, account4;

    @BeforeAll
    static void setUpBeforeTesting() {
        accountDAO = new AccountDAO();
        account1 = new Account("etunimi", "sukunimi", "email", "pwd");
        account2 = new Account("etunimi2", "sukunimi2", "email2", "pwd2");
        account3 = new Account("etunimi3", "sukunimi3", "email3", "pwd3");
        account4 = new Account("etunimi4", "sukunimi4", "email4", "pwd4");


    }

    @Test
    @Order(1)
    void persist(){
        accountDAO.persist(account1);
        accountDAO.persist(account2);
        accountDAO.persist(account3);
        accountDAO.persist(account4);

        List<Account> accountList = accountDAO.getAll();

        assertTrue(accountList.contains(account1), "Tietokannasta ei löytynyt käyttäjätiliä (account1)");
        assertTrue(accountList.contains(account2), "Tietokannasta ei löytynyt käyttäjätiliä (account2)");
        assertTrue(accountList.contains(account3), "Tietokannasta ei löytynyt käyttäjätiliä (account3)");
        assertTrue(accountList.contains(account4), "Tietokannasta ei löytynyt käyttäjätiliä (account3)");

    }

    @Test
    @Order(2)
    void update(){
        account1.setFirstName(account2.getFirstName());
        accountDAO.update(account1);

        assertEquals(account1.getFirstName(), accountDAO.get(account1.getAccountId()).getFirstName(), "Käyttäjätilin muokkaaminen tietokannassa epäonnistui");
    }

    @Test
    @Order(3)
    void get(){
        assertEquals(account2, accountDAO.get(account2.getAccountId()), "Käyttäjätilin hakeminen tietokannasta id:n avulla epäonnistui");
    }


    @Test
    @Order(4)
    void getByEmail(){
        assertEquals(account2, accountDAO.getByEmail(account2.getEmail()), "Käyttäjätilin hakeminen tietokannasta spostin avulla epäonnistui");
    }

    @Test
    @Order(5)
    void getAll(){
        assertTrue(accountDAO.getAll().contains(account1), "Käyttäjälistat eivät täsmää");
        assertTrue(accountDAO.getAll().contains(account2), "Käyttäjälistat eivät täsmää2");
        assertTrue(accountDAO.getAll().contains(account3), "Käyttäjälistat eivät täsmää3");
        assertTrue(accountDAO.getAll().contains(account4), "Käyttäjälistat eivät täsmää4");
    }


    @AfterAll
    static void clearDatabase(){
        accountDAO.remove(account1);
        accountDAO.remove(account2);
        accountDAO.remove(account3);
        accountDAO.remove(account4);
        System.out.println("db cleared");
    }

}