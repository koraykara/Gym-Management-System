package Queries;

import Controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;



public class SystemUserQueries {
    private Connection conn;
    private Controller mainController;

    public Controller getMainController() {
        return mainController;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;

    public SystemUserQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public int insertSystemUser(String userNameID,String name,String surname, String startDate , String EndDate , String Password
    ){

        String sql = "INSERT INTO System_User( UsernameID,`Name`,Surname, StartDate, EndDate, `Password` )"
                +" VALUES(?,? ,?,?,?,?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, userNameID );
                if(EndDate.isEmpty())
                    preparedStmt.setNull(5, Types.DATE);
                else
                    preparedStmt.setString(5, EndDate);

                preparedStmt.setString(2, name);
                preparedStmt.setString(3, surname);
                preparedStmt.setString(4, startDate);

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(Password.getBytes());

                byte byteData[] = md.digest();

                //Convert "byteData" to hex String:
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < byteData.length; i++) {
                    sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                }


                preparedStmt.setString(6, sb.toString());
                preparedStmt.execute();

                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }
    public void deleteSystemUser(String FieldUserNameID){

        String sql = "delete from `System_User` where UsernameID = ?";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, FieldUserNameID);
                preparedStmt.execute();
                // -------------------------------

                for( ;mainController.getPnTrainerItems().getChildren().size() > 1;){
                    mainController.getPnTrainerItems().getChildren().remove(1);
                }
                mainController.getNorTrainer().setText("0");

                // -------------------------------

                sql = "SELECT * FROM Trainer";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);


                //STEP 5: Extract data from result set

                int i=0;

                ArrayList<Node> nodesTrainer = new ArrayList<>();
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertTrainer.fxml"));

                if(i==0){
                    nodesTrainer.add(fxmlLoaderToAddInsert.load());
                    TrainerItemController trainerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                    trainerItemControllerInsert.setMainController(this.mainController);
                    mainController.getPnTrainerItems().getChildren().add(nodesTrainer.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    final String id  = rs.getString("UsernameID");
                    final String salary = rs.getString("Salary");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/TrainerItem.fxml"));


                    nodesTrainer.add(fxmlLoader.load());
                    TrainerItemController trainerItemController = fxmlLoader.getController();

                    trainerItemController.setMainController(this.mainController);
                    trainerItemController.assignTextLabel(id, salary);

                    mainController.getPnTrainerItems().getChildren().add(nodesTrainer.get(j));
                    i++;
                }
                mainController.getNorTrainer().setText(String.valueOf(i));



                // -------------------------------



                for( ;mainController.getPnItems().getChildren().size() > 1;){
                    mainController.getPnItems().getChildren().remove(1);
                }
                mainController.getNorCustomer().setText("0");
                sql = "SELECT * FROM Customer";
                rs = stmt.executeQuery(sql);
                ArrayList<Node> nodesCustomer = new ArrayList<>();
                //STEP 5: Extract data from result set
                i=0;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertCustomer.fxml"));

                if(i==0){
                    nodesCustomer.add(fxmlLoaderToAddInsert.load());
                    CustomerItemController customerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                    customerItemControllerInsert.setMainController(this.mainController);
                    mainController.getPnItems().getChildren().add(nodesCustomer.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    final String id  = rs.getString("UsernameID");
                    final String membershipTypeName = rs.getString("MembershipTypeName");
                    final String customerTrainerID = rs.getString("TrainerID");
                    final String weight = rs.getString("Weight");
                    final String length = rs.getString("Length");
                    final String age = rs.getString("Age");
                    final String fatRatio = rs.getString("FatRatio");
                    final String creditCardNumber = rs.getString("CreditCardNumber");
                    final String creditCardExpireDate = rs.getString("CreditCardExpireDate");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));


                    nodesCustomer.add(fxmlLoader.load());
                    CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();



                    customerItemController.setMainController(this.mainController);
                    customerItemController.assignTextLabel(id, membershipTypeName, customerTrainerID,
                            weight, length, age, fatRatio, creditCardNumber, creditCardExpireDate);

                    mainController.getPnItems().getChildren().add(nodesCustomer.get(j));
                    i++;
                }
                mainController.getNorCustomer().setText(String.valueOf(i));


