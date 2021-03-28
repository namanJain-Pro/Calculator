package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.dataModel.Data;

public class PreviousResultDialog {

    @FXML
    private TableView tableView;

    private Data data;

    @FXML
    public void initialize(){
        data = new Data();
        data.loadData();
        tableView.setItems(data.getData());
    }
}
