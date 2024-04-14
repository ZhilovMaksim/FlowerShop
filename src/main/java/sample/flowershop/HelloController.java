package sample.flowershop;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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

    }
}