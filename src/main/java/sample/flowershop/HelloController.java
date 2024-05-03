package sample.flowershop;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {

    @FXML
    private TextField lg_inputLogin;

    @FXML
    private PasswordField lg_inputPass;

    @FXML
    private Button lg_loginBtn;

    @FXML
    private AnchorPane lg_right;

    @FXML
    private AnchorPane register;

    @FXML
    private TextField rg_answer;

    @FXML
    private TextField rg_loginBtn;

    @FXML
    private PasswordField rg_passwordBtn;

    @FXML
    private ComboBox<?> rg_question;

    @FXML
    private Button rg_singUpBtn;

    @FXML
    private AnchorPane side;

    @FXML
    private Button side_alrHavAcc;

    @FXML
    private Button side_createAccBtn;

    private Stage stage;
    private Parent root;
    private Scene scene;
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void swithcSceneToMain(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключения
        }
    }


    public void switchSides(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == side_createAccBtn) {
            slider.setNode(side);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alrHavAcc.setVisible(true);
                side_createAccBtn.setVisible(false);
            });

            slider.play();
        } else if(event.getSource() == side_alrHavAcc) {
            slider.setNode(side);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alrHavAcc.setVisible(false);
                side_createAccBtn.setVisible(true);
            });

            slider.play();
        }
    }

    public void initialize() {
        AtomicInteger loggedInUserId = new AtomicInteger(-1);
        DatabaseHandler dbHandler = new DatabaseHandler();
        lg_loginBtn.setOnAction(actionEvent -> {
            String loginText = lg_inputLogin.getText().trim();
            String passwordText = lg_inputPass.getText().trim();
            try {
                if(loginUser(loginText,passwordText)){
                    int userId = dbHandler.getUserId(loginText, passwordText);
                    loggedInUserId.set(userId);
                    swithcSceneToMain(actionEvent);
                }
                else{
                    showMessage("Неверные данные пользователя!");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        rg_singUpBtn.setOnAction(actionEvent -> {
            try {
                dbHandler.signUpUser(rg_loginBtn.getText(),rg_passwordBtn.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            showMessage("Пользователь успешно создан! Авторизуйтесь, чтобы войти.");
            switchSides(actionEvent);
        });
    }

        private boolean loginUser(String loginText, String passwordText) throws SQLException, ClassNotFoundException {
            DatabaseHandler dbHandler = new DatabaseHandler();
            User user = new User();
            user.setUsername(loginText);
            user.setPassword(passwordText);
            ResultSet result= dbHandler.getUser(user);
            int counter = 0;
            while(result.next()){
                counter++;
            }
            return counter >= 1;
        }
}