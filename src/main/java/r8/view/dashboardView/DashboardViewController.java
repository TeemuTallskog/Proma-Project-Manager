package r8.view.dashboardView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import r8.App;
import r8.model.Account;
import r8.model.appState.AppState;
import r8.model.appState.IAppStateMain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardViewController {

        @FXML
        Label labelUserName;
        @FXML
        Label labelWelcome;
        @FXML
        Label labelSystemTimeDisplay;
        @FXML
        private Button btnCustomizeView;

        IAppStateMain appStateMain = AppState.getInstance();
        private App app;

        private final StringProperty value = new SimpleStringProperty();

        @FXML
        private void initialize() {
            mockData();
        }

        @FXML
        private void navigate(ActionEvent event) throws IOException {
            appStateMain.getMainViewController().handleNavigation(event);
        }

        @FXML
        public StringProperty valueProperty() {
            return value;
        }

        @FXML
        private void setValue (ActionEvent event) {
            final Node eventSource = (Node) event.getSource();
            this.value.set((String) eventSource.getUserData());
            System.out.println("userDate from button" + value);
        }

        public void setMainApp(App app) {
            this.app = app;
        }

        private void mockData() {
            Account account = appStateMain.getAccount();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            labelUserName.setText(account.getFirstName() +" "+ account.getLastName());
            labelSystemTimeDisplay.setText("Today is " + dtf.format(now));
        }
}
