package Queries;

import Controllers.Controller;
import Controllers.HasItemController;
import Controllers.NotFitItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class PhysicalAilmentItemQueries {
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

    public PhysicalAilmentItemQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public void deletePhysicalAilment(String FieldName){

        String sql = "delete from Physical_Ailment where Name = ?"; // ------>> duzelttim
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql); // ------>> EKLEDIM
                preparedStmt.setString(1, FieldName);
                preparedStmt.execute();

                // -------------------------------
                for( ;mainController.getPnNotFitItems().getChildren().size() > 1;){
                    mainController.getPnNotFitItems().getChildren().remove(1);
                }
                mainController.getNorNotFit().setText("0");
                sql = "SELECT * FROM Not_Fit";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesNotFit = new ArrayList<>();
                FXMLLoader fxmlLoader;
                FXMLLoader fxmlLoaderToAddInsert;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertNotFit.fxml"));

                if(i==0){
                    nodesNotFit.add(fxmlLoaderToAddInsert.load());
                    NotFitItemController notFitItemController = fxmlLoaderToAddInsert.getController();
                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.createInsertBox();
                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(i));
                }

                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String PyhsicalAilmentName = rs.getString("PyhsicalAilmentName");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));

                    nodesNotFit.add(fxmlLoader.load());
                    NotFitItemController notFitItemController = (NotFitItemController) fxmlLoader.getController();

                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.assignTextLabel(PyhsicalAilmentName,ProgramID);

                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(j));
                    i++;
                }
                mainController.getNorNotFit().setText(String.valueOf(i));


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
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public int insertPhysicalAilment(String name
    ){

        String sql = "INSERT INTO Physical_Ailment( Name )"
                +"VALUES(?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, name);
                preparedStmt.execute();




                for( ;mainController.getPnHasItems().getChildren().size() > 1;){
                    mainController.getPnHasItems().getChildren().remove(1);
                }
                mainController.getNorHas().setText("0");
                sql = "SELECT * FROM Has";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                int i=0;

                ArrayList<Node> nodesHas = new ArrayList<>();
                FXMLLoader fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertHas.fxml"));

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

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/HasItem.fxml"));

                    nodesHas.add(fxmlLoader.load());
                    HasItemController hasItemController = (HasItemController) fxmlLoader.getController();

                    hasItemController.setMainController(this.mainController);
                    hasItemController.assignTextLabel(customerID,PhysicalAilmentName);

                    mainController.getPnHasItems().getChildren().add(nodesHas.get(j));
                    i++;
                }
                mainController.getNorHas().setText(String.valueOf(i));


                i=0;

                ArrayList<Node> nodesNotFit = new ArrayList<>();
                FXMLLoader fxmlLoader;
                fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertNotFit.fxml"));

                if(i==0){
                    nodesNotFit.add(fxmlLoaderToAddInsert.load());
                    NotFitItemController notFitItemController = fxmlLoaderToAddInsert.getController();
                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.createInsertBox();
                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(i));
                }
                while(rs.next()){
                    //Retrieve by column name
                    final int j = i+1;
                    String PyhsicalAilmentName = rs.getString("PyhsicalAilmentName");
                    String ProgramID = rs.getString("ProgramID");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));


                    nodesNotFit.add(fxmlLoader.load());
                    NotFitItemController notFitItemController = (NotFitItemController) fxmlLoader.getController();

                    notFitItemController.setMainController(this.mainController);
                    notFitItemController.assignTextLabel(PyhsicalAilmentName,ProgramID);

                    mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(j));
                    i++;
                }
                mainController.getNorNotFit().setText(String.valueOf(i));

                return 1;
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }
        return 0;
    }

    public void updatePhysicalAilment(String oldFieldName,String newFieldName){
        String sql = "update Physical_Ailment set Name = ? where Name = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newFieldName);
            preparedStmt.setString(2, oldFieldName);
            preparedStmt.executeUpdate();
            // -------------------------------
            for( ;mainController.getPnNotFitItems().getChildren().size() > 1;){
                mainController.getPnNotFitItems().getChildren().remove(1);
            }
            mainController.getNorNotFit().setText("0");
            sql = "SELECT * FROM Not_Fit";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            int i=0;

            ArrayList<Node> nodesNotFit = new ArrayList<>();
            FXMLLoader fxmlLoader;
            FXMLLoader fxmlLoaderToAddInsert;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertNotFit.fxml"));

            if(i==0){
                nodesNotFit.add(fxmlLoaderToAddInsert.load());
                NotFitItemController notFitItemController = fxmlLoaderToAddInsert.getController();
                notFitItemController.setMainController(this.mainController);
                notFitItemController.createInsertBox();
                mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String PyhsicalAilmentName = rs.getString("PyhsicalAilmentName");
                String ProgramID = rs.getString("ProgramID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));


                nodesNotFit.add(fxmlLoader.load());
                NotFitItemController notFitItemController = (NotFitItemController) fxmlLoader.getController();

                notFitItemController.setMainController(this.mainController);
                notFitItemController.assignTextLabel(PyhsicalAilmentName,ProgramID);

                mainController.getPnNotFitItems().getChildren().add(nodesNotFit.get(j));
                i++;
            }
            mainController.getNorNotFit().setText(String.valueOf(i));



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
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
