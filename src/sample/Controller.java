package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.dataModel.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {
    @FXML
    private Button buttonNumber1, buttonNumber2, buttonNumber3,buttonNumber4, buttonNumber5, buttonNumber6, buttonNumber7, buttonNumber8, buttonNumber9, buttonNumber0, dot;

    @FXML
    private Button operatorPlus, operatorMinus, operatorMultiply, operatorDivide, minButton;

    @FXML
    private TextField textField, previousExpField;

    @FXML
    private BorderPane borderPane;

    private Data data;

    @FXML
    public void initialize(){
        data = new Data();
    }

    @FXML
    public void handleNumKeysPressed(ActionEvent e){
        if (e.getSource().equals(buttonNumber0)){
            textField.appendText("0");
        }else if (e.getSource().equals(buttonNumber1)){
            textField.appendText("1");
        }else if (e.getSource().equals(buttonNumber2)){
            textField.appendText("2");
        }else if (e.getSource().equals(buttonNumber3)){
            textField.appendText("3");
        }else if (e.getSource().equals(buttonNumber4)){
            textField.appendText("4");
        }else if (e.getSource().equals(buttonNumber5)){
            textField.appendText("5");
        }else if (e.getSource().equals(buttonNumber6)){
            textField.appendText("6");
        }else if (e.getSource().equals(buttonNumber7)){
            textField.appendText("7");
        }else if (e.getSource().equals(buttonNumber8)){
            textField.appendText("8");
        }else if (e.getSource().equals(buttonNumber9)){
            textField.appendText("9");
        }else if (e.getSource().equals(dot)){
            textField.appendText(".");
        }else if (e.getSource().equals(operatorPlus)){
            textField.appendText("+");
        }else if (e.getSource().equals(operatorMinus)){
            textField.appendText("-");
        }else if (e.getSource().equals(operatorMultiply)){
            textField.appendText("*");
        }else if (e.getSource().equals(operatorDivide)){
            textField.appendText("/");
        }
    }

    @FXML
    public void calculate(){
        List<String> value = new ArrayList<>();
        String exp = textField.getText();
        String operator = "";
        int start = 0, end;

        if(!(textField.getText().trim().isEmpty())){
            for(int i=0; i<exp.length(); i++){

                if(i != (exp.length()-1)){
                    if (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/'){
                        operator += exp.charAt(i);
                        end = i;
                        value.add(exp.substring(start, end));
                        start = i+1;
                    }
                }else{
                    value.add(exp.substring(start, exp.length()));
                }

            }

            double result = solve(value, operator);
            data.addData(textField.getText(), result+"");
            data.loadData();
            previousExpField.setText(textField.getText()+" = "+result);
            textField.clear();
            textField.setText(""+result);
        }
    }

    private double solve(List<String> value, String operator){
        double result = Double.parseDouble(value.get(0));
        int j = 1;
        for(int i=0; i<operator.length(); i++){

            switch (operator.charAt(i)){
                case '+':
                    result += Double.parseDouble(value.get(j));
                    break;
                case '-':
                    result -= Double.parseDouble(value.get(j));
                    break;
                case '*':
                    result *= Double.parseDouble(value.get(j));
                    break;
                case '/':
                    result /= Double.parseDouble(value.get(j));
                    break;
            }
            j++;
        }

        return result;
    }

    @FXML
    public void Clear(){
        textField.clear();
    }

    @FXML
    public void handleControl(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(minButton)){
            Stage stage = (Stage) borderPane.getScene().getWindow();
            stage.setIconified(true);

        }else{
            Platform.exit();
        }
    }

    @FXML
    public void showPreviousResult(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Previous Results");
        try{
            Parent root = FXMLLoader.load(getClass().getResource("previousResultDialog.fxml"));
            dialog.getDialogPane().setContent(root);

        }catch (IOException e){
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Ok pressed");
        }
    }
}
