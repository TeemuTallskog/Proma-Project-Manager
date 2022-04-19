package r8.model;

import org.hibernate.annotations.Cascade;
import r8.model.task.Task;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author sanku, Joona Nylander
 *
 */

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private int projectId;

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(
			name = "project_account",
			joinColumns = @JoinColumn(name = "project_id"),
			inverseJoinColumns = @JoinColumn(name = "account_id"))
	private Set<Account> accounts = new HashSet<>();

	@Column(name = "description")
	private String description;

	// Removing a project removes all tasks it
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Task> tasks = new HashSet<>();
	// Removing a project removes all teams under
	@OneToMany(mappedBy = "project", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<Team> teams = new HashSet<>();

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Sprint> sprints = new HashSet<>();

	/**
	 * Contructor
	 * @param name Project's name
	 */
	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Project() {}

	public void addAccount(Account account) {
		this.accounts.add(account);
		account.getProjects().remove(this);
	}

	public void removeAccount(Account account) {
		this.accounts.remove(account);
		account.getProjects().remove(this);
	}

	public void addTask(Task task) {
		this.tasks.add(task);
		task.setProject(this);
	}

	public void removeTask(Task task) {
		this.tasks.remove(task);
		task.setProject(null);
	}

	public void addTeam(Team team) {
		this.teams.add(team);
		team.setProject(this);
	}

	public void removeProject(Team team) {
		this.teams.remove(team);
		team.setProject(null);
	}

	public void addSprint(Sprint sprint) {
		sprints.add(sprint);
		sprint.setProject(this);
	}

	public void removeSprint(Sprint sprint) {
		sprints.remove(sprint);
		sprint.setProject(null);
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(Set<Sprint> sprints) {
		this.sprints = sprints;
	}

	@Override
	public String toString() {
		return this.projectId + " " + this.name;
	}
}
