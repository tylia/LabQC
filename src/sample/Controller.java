package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.DataSource;
import sample.model.Hematology;

import javax.swing.text.TabableView;
import java.text.ParseException;

public class Controller {
    @FXML
    private TableView<Hematology> hematologyTable;
    @FXML
    private TableColumn<Hematology, Integer> dateColumn;
    @FXML
    private TableColumn<Hematology, Integer> rbcColumn;
    @FXML
    private TableColumn<Hematology, Integer> hctColumn;
    @FXML
    private TableColumn<Hematology, Integer> mcvColumn;
    @FXML
    private TableColumn<Hematology, Integer> hgbColumn;
    @FXML
    private TableColumn<Hematology, Integer> mchColumn;
    @FXML
    private TableColumn<Hematology, Integer> mchcColumn;
    @FXML
    private TableColumn<Hematology, Integer> pltColumn;
    @FXML
    private TableColumn<Hematology, Integer> wbcColumn;

    @FXML
    public void initialize() throws ParseException {
        dateColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("date"));
        rbcColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("rbc"));
        mcvColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("mcv"));
        hctColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("hct"));
        hgbColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("hgb"));
        mchColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("mch"));
        mchcColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("mchc"));
        pltColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("plt"));
        wbcColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("wbc"));

        hematologyTable.setItems(DataSource.getInstance().showTable());
    }
}
