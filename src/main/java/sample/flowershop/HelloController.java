package sample.flowershop;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    public void swithcSceneToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private String[] questionList = {"What is your favorite color", "What is your favorite food", "What is your hobby"};

    public void regQuestionList() {

        ArrayList<String> listQ = new ArrayList<>(Arrays.asList(questionList));

        ObservableList listData = FXCollections.observableArrayList(listQ);
        rg_question.setItems(listData);

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
                regQuestionList();
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

    }
}