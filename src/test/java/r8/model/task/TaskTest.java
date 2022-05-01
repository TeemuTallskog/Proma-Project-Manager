package r8.model.task;

import org.junit.jupiter.api.*;
import r8.model.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskTest {

    private static Task task;
    private static TaskType taskType;
    private static Project project;
    private static Team team1, team2, team3;
    private static Account account, account2, account3;
    private static Sprint sprint;
    private static Event event, event2;

    @BeforeAll
    static void setUpBeforeTesting() {
        taskType = new TaskType("test");
        task = new Task("task", TaskState.NOT_STARTED, taskType, 48, "desc");
        project = new Project("project", "desc");
        team1 = new Team("team", project);
        team2 = new Team("team2", project);
        team3 = new Team("team3", project);
        account = new Account("etunimi", "sukunimi", "email", "pwd");
        account2 = new Account("etunimi2", "sukunimi2", "email2", "pwd2");
        account3 = new Account("etunimi3", "sukunimi3", "email3", "pwd3");
        sprint = new Sprint();
    }

    @Test
    @Order(1)
    void assignToTeam() {
        Set<Task> tasks = new HashSet<>();
        Set<Team> teams = new HashSet<>();
        tasks.add(task);
        team1.setTasks(tasks);
        teams.add(team1);
        task.setTeams(teams);
        assertTrue(team1.getTasks().contains(task), "Taskin lisääminen tiimille epäonnistui (ei löydy tiimin task-listasta)");
        assertTrue(task.getTeams().contains(team1), "Taskin lisääminen tiimille epäonnistui (ei löytynyt omasta tiimi-listasta)");
    }


    @Test
    @Order(2)
    void removeTeam() {
        task.removeTeam(team1);
        assertFalse(team1.getTasks().contains(task), "Taskin poistaminen tiimiltä epäonnistui (löytyy tiimin task-listasta)");
        assertFalse(task.getTeams().contains(team1), "Taskin poistaminen tiimilte epäonnistui (löytyy omasta tiimi-listasta)");
    }

    @Test
    @Order(3)
    void assignAccount() {
        Set<Account> accountsToSet = new HashSet<>();
        accountsToSet.add(account);
        task.setAccounts(accountsToSet);
        assertTrue(task.getAccounts().contains(account), "Käyttäjätilin lisääminen taskin listaan epäonnistui");
    }

    @Test
    @Order(5)
    void getName() {
        String name = "task";
        assertEquals(name, task.getName(), "Palautettu nimi on virheellinen");
    }

    @Test
    @Order(6)
    void setName() {
        String newName = "task new name";
        task.setName(newName);
        assertEquals(newName, task.getName(), "Taskin nimen muuttaminen epäonnistui");
    }

    @Test
    @Order(7)
    void getTaskState() {
        assertNotEquals(TaskState.IN_PROGRESS, task.getTaskState(), "Palautettu taskstate virheellinen");
        assertEquals(TaskState.NOT_STARTED, task.getTaskState(), "Palautettu taskstate virheellinen(2)" );
    }

    @Test
    @Order(8)
    void setTaskState() {
        task.setTaskState(TaskState.DONE);
        task.setTaskStateString(task.getTaskState().toString());
        assertEquals(TaskState.DONE, task.getTaskState(), "Taskstaten muuttaminen epäonnistui");
        assertNotEquals(TaskState.NOT_STARTED, task.getTaskState(), "Taskstaten muuttaminen epäonnistui(2)");
    }

    @Test
    @Order(9)
    void getTaskStateString() {
        assertEquals(TaskState.DONE.toString(), task.getTaskStateString(), "Taskstate palautettu virheellisenä merkkijonona");
    }

    @Test
    @Order(10)
    void setTaskStateString() {
        task.setTaskStateString(TaskState.READY.toString());
        task.setTaskState(TaskState.READY);
        assertEquals(TaskState.READY, task.getTaskState(), "TaskStaten muuttaminen setTaskStateString()-metodin+avulla epäonnistui");
    }

    @Test
    @Order(11)
    void setAndGetTaskType() {
        task.setTaskType(taskType);
        assertEquals(taskType, task.getTaskType(), "TaskTypen asettaminen/hakeminen epäonnistui");
    }

    @Test
    @Order(12)
    void setAndGetDescription() {
        String oldDesc = "desc";
        assertEquals(oldDesc, task.getDescription(), "Descin hakeminen epäonnistui");
        String newDesc = "new descpription";
        task.setDescription(newDesc);
        assertEquals(newDesc, task.getDescription(), "Descin muuttaminen epäonnistui");
        assertNotEquals(oldDesc, task.getDescription(), "Descin muuttaminen epäonnistui - palauttaa vanhan descin uuden sijaan");
    }

    @Test
    @Order(13)
    void getHours() {
        assertEquals(48, task.getHours(), "Palauttaa väärän tuntimäärän");
    }

    @Test
    @Order(14)
    void setHours() {
        task.setHours(50);
        assertEquals(50, task.getHours(), "Tuntimäärän muuttaminen epäonnistui");
        assertNotEquals(48, task.getHours(), "Tuntimäärän muuttminen epäonnistui(2)");
    }

    @Test
    @Order(15)
    void setAndGetStartDate() {
        task.setStartDate(LocalDate.now().plusDays(2));
        assertEquals(LocalDate.now().plusDays(2), task.getStartDate(), "Alkupvm asettaminen epäonnistui");
    }

    @Test
    @Order(16)
    void setAndGetEndDate() {
        task.setEndDate(LocalDate.now().plusDays(16));
        assertEquals(LocalDate.now().plusDays(16), task.getEndDate(), "Deadlinen asettaminen epäonnistui");
    }

    @Test
    @Order(17)
    void setAndGetAccounts() {
        Set<Account> accounts = new HashSet<Account>();
        accounts.add(account);
        accounts.add(account2);
        accounts.add(account3);

        task.setAccounts(accounts);
        assertEquals(accounts, task.getAccounts(), "Käyttäjätilien lisääminen tehtävälle epäonnistui");
    }

    @Test
    @Order(18)
    void setAndGetSprint() {
        sprint.setName("sprint1");
        Set<Sprint> sprintsToSet = new HashSet<>();
        sprintsToSet.add(sprint);
        task.setSprints(sprintsToSet);
        assertTrue(task.getSprints().contains(sprint), "Sprintin asettaminen tehtävälle epäonnistui");
    }

    @Test
    @Order(19)
    void setAndGetProject() {
        task.setProject(project);
        assertEquals(project, task.getProject(), "Projektin asettaminen taskille epäonnistui");
    }

    @Test
    @Order(20)
    void setAndGetTeams() {
        Set<Team> teams = new HashSet<Team>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);

        task.setTeams(teams);
        assertEquals(teams, task.getTeams(), "Tiimien (monikko) lisääminen taskille epäonnistui");
    }

    @Test
    @Order(21)
    void events(){
        event = new Event();
        event2 = new Event();
        Set<Event> events = new HashSet<>();
        events.add(event);
        events.add(event2);
        task.setEvents(events);
        assertEquals(events, task.getEvents(), "Evenetit eivät täsmää");
    }

}