package r8.model;

import org.junit.jupiter.api.*;
import r8.model.task.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventTest {

    private static Event event;
    private static Task task;
    private static Account account;

    @BeforeAll
    static void setUpBeforeTesting() {
        task = new Task();
        account = new Account("etunimi", "sukunimi", "email", "pwd");
        event = new Event("desc", LocalDate.now(), 2, account);
    }

    @Test
    @Order(1)
    void setAdnGetDescription() {
        String oldDesc = event.getDescription();
        String newDesc = "new desc";

        event.setDescription(newDesc);
        assertEquals(newDesc, event.getDescription(), "Descin muuttaminen epäonnistui");
        assertNotEquals(oldDesc, event.getDescription(), "Descin muuttaminen epäonnistui (2)");
        assertNotEquals("desc", event.getDescription(), "Descin muuttaminen epäonnistui (3)");
    }

    @Test
    @Order(2)
    void setAndGetDate() {
        assertEquals(LocalDate.now(), event.getDate(), "Päivämäärän hakeminen epäonnistui");
        event.setDate(LocalDate.now().plusDays(5));
        assertEquals(LocalDate.now().plusDays(5), event.getDate(), "Päivämäärän muuttaminen epäonnistui");
        assertNotEquals(LocalDate.now(), event.getDate(), "Päivämäärän muuttaminen epäonnistui(2)");
    }

    @Test
    @Order(3)
    void setAndGetHours() {
        float h = event.getHours();
        event.setHours(h + 2);
        float newH = h + 2;
        assertEquals(newH, event.getHours(), "Tuntimäärän muuttaminen epäonnistui");
        assertNotEquals(h, event.getHours(), "Tuntimäärän muuttaminen epäonnistui (2)");
    }

    @Test
    @Order(4)
    void setAndGetAccount() {
        event.setAccount(account);
        Account a2 = new Account("a", "b", "c", "d");
        assertEquals(account, event.getAccount(), "Käyttäjätilin asetttaminen epäonnistui");
        assertNotEquals(a2, event.getAccount(), "Palautti väärän käyttäjätilin");
        event.setAccount(a2);
        assertEquals(a2, event.getAccount(), "Käyttäjätilin muuttaminen epäonnistui");
    }

    @Test
    @Order(5)
    void setAndGetTask() {
        event.setTask(task);
        assertEquals(task, event.getTask(), "Tehtävän hakeminen epäonnistui");
        Task t2 = new Task();
        t2.setName("Task2");
        event.setTask(t2);
        assertEquals(t2, event.getTask(), "Taskin vaihtaminen epäonnistui");
        assertNotEquals(task, event.getTask(), "Taskin vaihtaminen epäonnistui (2)");
    }

    @Test
    @Order(6)
    void firstContructor() {
        String d = "desc";
        Account a = new Account();
        Task t = new Task();

        Event e = new Event(d, a, t);

        assertEquals(d, e.getDescription(), "Desc ei täsmää");
        assertEquals(a, e.getAccount(), "Account ei täsmää");
        assertEquals(t, e.getTask(), "Task ei täsmää");

    }

    @Test
    @Order(7)
    void scndConstructor(){
        String d = "desc";
        LocalDate ld = LocalDate.now();
        float h = 30;
        Account a = new Account();

        Event e = new Event(d, ld, h, a);

        assertEquals(d, e.getDescription(), "Desc ei täsmää");
        assertEquals(ld, e.getDate(), "Pvm ei täsmää");
        assertEquals(h, e.getHours(), "Tunnit eivät täsmää");
        assertEquals(a, e.getAccount(), "Account ei täsmää");

    }
    @Test
    @Order(8)
    void thrdContructor(){
        String d = "desc";
        LocalDate ld = LocalDate.now();
        float h = 30;
        Account a = new Account();
        Task t = new Task();

        Event e = new Event(d, ld, h, a, t);

        assertEquals(d, e.getDescription(), "Desc ei täsmää");
        assertEquals(ld, e.getDate(), "Pvm ei täsmää");
        assertEquals(h, e.getHours(), "Tunnit eivät täsmää");
        assertEquals(a, e.getAccount(), "Account ei täsmää");
        assertEquals(t, e.getTask(), "Task ei täsmää");

    }

    @Test
    @Order(9)
    void frthContructor() {
        String d = "desc";
        LocalDate ld = LocalDate.now();
        float h = 30;
        Account a = new Account();
        Task t = new Task();
        Project p = new Project();

        Event e = new Event(d, ld, h, a, t, p);

        assertEquals(d, e.getDescription(), "Desc ei täsmää");
        assertEquals(ld, e.getDate(), "Pvm ei täsmää");
        assertEquals(h, e.getHours(), "Tunnit eivät täsmää");
        assertEquals(a, e.getAccount(), "Account ei täsmää");
        assertEquals(t, e.getTask(), "Task ei täsmää");
        assertEquals(p, e.getProject(), "Project ei täsmää");

    }

    @Test
    @Order(10)
    void ffthContructor(){
        int eId = 1;
        String d = "desc";
        LocalDate ld = LocalDate.now();
        float h = 30;
        Account a = new Account();
        Task t = new Task();
        Project p = new Project();
        Sprint s = new Sprint();

        Event e = new Event(eId, d, ld, h, a, t, p, s);

        assertEquals(eId, e.getEventId(), "Id ei täsmää");
        assertEquals(d, e.getDescription(), "Desc ei täsmää");
        assertEquals(ld, e.getDate(), "Pvm ei täsmää");
        assertEquals(h, e.getHours(), "Tunnit eivät täsmää");
        assertEquals(a, e.getAccount(), "Account ei täsmää");
        assertEquals(t, e.getTask(), "Task ei täsmää");
        assertEquals(p, e.getProject(), "Project ei täsmää");
        assertEquals(s, e.getSprint(), "Sprint ei täsmää");
    }


}