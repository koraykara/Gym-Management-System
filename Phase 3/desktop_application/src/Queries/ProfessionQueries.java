package Queries;

import Controllers.BeProfessionItemController;
import Controllers.Controller;
import Controllers.LessonItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ProfessionQueries {

    private Connection conn;
    private Controller mainController;

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

    public ProfessionQueries(Connection conn) {
        try{
            this.conn = conn;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }

    }

    public void insertProfession( String name ){

        String sql = "INSERT INTO Profession(`Name`)"
                +"VALUES(?)";
        PreparedStatement preparedStmt;

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to add this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, name);
                preparedStmt.execute();

                for( ;mainController.getPnBeProfessionItems().getChildren().size() > 1;){
                    mainController.getPnBeProfessionItems().getChildren().remove(1);
                }

                Statement stmt = conn.createStatement();
                mainController.getNorBeProfession().setText("0");
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
                    this.mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(i));
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

                    this.mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(j));
                    i++;
                }
                this.mainController.getNorBeProfession().setText(String.valueOf(i));


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
                    String namee = rs.getString("Name");
                    String id = rs.getString("TrainerID");
                    String professionName = rs.getString("ProfessionName");
                    String price = rs.getString("Price");

                    fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/LessonItem.fxml"));

                    nodesLesson.add(fxmlLoader.load());
                    LessonItemController lessonItemController = (LessonItemController) fxmlLoader.getController();

                    lessonItemController.setMainController(this.mainController);
                    lessonItemController.assignTextLabel(ID,namee, id, professionName, price);

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

    public void deleteProfession(String Name){

        String sql = "delete from Profession where Name = ?";
        PreparedStatement preparedStmt; // ------>> EKLEDIM

        actionForCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?");
        if(actionForCancel == 0){
            try{
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, Name);
                preparedStmt.execute();
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
                    this.mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(i));
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

                    this.mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(j));
                    i++;
                }
                this.mainController.getNorBeProfession().setText(String.valueOf(i));
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
        }

    }

    public void updateProfessionName(String Name , String newName){
        String sql = "update Profession set Name = ? where Name = ?";
        PreparedStatement preparedStmt;
        try{
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, newName);
            preparedStmt.setString(2, Name);
            preparedStmt.executeUpdate();
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
                this.mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(i));
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

                this.mainController.getPnBeProfessionItems().getChildren().add(nodesBeProfession.get(j));
                i++;
            }
            this.mainController.getNorBeProfession().setText(String.valueOf(i));
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }


}
