package sample;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import sample.model.DataSource;
import sample.model.Hematology;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class Controller {
    private ObservableList<Hematology> listHematology;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
    private TableColumn<Hematology, Hematology> deleteColumn;
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

        listHematology = DataSource.getInstance().showTable();
        dateColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("date"));
        rbcColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("rbc"));
        rbcColumn.setCellFactory(TextFieldTableCell.<Hematology, Integer>forTableColumn(new IntegerStringConverter()));
        rbcColumn.setOnEditCommit(event -> {
//            int oldValue = event.getOldValue();
//            int newValue = event.getNewValue();
//            Hematology hematology = event.getRowValue();
//
//            if (newValue != oldValue) {
//
//            }
//            event.getRowValue().setRbc(event.getNewValue());
            int newRbcValue = event.getNewValue();
            String date = event.getRowValue().getDate().format(formatter);
            DataSource.getInstance().update(date, newRbcValue);
        });
        mcvColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("mcv"));
        hctColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("hct"));
        hgbColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("hgb"));
        mchColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("mch"));
        mchcColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("mchc"));
        pltColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("plt"));
        wbcColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Integer>("wbc"));

        deleteColumn.setCellValueFactory(new PropertyValueFactory<Hematology, Hematology>("delete"));
        deleteColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hematology, Hematology>, ObservableValue<Hematology>>() {
            @Override
            public ObservableValue<Hematology> call(TableColumn.CellDataFeatures<Hematology, Hematology> param) {
                return new ReadOnlyObjectWrapper<Hematology>(param.getValue());
            }
        });
        deleteColumn.setCellFactory(new Callback<TableColumn<Hematology, Hematology>, TableCell<Hematology, Hematology>>() {
            @Override
            public TableCell<Hematology, Hematology> call(TableColumn<Hematology, Hematology> deleteColumn) {

                final Button btnDelete = new Button();
                btnDelete.setId("btnDelete");

                TableCell<Hematology, Hematology> cell = new TableCell<Hematology, Hematology>() {
                    @Override
                    protected void updateItem(final Hematology item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnDelete);
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Hematology hematology = cell.getItem();
                        String date = hematology.getDate().format(formatter);
                        DataSource.getInstance().delete(date);
                        listHematology.remove(hematology);
                        hematologyTable.setItems(listHematology);
                    }
                });
                return cell;
            }
        });

        hematologyTable.setItems(listHematology);
    }

    @FXML
    public void insert() throws SQLException, ParseException {
//        DropShadow shadow = new DropShadow();
//        btnAdd.setEffect(shadow);

        int rbc = Integer.parseInt(rbcField.getText());
        int mcv = Integer.parseInt(mcvField.getText());
        int hct = Integer.parseInt(hctField.getText());
        int hgb = Integer.parseInt(hgbField.getText());
        int mch = Integer.parseInt(mchField.getText());
        int mchc = Integer.parseInt(mchcField.getText());
        int plt = Integer.parseInt(pltField.getText());
        int wbc = Integer.parseInt(wbcField.getText());

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
