package medicaldoctor.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import medicaldoctor.core.AppSession;
import medicaldoctor.core.DatabaseScope;
import medicaldoctor.entities.Patient;
import medicaldoctor.entities.User;
import medicaldoctor.util.InitializeException;

public class UserLookupController implements Initializable{
    
    @FXML
    private TextField first_name_tf, last_name_tf, email_tf, phone_tf, office_tf;
    
    @FXML
    private TableView<User> table;
    
    @FXML
    private TableColumn<Patient, String> c_first_name, c_last_name, c_email,c_department;
    
    @FXML
    private TableColumn<Patient, Integer> c_office;
    
    private List<User> user;
    
    private void getTableResults(User user){
        table.getItems().clear();
        c_first_name.setCellValueFactory(new PropertyValueFactory("firstName"));
        c_last_name.setCellValueFactory(new PropertyValueFactory("lastName"));
        c_email.setCellValueFactory(new PropertyValueFactory("email"));
        c_office.setCellValueFactory(new PropertyValueFactory("officeNum"));
        c_department.setCellValueFactory(new PropertyValueFactory("department"));
        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(user);
        table.setItems(data);
        table.refresh();
    }
    
    void search(TextField tf, int i){
        if(!tf.getText().isEmpty()){
            if(user.get(i).getFirstName().toLowerCase().equals(tf.getText().toLowerCase()))
                getTableResults(user.get(i));
            if(user.get(i).getLastName().toLowerCase().equals(tf.getText().toLowerCase()))
                getTableResults(user.get(i));
            if(user.get(i).getEmail().toLowerCase().equals(tf.getText().toLowerCase()))
                getTableResults(user.get(i));
        }
    }
    
    void search(TextField tf, int i, int j){
        if(!tf.getText().isEmpty()){
            if(user.get(i).getOfficeNum() == Integer.parseInt(office_tf.getText())){
                getTableResults(user.get(i));
            }
        }
    }
    
    @FXML
    void employee_search(ActionEvent event) throws Exception{
        for(int i = 0; i < user.size(); i++){
            search(first_name_tf,i);
            search(last_name_tf,i);
            search(email_tf,i);
            search(phone_tf,i);
            search(office_tf,i,0);
        } 
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try (DatabaseScope scope = new DatabaseScope()) {
            user = User.getAll();
        } catch (Exception ex) {
           throw new InitializeException(ex);
        }
        
        c_first_name.setCellValueFactory(new PropertyValueFactory("firstName"));
        c_last_name.setCellValueFactory(new PropertyValueFactory("lastName"));
        c_email.setCellValueFactory(new PropertyValueFactory("email"));
        c_office.setCellValueFactory(new PropertyValueFactory("officeNum"));
        c_department.setCellValueFactory(new PropertyValueFactory("department"));
        ObservableList<User> data = FXCollections.<User>observableArrayList();
        data.addAll(user);
        table.setItems(data);
        table.refresh();
        
        table.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    User rowData = row.getItem();
                    AppSession.setViewingUserSelection(rowData);
                    try {
                        AppSession.CONTROLLER_MANAGER.loadAndShowScreen(LookUp.PROFILE);
                    } catch (Exception ex) {
                        Logger.getLogger(UserLookupController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });
        
    }
    
    
}
