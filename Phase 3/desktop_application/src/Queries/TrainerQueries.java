package Queries;

import Controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class TrainerQueries {
    private Connection conn;

    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    private int actionForCancel;
    private Controller mainController;

    public Controller getMainController() {
        return mainController;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public TrainerQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }
    public void insertTrainer(String usernameID, String salary
                               ){

        String sql = "INSERT INTO Trainer( UsernameID ,Salary)"
                +"VALUES(?,"+
                "?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, usernameID);
                if(salary.isEmpty())
                    preparedStmt.setNull(2, Types.FLOAT);
                else
                    preparedStmt.setFloat(2, Float.parseFloat(salary));
                preparedStmt.execute();
                // -------------------------------

                for( ;mainController.getPnBeProfessionItems().getChildren().size() > 1;){
                    mainController.getPnBeProfessionItems().getChildren().remove(1);
                }
                mainController.getNorBeProfession().setText("0");
                Statement stmt = conn.createStatement();
                sql = "SELECT * FROM Be_Profession";
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesBeProfession = new ArrayList<>();
                FXMLLoader fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertBeProfession.fxml"));

                if(i==0){
                    nodesBeProfession.add(fxmlLoaderToAddInsert.load());
                    BeProfessionItemController beProfessionItemController = fxmlLoaderToAddInsert.getController();
                    beProfessionItemController.setMainController(this.mainController);
                    beProfessionItemController.createInsertBox();
                    mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String namee = rs.getString("ProfessionName");
                    String ID = rs.getString("TrainerID");

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/BeProfessionItem.fxml"));

                    nodesBeProfession.add(fxmlLoader.load());
                    BeProfessionItemController beProfessionItemController = (BeProfessionItemController) fxmlLoader.getController();


                    beProfessionItemController.setMainController(this.mainController);
                    beProfessionItemController.assignTextLabel(namee,ID);

                    mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(j));
                    i++;
                }
                mainController.getNorBeProfession().setText(String.valueOf(i));


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
                    customerItemControllerInsert.createInsertBox();
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

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/CustomerItem.fxml"));


                    nodesCustomer.add(fxmlLoader.load());
                    CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                    customerItemController.setMainController(this.mainController);
                    customerItemController.assignTextLabel(id, membershipTypeName, customerTrainerID,
                            weight, length, age, fatRatio, creditCardNumber, creditCardExpireDate);


                    mainController.getPnItems().getChildren().add(nodesCustomer.get(j));
                    i++;
                }
                mainController.getNorCustomer().setText(String.valueOf(i));

                // -------------------------------

                for( ;mainController.getPnLessonItems().getChildren().size() > 1;){
                    mainController.getPnLessonItems().getChildren().remove(1);
                }
                mainController.getNorLesson().setText("0");



                sql = "SELECT * FROM Lesson";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesLesson = new ArrayList<>();
                FXMLLoader fxmlLoader;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertLesson.fxml"));

                if(i==0){
                    nodesLesson.add(fxmlLoaderToAddInsert.load());
                    LessonItemController lessonItemController = fxmlLoaderToAddInsert.getController();
                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.createInsertBox();
                    mainController.getPnLessonItems().getChildren().add(nodesLesson.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String ID = rs.getString("ID");
                    String name = rs.getString("Name");
                    String id = rs.getString("TrainerID");
                    String professionName = rs.getString("ProfessionName");
                    String price = rs.getString("Price");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));

                    nodesLesson.add(fxmlLoader.load());
                    LessonItemController lessonItemController = (LessonItemController) fxmlLoader.getController();

                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.assignTextLabel(ID,name, id, professionName, price);

                    mainController.getPnLessonItems().getChildren().add(nodesLesson.get(j));
                    i++;
                }
                mainController.getNorLesson().setText(String.valueOf(i));
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    public void deleteTrainer(String UserNameID){

        String sql = "delete from Trainer where UsernameID = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, UserNameID);
                preparedStmt.execute();

                // -------------------------------

                for( ;mainController.getPnLessonItems().getChildren().size() > 1;){
                    mainController.getPnLessonItems().getChildren().remove(1);
                }
                mainController.getNorLesson().setText("0");



                sql = "SELECT * FROM Lesson";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesLesson = new ArrayList<>();
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertLesson.fxml"));

                if(i==0){
                    nodesLesson.add(fxmlLoaderToAddInsert.load());
                    LessonItemController lessonItemController = fxmlLoaderToAddInsert.getController();
                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.createInsertBox();
                    mainController.getPnLessonItems().getChildren().add(nodesLesson.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String ID = rs.getString("ID");
                    String name = rs.getString("Name");
                    String id = rs.getString("TrainerID");
                    String professionName = rs.getString("ProfessionName");
                    String price = rs.getString("Price");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));

                    nodesLesson.add(fxmlLoader.load());
                    LessonItemController lessonItemController = (LessonItemController) fxmlLoader.getController();

                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.assignTextLabel(ID,name, id, professionName, price);

                    mainController.getPnLessonItems().getChildren().add(nodesLesson.get(j));
                    i++;
                }
                mainController.getNorLesson().setText(String.valueOf(i));



                // -------------------------------

                for( ;mainController.getPnBeProfessionItems().getChildren().size() > 1;){
                    mainController.getPnBeProfessionItems().getChildren().remove(1);
                }
                mainController.getNorBeProfession().setText("0");
                sql = "SELECT * FROM Be_Profession";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesBeProfession = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertBeProfession.fxml"));

                if(i==0){
                    nodesBeProfession.add(fxmlLoaderToAddInsert.load());
                    BeProfessionItemController beProfessionItemController = fxmlLoaderToAddInsert.getController();
                    beProfessionItemController.setMainController(this.mainController);
                    beProfessionItemController.createInsertBox();
                    mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String name = rs.getString("ProfessionName");
                    String ID = rs.getString("TrainerID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/BeProfessionItem.fxml"));

                    nodesBeProfession.add(fxmlLoader.load());
                    BeProfessionItemController beProfessionItemController = (BeProfessionItemController) fxmlLoader.getController();


                    beProfessionItemController.setMainController(this.mainController);
                    beProfessionItemController.assignTextLabel(name,ID);

                    mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(j));
                    i++;
                }
                mainController.getNorBeProfession().setText(String.valueOf(i));
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
                    customerItemControllerInsert.createInsertBox();
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



                // -------------------------------

                for( ;mainController.getPnSystemUserItems().getChildren().size() > 1;){
                    mainController.getPnSystemUserItems().getChildren().remove(1);
                }
                mainController.getNorCustomer().setText("0");
                sql = "SELECT * FROM `System_User`";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesSystemUser = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertSystemUser.fxml"));

                if(i==0){
                    nodesSystemUser.add(fxmlLoaderToAddInsert.load());
                    SystemUserItemController systemUserItemController = fxmlLoaderToAddInsert.getController();
                    systemUserItemController.setMainController(this.mainController);
                    mainController.getPnSystemUserItems().getChildren().add(nodesSystemUser.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String UsernameID = rs.getString("UsernameID");
                    final String name = rs.getString("Name");
                    final String surname = rs.getString("Surname");
                    String StartDate = rs.getString("StartDate");
                    String EndDate = rs.getString("EndDate");
                    String Password = rs.getString("Password");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/SystemUserItem.fxml"));

                    nodesSystemUser.add(fxmlLoader.load());
                    SystemUserItemController systemUserItemController = (SystemUserItemController) fxmlLoader.getController();

                    systemUserItemController.assignTextLabel(UsernameID,name,surname,StartDate,EndDate,Password);
                    systemUserItemController.setMainController(this.mainController);

                    mainController.getPnSystemUserItems().getChildren().add(nodesSystemUser.get(j));
                    i++;
                }
                mainController.getNorSystemUser().setText(String.valueOf(i));
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }



    }


    public void updateUserNameID(String UserNameID , String newUserNameID){
        String sql = "update System_User set UsernameID = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newUserNameID);
            preparedStmt.setString(2, UserNameID);
            preparedStmt.executeUpdate();
            // -------------------------------

            for( ;mainController.getPnLessonItems().getChildren().size() > 1;){
                mainController.getPnLessonItems().getChildren().remove(1);
            }
            mainController.getNorLesson().setText("0");
            sql = "SELECT * FROM Lesson";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            int i=0;

            ArrayList<Node> nodesLesson = new ArrayList<>();
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertLesson.fxml"));

            if(i==0){
                nodesLesson.add(fxmlLoaderToAddInsert.load());
                LessonItemController lessonItemController = fxmlLoaderToAddInsert.getController();
                lessonItemController.setMainController(this.mainController);
                lessonItemController.createInsertBox();
                mainController.getPnLessonItems().getChildren().add(nodesLesson.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String ID = rs.getString("ID");
                String name = rs.getString("Name");
                String id = rs.getString("TrainerID");
                String professionName = rs.getString("ProfessionName");
                String price = rs.getString("Price");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));

                nodesLesson.add(fxmlLoader.load());
                LessonItemController lessonItemController = (LessonItemController) fxmlLoader.getController();

                lessonItemController.setMainController(this.mainController);
                lessonItemController.assignTextLabel(ID,name, id, professionName, price);

                mainController.getPnLessonItems().getChildren().add(nodesLesson.get(j));
                i++;
            }
            mainController.getNorLesson().setText(String.valueOf(i));



            // -------------------------------

            for( ;mainController.getPnBeProfessionItems().getChildren().size() > 1;){
                mainController.getPnBeProfessionItems().getChildren().remove(1);
            }
            mainController.getNorBeProfession().setText("0");
            sql = "SELECT * FROM Be_Profession";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesBeProfession = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertBeProfession.fxml"));

            if(i==0){
                nodesBeProfession.add(fxmlLoaderToAddInsert.load());
                BeProfessionItemController beProfessionItemController = fxmlLoaderToAddInsert.getController();
                beProfessionItemController.setMainController(this.mainController);
                beProfessionItemController.createInsertBox();
                mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String name = rs.getString("ProfessionName");
                String ID = rs.getString("TrainerID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/BeProfessionItem.fxml"));

                nodesBeProfession.add(fxmlLoader.load());
                BeProfessionItemController beProfessionItemController = (BeProfessionItemController) fxmlLoader.getController();

                beProfessionItemController.setMainController(this.mainController);
                beProfessionItemController.assignTextLabel(name,ID);

                mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(j));
                i++;
            }
            mainController.getNorBeProfession().setText(String.valueOf(i));



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
                customerItemControllerInsert.createInsertBox();
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


            // -------------------------------

            for( ;mainController.getPnSystemUserItems().getChildren().size() > 1;){
                mainController.getPnSystemUserItems().getChildren().remove(1);
            }
            mainController.getNorCustomer().setText("0");
            sql = "SELECT * FROM `System_User`";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesSystemUser = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertSystemUser.fxml"));

            if(i==0){
                nodesSystemUser.add(fxmlLoaderToAddInsert.load());
                SystemUserItemController systemUserItemController = fxmlLoaderToAddInsert.getController();
                systemUserItemController.setMainController(this.mainController);
                mainController.getPnSystemUserItems().getChildren().add(nodesSystemUser.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String UsernameID = rs.getString("UsernameID");
                final String name = rs.getString("Name");
                final String surname = rs.getString("Surname");
                String StartDate = rs.getString("StartDate");
                String EndDate = rs.getString("EndDate");
                String Password = rs.getString("Password");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/SystemUserItem.fxml"));

                nodesSystemUser.add(fxmlLoader.load());
                SystemUserItemController systemUserItemController = (SystemUserItemController) fxmlLoader.getController();

                systemUserItemController.assignTextLabel(UsernameID, name, surname, StartDate,EndDate,Password);
                systemUserItemController.setMainController(this.mainController);

                mainController.getPnSystemUserItems().getChildren().add(nodesSystemUser.get(j));
                i++;
            }
            mainController.getNorSystemUser().setText(String.valueOf(i));
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateSalary(String UserNameID, String salary ){
        String sql = "update Trainer set Salary = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(salary.isEmpty())
                preparedStmt.setNull(1, Types.FLOAT);
            else
                preparedStmt.setFloat(1, Float.parseFloat(salary));

            preparedStmt.setString(2, UserNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }


}
