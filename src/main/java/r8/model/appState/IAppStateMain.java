package r8.model.appState;


import r8.model.*;
import r8.model.task.Task;


public interface IAppStateMain {

    Account getAccount();

    boolean getIsAdmin();

    void setIsAdmin(boolean isAdmin);

    boolean getTooltipsEnabled();

    void setTooltipsEnabled(boolean tooltipsEnabled);

    Task getSelectedTask();

    void setSelectedTask(Task task);

    void setSelectedProject(Project project);

    Project getSelectedProject();

}
