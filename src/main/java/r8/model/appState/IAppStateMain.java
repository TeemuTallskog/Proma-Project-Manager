package r8.model.appState;


import javafx.collections.ObservableList;
import r8.model.*;
import r8.model.task.Task;
import r8.model.task.TaskState;
import r8.model.task.TaskType;
import r8.view.mainView.MainViewController;

import java.util.List;

public interface IAppStateMain {

    MainViewController getMainViewController();

    void setMainViewController(MainViewController mainViewController);

    boolean getIsAdmin();

    void setIsAdmin(boolean isAdmin);

    Account getAccount();

    void createTeam(String name, Project project);

    void createProject(String name, String description, ObservableList<Account> accountList, ObservableList<String> teamList);

    List<Account> getAllAccounts();

    List<Project> getProjects();

    void createTask(String name, TaskState taskState, TaskType taskType, float hours, String desc, ObservableList<Account> accounts, ObservableList<Team> teams, Project project);

    void createTaskType(String name);

    List<TaskType> getAllTaskTypes();

    List<Team> getAllTeams();

    void setSelectedTask(Task task);
    Task getSelectedTask();
    void updateTask(Task task);

    void setSelectedProject(Project project);
    Project getSelectedProject();

    List<r8.model.Event> getEvents();

    List<Sprint> getSprints();
}
