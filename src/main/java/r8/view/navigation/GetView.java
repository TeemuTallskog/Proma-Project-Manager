package r8.view.navigation;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import r8.App;
import r8.util.TextLoader;


import java.net.URL;
import java.util.ResourceBundle;

public class GetView {

    private Pane view;

    public Pane getView(String viewName) {
        FXMLLoader loader = new FXMLLoader();
        try {
            ResourceBundle resourceBundle = TextLoader.getInstance().getBundle();
            URL viewUrl = getClass().getResource("/fxml/" + viewName + ".fxml");
            if (viewUrl == null) {
                throw new java.io.FileNotFoundException(viewName + ".fxml not found");
            }
            loader.setLocation(viewUrl);
            loader.setResources(resourceBundle);
            view = loader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}

