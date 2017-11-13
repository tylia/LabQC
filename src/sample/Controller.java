package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.DataSource;
import sample.model.Hematology;

import javax.swing.text.TabableView;
import java.beans.EventHandler;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private DatePicker datePicker;
    @FXML
    private TextField rbcField;
    @FXML
    private TextField hctField;
    @FXML
    private TextField mcvField;
    @FXML
    private TextField hgbField;
    @FXML
    private TextField mchField;
    @FXML
    private TextField mchcField;
    @FXML
    private TextField pltField;
    @FXML
    private TextField wbcField;

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

    @FXML
    public void insert() throws SQLException, ParseException {
        int rbc = Integer.parseInt(rbcField.getText());
        int mcv = Integer.parseInt(mcvField.getText());
        int hct = Integer.parseInt(hctField.getText());
        int hgb = Integer.parseInt(hgbField.getText());
        int mch = Integer.parseInt(mchField.getText());
        int mchc = Integer.parseInt(mchcField.getText());
        int plt = Integer.parseInt(pltField.getText());
        int wbc = Integer.parseInt(wbcField.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = datePicker.getValue().format(formatter);

        DataSource.getInstance().insert(date, rbc, mcv, hct, hgb, mch, mchc, plt, wbc);
        clearFieldsAfterSubmitting();

        hematologyTable.setItems(DataSource.getInstance().showTable());
    }

    private void clearFieldsAfterSubmitting() {
        rbcField.clear();
        hctField.clear();
        mcvField.clear();
        hgbField.clear();
        mchField.clear();
        mchcField.clear();
        pltField.clear();
        wbcField.clear();
    }

}
