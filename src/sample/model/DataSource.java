package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataSource {
    public static final String DB_NAME = "hematology.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\" + DB_NAME;

    public static final String TABLE_HEMATOLOGY = "hematology";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_RBC = "rbc";
    public static final String COLUMN_MCV = "mcv";
    public static final String COLUMN_HCT = "hct";
    public static final String COLUMN_HGB = "hgb";
    public static final String COLUMN_MCH = "mch";
    public static final String COLUMN_MCHC = "mchc";
    public static final String COLUMN_PLT = "plt";
    public static final String COLUMN_WBC = "wbc";
    public static final String COLUMN_LOT = "lot";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HEMATOLOGY +
            " (" + COLUMN_ID + " integer PRIMARY KEY, " +
                   COLUMN_LOT + " text, " +
                   COLUMN_DATE + " date, " +
                   COLUMN_RBC + " double, " +
                   COLUMN_MCV + " double, " +
                   COLUMN_HCT + " double, " +
                   COLUMN_HGB + " double, " +
                   COLUMN_MCH + " double, " +
                   COLUMN_MCHC + " double, " +
                   COLUMN_PLT + " double, " +
                   COLUMN_WBC + " double" +
            ")";

    public static final String SHOW_TABLE = "SELECT " +
            COLUMN_DATE + ", " + COLUMN_RBC + ", " +
            COLUMN_MCV + ", " + COLUMN_HCT + ", " +
            COLUMN_HGB + ", " + COLUMN_MCH + ", " +
            COLUMN_MCHC + ", " + COLUMN_PLT + ", " +
            COLUMN_WBC + " FROM " + TABLE_HEMATOLOGY;

    public static final String INSERT_ROW = "INSERT INTO " + TABLE_HEMATOLOGY +
            " (" + COLUMN_DATE + ", " + COLUMN_RBC + ", " +
            COLUMN_MCV + ", " + COLUMN_HCT + ", " +
            COLUMN_HGB + ", " + COLUMN_MCH + ", " +
            COLUMN_MCHC + ", " + COLUMN_PLT + ", " +
            COLUMN_WBC + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String DELETE_ROW = "DELETE FROM " + TABLE_HEMATOLOGY + " WHERE " + COLUMN_DATE + " = ?";
    public static final String UPDATE_RBC = "UPDATE " + TABLE_HEMATOLOGY + " SET " + COLUMN_RBC
            + " = ? WHERE " + COLUMN_DATE + " = ?";

    private Connection con;
    private static DataSource instance = new DataSource();
    private PreparedStatement insertRow;
    private PreparedStatement deleteRow;
    private PreparedStatement updateRBC;

    private DataSource() {

    }

    public static DataSource getInstance() {
        return instance;
    }

    public boolean open() {
        try{
            con = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = con.createStatement();
            statement.execute(CREATE_TABLE);
            insertRow = con.prepareStatement(INSERT_ROW);
            deleteRow = con.prepareStatement(DELETE_ROW);
            updateRBC = con.prepareStatement(UPDATE_RBC);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            deleteRow.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
        try {
            updateRBC.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
        try {
            insertRow.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
        }
    }

    public ObservableList<Hematology> showTable() throws ParseException {
        try(Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SHOW_TABLE)) {

            ObservableList<Hematology> hematologyObservableList =  FXCollections.observableArrayList();
            while (rs.next()) {
                Hematology hematology = new Hematology();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                hematology.setDate(LocalDate.parse(rs.getString(1), formatter));
                hematology.setRbc(rs.getInt(2));
                hematology.setHct(rs.getInt(3));
                hematology.setMcv(rs.getInt(4));
                hematology.setHgb(rs.getInt(5));
                hematology.setMch(rs.getInt(6));
                hematology.setMchc(rs.getInt(7));
                hematology.setPlt(rs.getInt(8));
                hematology.setWbc(rs.getInt(9));
                hematologyObservableList.add(hematology);
            }
            return hematologyObservableList;

        } catch (SQLException e) {
            System.out.println("Failed: " + e.getMessage());
            return null;
        }
    }

    public void insert(String date, int rbc, int hct, int mcv, int hgb, int mch, int mchc, int plt, int wbc) throws SQLException {
        insertRow.setString(1, date);
        insertRow.setInt(2, rbc);
        insertRow.setInt(3, hct);
        insertRow.setInt(4, mcv);
        insertRow.setInt(5, hgb);
        insertRow.setInt(6, mch);
        insertRow.setInt(7, mchc);
        insertRow.setInt(8, plt);
        insertRow.setInt(9, wbc);

        insertRow.executeUpdate();
    }

    public void delete(String date) {
        try {
            deleteRow.setString(1, date);
            deleteRow.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Couldn't delete selected row");
        }
    }

    public void update(String date, int rbc) {
        try {
            updateRBC.setInt(1, rbc);
            updateRBC.setString(2, date);
            updateRBC.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Couldn't update rbc value");
        }
    }
}