////////////


            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateUserNameID(String oldFieldUsernameID,String newFieldUsernameID){
        String sql = "update `System_User` set UsernameID = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldUsernameID);
            preparedStmt.setString(2, oldFieldUsernameID);
            preparedStmt.executeUpdate();
// -------------------------------

            for( ;mainController.getPnTrainerItems().getChildren().size() > 1;){
                mainController.getPnTrainerItems().getChildren().remove(1);
            }
            mainController.getNorTrainer().setText("0");

            // -------------------------------

            sql = "SELECT * FROM Trainer";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            //STEP 5: Extract data from result set

            int i=0;

            ArrayList<Node> nodesTrainer = new ArrayList<>();
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertTrainer.fxml"));

            if(i==0){
                nodesTrainer.add(fxmlLoaderToAddInsert.load());
                TrainerItemController trainerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                trainerItemControllerInsert.setMainController(this.mainController);
                mainController.getPnTrainerItems().getChildren().add(nodesTrainer.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                final String id  = rs.getString("UsernameID");
                final String salary = rs.getString("Salary");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/TrainerItem.fxml"));


                nodesTrainer.add(fxmlLoader.load());
                TrainerItemController trainerItemController = fxmlLoader.getController();

                trainerItemController.setMainController(this.mainController);
                trainerItemController.assignTextLabel(id , salary);

                mainController.getPnTrainerItems().getChildren().add(nodesTrainer.get(j));
                i++;
            }
            mainController.getNorTrainer().setText(String.valueOf(i));



            // -------------------------------



            for( ;mainController.getPnItems().getChildren().size() > 1;){
                mainController.getPnItems().getChildren().remove(1);
            }
            mainController.getNorCustomer().setText("0");
            sql = "SELECT * FROM Customer";
            rs = stmt.executeQuery(sql);
            ArrayList<Node> nodesCustomer = new ArrayList<>();
            //STEP 5: Extract data from result set
            i=0;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertCustomer.fxml"));

            if(i==0){
                nodesCustomer.add(fxmlLoaderToAddInsert.load());
                CustomerItemController customerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                customerItemControllerInsert.setMainController(this.mainController);
                mainController.getPnItems().getChildren().add(nodesCustomer.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                final String id  = rs.getString("UsernameID");
                final String membershipTypeName = rs.getString("MembershipTypeName");
                final String customerTrainerID = rs.getString("TrainerID");
                final String weight = rs.getString("Weight");
                final String length = rs.getString("Length");
                final String age = rs.getString("Age");
                final String fatRatio = rs.getString("FatRatio");
                final String creditCardNumber = rs.getString("CreditCardNumber");
                final String creditCardExpireDate = rs.getString("CreditCardExpireDate");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));


                nodesCustomer.add(fxmlLoader.load());
                CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();


                customerItemController.setMainController(this.mainController);
                customerItemController.assignTextLabel(id,  membershipTypeName, customerTrainerID,
                        weight, length, age, fatRatio, creditCardNumber, creditCardExpireDate);

                mainController.getPnItems().getChildren().add(nodesCustomer.get(j));
                i++;
            }
            mainController.getNorCustomer().setText(String.valueOf(i));





        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerName(String userNameID, String name ){
        String sql = "update System_User set Name = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerSurname(String userNameID, String surname  ){
        String sql = "update System_User set Surname = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, surname);
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateStartDate(String newFieldUsernameID,String newFieldStartDate){
        String sql = "update `System_User` set StartDate = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldStartDate);
            preparedStmt.setString(2, newFieldUsernameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
    public void updateEndDate(String newFieldUsernameID,String newFieldEndDate){
        String sql = "update `System_User` set EndDate = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldEndDate);
            preparedStmt.setString(2, newFieldUsernameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updatePassword(String newFieldUsernameID,String newFieldPassword){
        String sql = "update `System_User` set `Password` = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(newFieldPassword.getBytes());

            byte byteData[] = md.digest();

            //Convert "byteData" to hex String:
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }


            preparedStmt.setString(1, sb.toString());
            preparedStmt.setString(2, newFieldUsernameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
