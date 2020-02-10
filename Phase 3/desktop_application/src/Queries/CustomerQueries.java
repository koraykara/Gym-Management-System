package Queries;

import Controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CustomerQueries {

    private Connection conn;
    private Controller mainController;
    public int getActionForCancel() {
        return actionForCancel;
    }
    public void setActionForCancel(int actionForCancel) {
        this.actionForCancel = actionForCancel;
    }

    public Controller getMainController() {
        return mainController;
    }

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    private int actionForCancel;

    public CustomerQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public int insertCustomer(String usernameID,String membershipTypeName,String trainerID, String weight,
                               String length,String age,String fatRatio,String creditCardNumber,String creditCardExpireDate){

        String sql = "INSERT INTO Customer( UsernameID,MembershipTypeName,TrainerID,Weight,Length,Age,FatRatio,CreditCardNumber, CreditCardExpireDate)"
                +"VALUES(?,"+
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?," +
                "?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, usernameID);
                preparedStmt.setString(2, membershipTypeName);
                preparedStmt.setString(3, trainerID);

                if(weight.isEmpty())
                    preparedStmt.setNull(4, Types.FLOAT);
                else
                    preparedStmt.setFloat(4, Float.parseFloat(weight));

                if(length.isEmpty())
                    preparedStmt.setNull(5, Types.FLOAT);
                else
                    preparedStmt.setFloat(5, Float.parseFloat(length));

                if(age.isEmpty())
                    preparedStmt.setNull(6, Types.INTEGER);
                else
                    preparedStmt.setInt(6, Integer.parseInt(age));

                if(fatRatio.isEmpty())
                    preparedStmt.setNull(7, Types.FLOAT);
                else
                    preparedStmt.setFloat(7, Float.parseFloat(fatRatio));

                preparedStmt.setString(8, creditCardNumber);
                preparedStmt.setString(9, creditCardExpireDate);
                preparedStmt.execute();

                // -------------------------------

                for( ;mainController.getPnAskedForItems().getChildren().size() > 1;){
                    mainController.getPnAskedForItems().getChildren().remove(1);
                }

                Statement stmt = conn.createStatement();
                mainController.getNorAskedFor().setText("0");
                sql = "SELECT * FROM Asked_For";
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                ArrayList<Node> nodesAsked_For = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertAskedFor.fxml"));

                if(i==0){
                    nodesAsked_For.add(fxmlLoaderToAddInsert.load());
                    AskedForItemController askedForItemController = fxmlLoaderToAddInsert.getController();
                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.createInsertBox();
                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));


                    nodesAsked_For.add(fxmlLoader.load());
                    AskedForItemController askedForItemController = (AskedForItemController) fxmlLoader.getController();

                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.assignTextLabel(customerID,ProgramID);
                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(j));
                    i++;
                }
                mainController.getNorAskedFor().setText(String.valueOf(i));


                // -------------------------------

                for( ;mainController.getPnHasItems().getChildren().size() > 1;){
                    mainController.getPnHasItems().getChildren().remove(1);
                }

                mainController.getNorHas().setText("0");
                sql = "SELECT * FROM Has";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesHas = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertHas.fxml"));

                if(i==0){
                    nodesHas.add(fxmlLoaderToAddInsert.load());
                    HasItemController hasItemController = fxmlLoaderToAddInsert.getController();
                    hasItemController.setMainController(this.mainController);
                    hasItemController.createInsertBox();
                    mainController.getPnHasItems().getChildren().add(nodesHas.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String PhysicalAilmentName = rs.getString("PyhsicalAilmentName");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/HasItem.fxml"));

                    nodesHas.add(fxmlLoader.load());
                    HasItemController hasItemController = (HasItemController) fxmlLoader.getController();

                    hasItemController.setMainController(this.mainController);
                    hasItemController.assignTextLabel(customerID,PhysicalAilmentName);

                    mainController.getPnHasItems().getChildren().add(nodesHas.get(j));
                    i++;
                }
                mainController.getNorHas().setText(String.valueOf(i));




// -------------------------------
                for( ;mainController.getPnPurchaseItems().getChildren().size() > 1;){
                    mainController.getPnPurchaseItems().getChildren().remove(1);
                }

                mainController.getNorPurchase().setText("0");
                sql = "SELECT * FROM Purchase";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesPurchase = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertPurchase.fxml"));

                if(i==0){
                    nodesPurchase.add(fxmlLoaderToAddInsert.load());
                    PurchaseItemController purchaseItemController = fxmlLoaderToAddInsert.getController();
                    purchaseItemController.setMainController(this.mainController);
                    purchaseItemController.createInsertBox();
                    mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String LessonID = rs.getString("LessonID");
                    String enrollTimeDay = rs.getString("EnrollTimeDay");
                    String PurchasedDate = rs.getString("PurchasedDate");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/PurchaseItem.fxml"));

                    nodesPurchase.add(fxmlLoader.load());
                    PurchaseItemController purchaseItemController = (PurchaseItemController) fxmlLoader.getController();

                    purchaseItemController.setMainController(this.mainController);
                    purchaseItemController.assignTextLabel(customerID,LessonID,enrollTimeDay,PurchasedDate);

                    mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(j));
                    i++;
                }
                mainController.getNorPurchase().setText(String.valueOf(i));

            }
            catch (Exception e1){
                e1.printStackTrace();
            }
            return 1;
        }
        return 0;
    }

    //FOR DELETE QUERY
    public void deleteCustomer(String UserNameID){

        String sql = "delete from Customer where UsernameID = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, UserNameID);
                preparedStmt.execute();


                // -------------------------------

                for( ;mainController.getPnSystemUserItems().getChildren().size() > 1;){
                    mainController.getPnSystemUserItems().getChildren().remove(1);
                }

                mainController.getNorSystemUser().setText("0");
                sql = "SELECT * FROM `System_User`";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesSystemUser = new ArrayList<>();
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
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


                // -------------------------------
                for( ;mainController.getPnPurchaseItems().getChildren().size() > 1;){
                    mainController.getPnPurchaseItems().getChildren().remove(1);
                }

                mainController.getNorPurchase().setText("0");
                sql = "SELECT * FROM Purchase";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesPurchase = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertPurchase.fxml"));

                if(i==0){
                    nodesPurchase.add(fxmlLoaderToAddInsert.load());
                    PurchaseItemController purchaseItemController = fxmlLoaderToAddInsert.getController();
                    purchaseItemController.setMainController(this.mainController);
                    purchaseItemController.createInsertBox();
                    mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String LessonID = rs.getString("LessonID");
                    String enrollTimeDay = rs.getString("EnrollTimeDay");
                    String PurchasedDate = rs.getString("PurchasedDate");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/PurchaseItem.fxml"));

                    nodesPurchase.add(fxmlLoader.load());
                    PurchaseItemController purchaseItemController = (PurchaseItemController) fxmlLoader.getController();

                    purchaseItemController.setMainController(this.mainController);
                    purchaseItemController.assignTextLabel(customerID,LessonID,enrollTimeDay,PurchasedDate);

                   mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(j));
                    i++;
                }
                mainController.getNorPurchase().setText(String.valueOf(i));



                // -------------------------------

                for( ;mainController.getPnHasItems().getChildren().size() > 1;){
                    mainController.getPnHasItems().getChildren().remove(1);
                }

                mainController.getNorHas().setText("0");
                sql = "SELECT * FROM Has";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesHas = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertHas.fxml"));

                if(i==0){
                    nodesHas.add(fxmlLoaderToAddInsert.load());
                    HasItemController hasItemController = fxmlLoaderToAddInsert.getController();
                    hasItemController.setMainController(this.mainController);
                    hasItemController.createInsertBox();
                    mainController.getPnHasItems().getChildren().add(nodesHas.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String PhysicalAilmentName = rs.getString("PyhsicalAilmentName");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/HasItem.fxml"));

                    nodesHas.add(fxmlLoader.load());
                    HasItemController hasItemController = (HasItemController) fxmlLoader.getController();

                    hasItemController.setMainController(this.mainController);
                    hasItemController.assignTextLabel(customerID,PhysicalAilmentName);

                    mainController.getPnHasItems().getChildren().add(nodesHas.get(j));
                    i++;
                }
                mainController.getNorHas().setText(String.valueOf(i));


                // -------------------------------

                for( ;mainController.getPnAskedForItems().getChildren().size() > 1;){
                    mainController.getPnAskedForItems().getChildren().remove(1);
                }

                mainController.getNorAskedFor().setText("0");
                sql = "SELECT * FROM Asked_For";
                rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                i=0;

                ArrayList<Node> nodesAsked_For = new ArrayList<>();
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertAskedFor.fxml"));

                if(i==0){
                    nodesAsked_For.add(fxmlLoaderToAddInsert.load());
                    AskedForItemController askedForItemController = fxmlLoaderToAddInsert.getController();
                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.createInsertBox();
                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(i));
                }

                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String customerID = rs.getString("CustomerID");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));

                    nodesAsked_For.add(fxmlLoader.load());
                    AskedForItemController askedForItemController = (AskedForItemController) fxmlLoader.getController();

                    askedForItemController.setMainController(this.mainController);
                    askedForItemController.assignTextLabel(customerID,ProgramID);
                    mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(j));
                    i++;
                }
                mainController.getNorAskedFor().setText(String.valueOf(i));
                // -------------------------------
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    //FOR UPDATE QUERY
    public void updateCustomerUserNameID(String UserNameID , String newUserNameID){
        String sql = "update System_User set UsernameID = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newUserNameID);
            preparedStmt.setString(2, UserNameID);
            preparedStmt.executeUpdate();
// -------------------------------

            for( ;mainController.getPnSystemUserItems().getChildren().size() > 1;){
                mainController.getPnSystemUserItems().getChildren().remove(1);
            }

            mainController.getNorSystemUser().setText("0");
            sql = "SELECT * FROM `System_User`";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            int i=0;

            ArrayList<Node> nodesSystemUser = new ArrayList<>();
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
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
                String UsernameID = rs.getString("UsernameID");final String name = rs.getString("Name");
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

// -------------------------------
            for( ;mainController.getPnPurchaseItems().getChildren().size() > 1;){
                mainController.getPnPurchaseItems().getChildren().remove(1);
            }

            mainController.getNorPurchase().setText("0");
            sql = "SELECT * FROM Purchase";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesPurchase = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertPurchase.fxml"));

            if(i==0){
                nodesPurchase.add(fxmlLoaderToAddInsert.load());
                PurchaseItemController purchaseItemController = fxmlLoaderToAddInsert.getController();
                purchaseItemController.setMainController(this.mainController);
                purchaseItemController.createInsertBox();
                mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String customerID = rs.getString("CustomerID");
                String LessonID = rs.getString("LessonID");
                String enrollTimeDay = rs.getString("EnrollTimeDay");
                String PurchasedDate = rs.getString("PurchasedDate");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/PurchaseItem.fxml"));

                nodesPurchase.add(fxmlLoader.load());
                PurchaseItemController purchaseItemController = (PurchaseItemController) fxmlLoader.getController();

                purchaseItemController.setMainController(this.mainController);
                purchaseItemController.assignTextLabel(customerID,LessonID,enrollTimeDay,PurchasedDate);

                mainController.getPnPurchaseItems().getChildren().add(nodesPurchase.get(j));
                i++;
            }
            mainController.getNorPurchase().setText(String.valueOf(i));



            // -------------------------------

            for( ;mainController.getPnHasItems().getChildren().size() > 1;){
                mainController.getPnHasItems().getChildren().remove(1);
            }

            mainController.getNorHas().setText("0");
            sql = "SELECT * FROM Has";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesHas = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertHas.fxml"));

            if(i==0){
                nodesHas.add(fxmlLoaderToAddInsert.load());
                HasItemController hasItemController = fxmlLoaderToAddInsert.getController();
                hasItemController.setMainController(this.mainController);
                hasItemController.createInsertBox();
                mainController.getPnHasItems().getChildren().add(nodesHas.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String customerID = rs.getString("CustomerID");
                String PhysicalAilmentName = rs.getString("PyhsicalAilmentName");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/HasItem.fxml"));

                nodesHas.add(fxmlLoader.load());
                HasItemController hasItemController = (HasItemController) fxmlLoader.getController();

                hasItemController.setMainController(this.mainController);
                hasItemController.assignTextLabel(customerID,PhysicalAilmentName);

                mainController.getPnHasItems().getChildren().add(nodesHas.get(j));
                i++;
            }
            mainController.getNorHas().setText(String.valueOf(i));


            // -------------------------------

            for( ;mainController.getPnAskedForItems().getChildren().size() > 1;){
                mainController.getPnAskedForItems().getChildren().remove(1);
            }

            mainController.getNorAskedFor().setText("0");
            sql = "SELECT * FROM Asked_For";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesAsked_For = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertAskedFor.fxml"));

            if(i==0){
                nodesAsked_For.add(fxmlLoaderToAddInsert.load());
                AskedForItemController askedForItemController = fxmlLoaderToAddInsert.getController();
                askedForItemController.setMainController(this.mainController);
                askedForItemController.createInsertBox();
                mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String customerID = rs.getString("CustomerID");
                String ProgramID = rs.getString("ProgramID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));


                nodesAsked_For.add(fxmlLoader.load());
                AskedForItemController askedForItemController = (AskedForItemController) fxmlLoader.getController();

                askedForItemController.setMainController(this.mainController);
                askedForItemController.assignTextLabel(customerID,ProgramID);

                mainController.getPnAskedForItems().getChildren().add(nodesAsked_For.get(j));
                i++;
            }
            mainController.getNorAskedFor().setText(String.valueOf(i));
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }



    public void updateCustomerMembershipType(String userNameID, String membershipType  ){
        String sql = "update Customer set MembershipTypeName = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, membershipType);
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerTrainerID(String userNameID, String trainerID  ){
        String sql = "update Customer set TrainerID = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, trainerID);
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerWeight(String userNameID, String weight  ){
        String sql = "update Customer set Weight = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(weight.isEmpty())
                preparedStmt.setNull(1, Types.FLOAT);
            else
                preparedStmt.setFloat(1, Float.parseFloat(weight));
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerLength(String userNameID, String length  ){
        String sql = "update Customer set Length = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(length.isEmpty())
                preparedStmt.setNull(1, Types.FLOAT);
            else
                preparedStmt.setFloat(1, Float.parseFloat(length));
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerAge(String userNameID, String age  ){
        String sql = "update Customer set Age = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(age.isEmpty())
                preparedStmt.setNull(1, Types.INTEGER);
            else
                preparedStmt.setInt(1, Integer.parseInt(age));
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerFatRatio(String userNameID, String fatRatio ){
        String sql = "update Customer set FatRatio = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            if(fatRatio.isEmpty())
                preparedStmt.setNull(1, Types.FLOAT);
            else
                preparedStmt.setFloat(1, Float.parseFloat(fatRatio));

            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerCreditCardNumber(String userNameID, String creditCardNumber ){
        String sql = "update Customer set CreditCardNumber = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, creditCardNumber);
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public void updateCustomerCardExpireDate(String userNameID, String expireDtae ){
        String sql = "update Customer set CreditCardExpireDate = ? where UsernameID = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, expireDtae);
            preparedStmt.setString(2, userNameID);
            preparedStmt.executeUpdate();

        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

}
