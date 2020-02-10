package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.sql.*;

public class Controller implements Initializable {

    public VBox getPnItems() {
        return pnItems;
    }

    public void setPnItems(VBox pnItems) {
        this.pnItems = pnItems;
    }


    public VBox getPnTrainerItems() {
        return pnTrainerItems;
    }

    public void setPnTrainerItems(VBox pnTrainerItems) {
        this.pnTrainerItems = pnTrainerItems;
    }

    @FXML
    private VBox pnTrainerItems; // Vbox of Trainer Hbox


    @FXML
    private VBox pnProfessionItems;

    @FXML private VBox getPnHasItems;
    public VBox getGetPnHasItems() {

        return getPnHasItems;

    }

    public void setGetPnHasItems(VBox getPnHasItems) {

        this.getPnHasItems = getPnHasItems;

    }

    @FXML
    private VBox pnEnrollTimeItems;

    public VBox getPnEnrollTimeItems() {
        return pnEnrollTimeItems;
    }

    public void setPnEnrollTimeItems(VBox pnEnrollTimeItems) {
        this.pnEnrollTimeItems = pnEnrollTimeItems;
    }

    public VBox getPnAdminItems() {
        return pnAdminItems;
    }

    public void setPnAdminItems(VBox pnAdminItems) {
        this.pnAdminItems = pnAdminItems;
    }

    @FXML
    private VBox pnAdminItems;

    @FXML
    private VBox pnProgramItems;

    @FXML
    private VBox pnSportToolsItems;

    @FXML
    private VBox pnBeProfessionItems;

    @FXML
    private VBox pnLessonItems;

    @FXML
    private VBox pnMembershipItems;

    @FXML
    private VBox pnPurchaseItems;

    @FXML
    private VBox pnUsedInItems;

    @FXML
    private VBox pnPhysicalAilmentItems;

    @FXML
    private VBox pnHasItems;

    @FXML
    private VBox pnAskedForItems;

    @FXML
    private VBox pnNotFitItems;

    @FXML
    private VBox pnExerciseItems;

    @FXML
    private VBox pnConsistOfItems;

    @FXML
    private VBox pnMakeWithItems;

    public VBox getPnSystemUserItems() {
        return pnSystemUserItems;
    }

    public void setPnSystemUserItems(VBox pnSystemUserItems) {
        this.pnSystemUserItems = pnSystemUserItems;
    }

    @FXML
    private VBox pnSystemUserItems;

    public VBox getPnMakeWithItems() {
        return pnMakeWithItems;
    }

    public void setPnMakeWithItems(VBox pnMakeWithItems) {
        this.pnMakeWithItems = pnMakeWithItems;
    }

    public VBox getPnConsistOfItems() {
        return pnConsistOfItems;
    }

    public void setPnConsistOfItems(VBox pnConsistOfItems) {
        this.pnConsistOfItems = pnConsistOfItems;
    }

    public VBox getPnExerciseItems() {
        return pnExerciseItems;
    }

    public void setPnExerciseItems(VBox pnExerciseItems) {
        this.pnExerciseItems = pnExerciseItems;
    }

    public VBox getPnNotFitItems() {
        return pnNotFitItems;
    }

    public void setPnNotFitItems(VBox pnNotFitItems) {
        this.pnNotFitItems = pnNotFitItems;
    }

    public VBox getPnAskedForItems() {
        return pnAskedForItems;
    }

    public void setPnAskedForItems(VBox pnAskedForItems) {
        this.pnAskedForItems = pnAskedForItems;
    }

    public VBox getPnHasItems() {
        return pnHasItems;
    }

    public void setPnHasItems(VBox pnHasItems) {
        this.pnHasItems = pnHasItems;
    }

    public VBox getPnPhysicalAilmentItems() {
        return pnPhysicalAilmentItems;
    }

    public void setPnPhysicalAilmentItems(VBox pnPhysicalAilmentItems) {
        this.pnPhysicalAilmentItems = pnPhysicalAilmentItems;
    }

    public VBox getPnUsedInItems() {
        return pnUsedInItems;
    }

    public void setPnUsedInItems(VBox pnUsedInItems) {
        this.pnUsedInItems = pnUsedInItems;
    }


    public VBox getPnPurchaseItems() {
        return pnPurchaseItems;
    }

    public void setPnPurchaseItems(VBox pnPurchaseItems) {
        this.pnPurchaseItems = pnPurchaseItems;
    }

    public VBox getPnMembershipItems() {
        return pnMembershipItems;
    }

    public void setPnMembershipItems(VBox pnMembershipItems) {
        this.pnMembershipItems = pnMembershipItems;
    }

    public VBox getPnLessonItems() {
        return pnLessonItems;
    }

    public void setPnLessonItems(VBox pnLessonItems) {
        this.pnLessonItems = pnLessonItems;
    }

    public VBox getPnBeProfessionItems() {
        return pnBeProfessionItems;
    }

    public void setPnBeProfessionItems(VBox pnBeProfessionItems) {
        this.pnBeProfessionItems = pnBeProfessionItems;
    }

    public VBox getPnSportToolsItems() {
        return pnSportToolsItems;
    }

    public void setPnSportToolsItems(VBox pnSportToolsItems) {
        this.pnSportToolsItems = pnSportToolsItems;
    }

    public VBox getPnProgramItems() {
        return pnProgramItems;
    }

    public void setPnProgramItems(VBox pnProgramItems) {
        this.pnProgramItems = pnProgramItems;
    }

    public Label getNorTrainer() {
        return norTrainer;
    }

    public Label getNorCustomer() {
        return norCustomer;
    }

    public Label getNorProfession() {
        return norProfession;
    }

    public Label getNorProgram() {
        return norProgram;
    }

    public Label getNorSportTools() {
        return norSportTools;
    }

    public Label getNorBeProfession() {
        return norBeProfession;
    }

    public Label getNorLesson() {
        return norLesson;
    }

    public Label getNorEnrollTime() {
        return norEnrollTime;
    }

    public Label getNorMembershipType() {
        return norMembershipType;
    }

    public Label getNorPurchase() {
        return norPurchase;
    }

    public Label getNorUsedIn() {
        return norUsedIn;
    }

    public Label getNorPhysicalAilment() {
        return norPhysicalAilment;
    }

    public Label getNorHas() {
        return norHas;
    }

    public Label getNorAskedFor() {
        return norAskedFor;
    }

    public Label getNorNotFit() {
        return norNotFit;
    }

    public Label getNorExercise() {
        return norExercise;
    }

    public Label getNorConsistOf() {
        return norConsistOf;
    }

    public Label getNorSystemUser() {
        return norSystemUser;
    }

    public VBox getPnProfessionItems() {
        return pnProfessionItems;
    }

    public void setPnProfessionItems(VBox pnProfessionItems) {
        this.pnProfessionItems = pnProfessionItems;
    }

    @FXML
    private VBox pnItems;



    @FXML
    private Button btnAdmin;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnTrainers;
    @FXML
    private Button btnProfession;
    @FXML
    private Button btnProgram;
    @FXML
    private Button btnBeProfession;
    @FXML
    private Button btnSportTools;
    @FXML
    private Button btnLesson;
    @FXML
    private Button btnEnrollTime;
    @FXML
    private Button btnMembershipType;
    @FXML
    private Button btnPurchase;

    @FXML
    private Button btnUsedIn;

    @FXML
    private Button btnHas;

    @FXML
    private Button btnAskedFor;

    @FXML
    private Button btnNotFit;

    @FXML
    private Button btnExercise;

    @FXML
    private Button btnConsistOf;

    @FXML
    private Button btnMakeWith;

    @FXML
    private Button btnSystemUser;

    @FXML
    private Button btnPhysicalAilment;

    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlTrainer;
    @FXML
    private Pane pnlProfession;
    @FXML
    private Pane pnlProgram;
    @FXML
    private Pane pnlSportTools;
    @FXML
    private Pane pnlBeProfession;
    @FXML
    private Pane pnlLesson;
    @FXML
    private Pane pnlEnrollTime;
    @FXML
    private Pane pnlMembershipType;
    @FXML
    private Pane pnlPurchase;
    @FXML
    private Pane pnlPhysicalAilment;

    @FXML
    private Pane pnlUsedIn;

    @FXML
    private Pane pnlHas;

    @FXML
    private Pane pnlAskedFor;

    @FXML
    private Pane pnlNotFit;

    @FXML
    private Pane pnlExercise;

    @FXML
    private Pane pnlConsistOf;

    @FXML
    private Pane pnlMakeWith;

    @FXML
    private Pane pnlSystemUser;


    @FXML
    private Pane pnlAdmin;


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private  Connection conn;

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    private ArrayList<Node> nodes;


    public Label getNorMakeWith() {
        return norMakeWith;
    }

    public void setNorMakeWith(Label norMakeWith) {
        this.norMakeWith = norMakeWith;
    }

    // For Counting Row
    @FXML
    private Label norTrainer;

    public Pane getPnlAdmin() {
        return pnlAdmin;
    }

    public void setPnlAdmin(Pane pnlAdmin) {
        this.pnlAdmin = pnlAdmin;
    }

    public Label getNorAdmin() {
        return norAdmin;
    }

    public void setNorAdmin(Label norAdmin) {
        this.norAdmin = norAdmin;
    }

    @FXML
    private Label norAdmin;
    @FXML
    private Label norCustomer;
    @FXML
    private Label norProfession;
    @FXML
    private Label norProgram;
    @FXML
    private Label norSportTools;
    @FXML
    private Label norBeProfession;
    @FXML
    private Label norLesson;
    @FXML
    private Label norEnrollTime;
    @FXML
    private Label norMembershipType;
    @FXML
    private Label norPurchase;
    @FXML
    private Label norUsedIn;
    @FXML
    private Label norPhysicalAilment;
    @FXML
    private Label norHas;
    @FXML
    private Label norAskedFor;
    @FXML
    private Label norNotFit;
    @FXML
    private Label norExercise;
    @FXML
    private Label norConsistOf;
    @FXML
    private Label norMakeWith;
    @FXML
    private Label norSystemUser;


    @FXML
    private Label norTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nodes = new ArrayList<>();
        try {
            conn = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/gym_management", "root", "123456yedi");
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            assert conn != null;


            String sql2 = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'gym_management'";

            DatabaseMetaData dbmd = conn.getMetaData();
            PreparedStatement st = conn.prepareStatement (sql2);
            String[] types = {"TABLE"};
            ResultSet resultSet = dbmd.getTables(null, null, "%", types);
            Statement stst = conn.createStatement();
            int countTable = 0;
            while (resultSet.next()) {
                String tableName = resultSet.getString(3);   // Get the table name
                // String tableCatalog = resultSet.getString(1);  // Get the table's catalog and schema names (if any)
                // String tableSchema = resultSet.getString(2);
                countTable++;
            }
            norTable.setText(String.valueOf(countTable));


            Statement stmt = conn.createStatement();
            FXMLLoader fxmlLoader = new FXMLLoader();

            String sql = "SELECT * FROM Customer";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            int i=0;
            FXMLLoader fxmlLoaderToAddInsert;
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertCustomer.fxml"));

            if(i==0){
                nodes.add(fxmlLoaderToAddInsert.load());
                CustomerItemController customerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                customerItemControllerInsert.setMainController(this);
                customerItemControllerInsert.createInsertBox();
                pnItems.getChildren().add(nodes.get(i));
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


                nodes.add(fxmlLoader.load());
                CustomerItemController customerItemController = (CustomerItemController) fxmlLoader.getController();

                customerItemController.setMainController(this);
                customerItemController.assignTextLabel(id , membershipTypeName, customerTrainerID,
                        weight, length, age, fatRatio, creditCardNumber, creditCardExpireDate);


                pnItems.getChildren().add(nodes.get(j));
                i++;
            }
            norCustomer.setText(String.valueOf(i));
            // -------------------------------

            fxmlLoader = new FXMLLoader();
            fxmlLoaderToAddInsert = new FXMLLoader();
            sql = "SELECT * FROM Trainer";
            rs = stmt.executeQuery(sql);


            //STEP 5: Extract data from result set

            i=0;

            ArrayList<Node> nodesTrainer = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertTrainer.fxml"));

            if(i==0){
                nodesTrainer.add(fxmlLoaderToAddInsert.load());
                TrainerItemController trainerItemControllerInsert = fxmlLoaderToAddInsert.getController();
                trainerItemControllerInsert.setMainController(this);
                pnTrainerItems.getChildren().add(nodesTrainer.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                final String id  = rs.getString("UsernameID");
                final String salary = rs.getString("Salary");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/TrainerItem.fxml"));


                nodesTrainer.add(fxmlLoader.load());
                TrainerItemController trainerItemController = fxmlLoader.getController();

                trainerItemController.setMainController(this);
                trainerItemController.assignTextLabel(id , salary);

                pnTrainerItems.getChildren().add(nodesTrainer.get(j));
                i++;
            }
            norTrainer.setText(String.valueOf(i));

            // -------------------------------


            sql = "SELECT * FROM Profession";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesProfession = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertProfession.fxml"));

            if(i==0){
                nodesProfession.add(fxmlLoaderToAddInsert.load());
                ProfessionItemController professionItemControllerInsert = fxmlLoaderToAddInsert.getController();
                professionItemControllerInsert.setMainController(this);
                pnProfessionItems.getChildren().add(nodesProfession.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String Name = rs.getString("Name");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ProfessionItem.fxml"));


                nodesProfession.add(fxmlLoader.load());
                ProfessionItemController professionItemController = (ProfessionItemController) fxmlLoader.getController();

                professionItemController.assignTextLabel(Name);
                professionItemController.setMainController(this);

                pnProfessionItems.getChildren().add(nodesProfession.get(j));
                i++;
            }
            norProfession.setText(String.valueOf(i));

            // -------------------------------


            sql = "SELECT * FROM Program";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesProgram = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertProgram.fxml"));

            if(i==0){
                nodesProgram.add(fxmlLoaderToAddInsert.load());
                ProgramItemController programItemControllerInsert = fxmlLoaderToAddInsert.getController();
                programItemControllerInsert.setMainController(this);
                pnProgramItems.getChildren().add(nodesProgram.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String ID = rs.getString("ID");
                String bmi = rs.getString("bmi");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ProgramItem.fxml"));

                nodesProgram.add(fxmlLoader.load());
                ProgramItemController programItemController = (ProgramItemController) fxmlLoader.getController();

                programItemController.assignTextLabel(ID,bmi);
                programItemController.setMainController(this);
                pnProgramItems.getChildren().add(nodesProgram.get(j));
                i++;
            }
            norProgram.setText(String.valueOf(i));

            // -------------------------------


            sql = "SELECT * FROM Sport_Tools";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesSportTools = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertSportTools.fxml"));

            if(i==0){
                nodesSportTools.add(fxmlLoaderToAddInsert.load());
                SportToolItemController sportToolItemControllerInsert = fxmlLoaderToAddInsert.getController();
                sportToolItemControllerInsert.setMainController(this);
                pnSportToolsItems.getChildren().add(nodesSportTools.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String name  = rs.getString("Name");
                String amount = rs.getString("Amount");
                String urlImage = rs.getString("urlImage");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/SportToolItem.fxml"));


                nodesSportTools.add(fxmlLoader.load());
                SportToolItemController sportToolItemController = (SportToolItemController) fxmlLoader.getController();

                sportToolItemController.assignTextLabel(name,amount, urlImage);
                sportToolItemController.setMainController(this);

                pnSportToolsItems.getChildren().add(nodesSportTools.get(j));
                i++;
            }
            norSportTools.setText(String.valueOf(i));

            // -------------------------------


            sql = "SELECT * FROM Be_Profession";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesBeProfession = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertBeProfession.fxml"));

            if(i==0){
                nodesBeProfession.add(fxmlLoaderToAddInsert.load());
                BeProfessionItemController beProfessionItemController = fxmlLoaderToAddInsert.getController();
                beProfessionItemController.setMainController(this);
                beProfessionItemController.createInsertBox();
                pnBeProfessionItems.getChildren().add(nodesBeProfession.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String name = rs.getString("ProfessionName");
                String ID = rs.getString("TrainerID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/BeProfessionItem.fxml"));

                nodesBeProfession.add(fxmlLoader.load());
                BeProfessionItemController beProfessionItemController = (BeProfessionItemController) fxmlLoader.getController();

                beProfessionItemController.setMainController(this);
                beProfessionItemController.assignTextLabel(name,ID);

                pnBeProfessionItems.getChildren().add(nodesBeProfession.get(j));
                i++;
            }
            norBeProfession.setText(String.valueOf(i));
            // -------------------------------



            sql = "SELECT * FROM Lesson";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesLesson = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertLesson.fxml"));

            if(i==0){
                nodesLesson.add(fxmlLoaderToAddInsert.load());
                LessonItemController lessonItemController = fxmlLoaderToAddInsert.getController();
                lessonItemController.setMainController(this);
                lessonItemController.createInsertBox();
                pnLessonItems.getChildren().add(nodesLesson.get(i));
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


                lessonItemController.setMainController(this);
                lessonItemController.assignTextLabel(ID,name, id, professionName, price);

                pnLessonItems.getChildren().add(nodesLesson.get(j));
                i++;
            }
            norLesson.setText(String.valueOf(i));
            // -------------------------------


            sql = "SELECT * FROM Enroll_Time";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesEnrollTime = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertEnrollTime.fxml"));

            if(i==0){
                nodesEnrollTime.add(fxmlLoaderToAddInsert.load());
                EnrollTimeItemController enrollTimeItemController = fxmlLoaderToAddInsert.getController();
                enrollTimeItemController.setMainController(this);
                enrollTimeItemController.createInsertBox();
                pnEnrollTimeItems.getChildren().add(nodesEnrollTime.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String lessonID = rs.getString("LessonID");
                String Day = rs.getString("Day");
                String StartTime  = rs.getString("StartTime");
                String EndTime = rs.getString("EndTime");
                String Quota = rs.getString("Quota");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/EnrollTimeItem.fxml"));


                nodesEnrollTime.add(fxmlLoader.load());
                EnrollTimeItemController enrollTimeItemController = (EnrollTimeItemController) fxmlLoader.getController();

                enrollTimeItemController.setMainController(this);
                enrollTimeItemController.assignTextLabel(lessonID,Day,StartTime,EndTime,Quota);

                pnEnrollTimeItems.getChildren().add(nodesEnrollTime.get(j));
                i++;
            }
            norEnrollTime.setText(String.valueOf(i));

            // -------------------------------


            sql = "SELECT * FROM Membership_Type";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesMembership_Type = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertMembershipType.fxml"));

            if(i==0){
                nodesMembership_Type.add(fxmlLoaderToAddInsert.load());
                MembershipTypeItemController membershipTypeItemController = fxmlLoaderToAddInsert.getController();
                membershipTypeItemController.setMainController(this);
                pnMembershipItems.getChildren().add(nodesMembership_Type.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String Name = rs.getString("Name");
                String price = rs.getString("price");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/MembershipItem.fxml"));


                nodesMembership_Type.add(fxmlLoader.load());
                MembershipTypeItemController membershipTypeItemController = (MembershipTypeItemController) fxmlLoader.getController();

                membershipTypeItemController.assignTextLabel(Name,price);
                membershipTypeItemController.setMainController(this);

                pnMembershipItems.getChildren().add(nodesMembership_Type.get(j));
                i++;
            }
            norMembershipType.setText(String.valueOf(i));

            // -------------------------------

            sql = "SELECT * FROM Purchase";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesPurchase = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertPurchase.fxml"));

            if(i==0){
                nodesPurchase.add(fxmlLoaderToAddInsert.load());
                PurchaseItemController purchaseItemController = fxmlLoaderToAddInsert.getController();
                purchaseItemController.setMainController(this);
                purchaseItemController.createInsertBox();
                pnPurchaseItems.getChildren().add(nodesPurchase.get(i));
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

                purchaseItemController.setMainController(this);
                purchaseItemController.assignTextLabel(customerID,LessonID,enrollTimeDay,PurchasedDate);

                pnPurchaseItems.getChildren().add(nodesPurchase.get(j));
                i++;
            }
            norPurchase.setText(String.valueOf(i));


            // -------------------------------


            sql = "SELECT * FROM Used_In";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesUsedIn = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertUsedIn.fxml"));

            if(i==0){
                nodesUsedIn.add(fxmlLoaderToAddInsert.load());
                UsedInItemController usedInItemController = fxmlLoaderToAddInsert.getController();
                usedInItemController.setMainController(this);
                usedInItemController.createInsertBox();
                pnUsedInItems.getChildren().add(nodesUsedIn.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String SportToolsName = rs.getString("SportToolsName");
                String LessonID = rs.getString("LessonID");
                String Quantity = rs.getString("Quantity");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/UsedInItem.fxml"));

                nodesUsedIn.add(fxmlLoader.load());
                UsedInItemController usedInItemController = (UsedInItemController) fxmlLoader.getController();

                usedInItemController.setMainController(this);
                usedInItemController.assignTextLabel(SportToolsName,LessonID,Quantity);

                pnUsedInItems.getChildren().add(nodesUsedIn.get(j));
                i++;
            }
            norUsedIn.setText(String.valueOf(i));

            // -------------------------------


            sql = "SELECT * FROM Physical_Ailment";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesPhysical_Ailment = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertPhysicalAilment.fxml"));

            if(i==0){
                nodesPhysical_Ailment.add(fxmlLoaderToAddInsert.load());
                PhysicalAilmentItemController physicalAilmentItemController = fxmlLoaderToAddInsert.getController();
                physicalAilmentItemController.setMainController(this);
                pnPhysicalAilmentItems.getChildren().add(nodesPhysical_Ailment.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String PhysicalAilmentName = rs.getString("Name");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/PhysicalAilmentItem.fxml"));


                nodesPhysical_Ailment.add(fxmlLoader.load());
                PhysicalAilmentItemController physicalAilmentItemController = (PhysicalAilmentItemController) fxmlLoader.getController();

                physicalAilmentItemController.assignTextLabel(PhysicalAilmentName);
                physicalAilmentItemController.setMainController(this);

                pnPhysicalAilmentItems.getChildren().add(nodesPhysical_Ailment.get(j));
                i++;
            }
            norPhysicalAilment.setText(String.valueOf(i));
            // -------------------------------


            sql = "SELECT * FROM Has";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesHas = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertHas.fxml"));

            if(i==0){
                nodesHas.add(fxmlLoaderToAddInsert.load());
                HasItemController hasItemController = fxmlLoaderToAddInsert.getController();
                hasItemController.setMainController(this);
                hasItemController.createInsertBox();
                pnHasItems.getChildren().add(nodesHas.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String customerID = rs.getString("CustomerID");
                String PhysicalAilmentName = rs.getString("PyhsicalAilmentName");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/HasItem.fxml"));

                nodesHas.add(fxmlLoader.load());
                HasItemController hasItemController = (HasItemController) fxmlLoader.getController();

                hasItemController.setMainController(this);
                hasItemController.assignTextLabel(customerID,PhysicalAilmentName);

                pnHasItems.getChildren().add(nodesHas.get(j));
                i++;
            }
            norHas.setText(String.valueOf(i));
            // -------------------------------


            sql = "SELECT * FROM Asked_For";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesAsked_For = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertAskedFor.fxml"));

            if(i==0){
                nodesAsked_For.add(fxmlLoaderToAddInsert.load());
                AskedForItemController askedForItemController = fxmlLoaderToAddInsert.getController();
                askedForItemController.setMainController(this);
                askedForItemController.createInsertBox();
                pnAskedForItems.getChildren().add(nodesAsked_For.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String customerID = rs.getString("CustomerID");
                String ProgramID = rs.getString("ProgramID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/AskedForItem.fxml"));


                nodesAsked_For.add(fxmlLoader.load());
                AskedForItemController askedForItemController = (AskedForItemController) fxmlLoader.getController();

                askedForItemController.setMainController(this);
                askedForItemController.assignTextLabel(customerID,ProgramID);

                pnAskedForItems.getChildren().add(nodesAsked_For.get(j));
                i++;
            }
            norAskedFor.setText(String.valueOf(i));
            // -------------------------------

            sql = "SELECT * FROM Not_Fit";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesNotFit = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertNotFit.fxml"));

            if(i==0){
                nodesNotFit.add(fxmlLoaderToAddInsert.load());
                NotFitItemController notFitItemController = fxmlLoaderToAddInsert.getController();
                notFitItemController.setMainController(this);
                notFitItemController.createInsertBox();
                pnNotFitItems.getChildren().add(nodesNotFit.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String PyhsicalAilmentName = rs.getString("PyhsicalAilmentName");
                String ProgramID = rs.getString("ProgramID");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/NotFitItem.fxml"));


                nodesNotFit.add(fxmlLoader.load());
                NotFitItemController notFitItemController = (NotFitItemController) fxmlLoader.getController();

                notFitItemController.setMainController(this);
                notFitItemController.assignTextLabel(PyhsicalAilmentName,ProgramID);

                pnNotFitItems.getChildren().add(nodesNotFit.get(j));
                i++;
            }
            norNotFit.setText(String.valueOf(i));

            // -------------------------------



            sql = "SELECT * FROM Exercise";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesExercise = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertExercise.fxml"));

            if(i==0){
                nodesExercise.add(fxmlLoaderToAddInsert.load());
                ExerciseItemController exerciseItemController = fxmlLoaderToAddInsert.getController();
                exerciseItemController.setMainController(this);
                pnExerciseItems.getChildren().add(nodesExercise.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String Name = rs.getString("Name");
                String Set = rs.getString("Set");
                String RepetitionPerSet = rs.getString("RepetitionPerSet");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ExerciseItem.fxml"));


                nodesExercise.add(fxmlLoader.load());
                ExerciseItemController exerciseItemController = (ExerciseItemController) fxmlLoader.getController();

                exerciseItemController.assignTextLabel(Name,Set,RepetitionPerSet);
                exerciseItemController.setMainController(this);

                pnExerciseItems.getChildren().add(nodesExercise.get(j));
                i++;
            }
            norExercise.setText(String.valueOf(i));
            // -------------------------------


            sql = "SELECT * FROM Consist_Of";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesConsistOf = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertConsistOf.fxml"));

            if(i==0){
                nodesConsistOf.add(fxmlLoaderToAddInsert.load());
                ConsistOfItemController consistOfItemController = fxmlLoaderToAddInsert.getController();
                consistOfItemController.setMainController(this);
                consistOfItemController.createInsertBox();
                pnConsistOfItems.getChildren().add(nodesConsistOf.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String ProgramID = rs.getString("ProgramID");
                String ExerciseName = rs.getString("ExerciseName");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/ConsistOfItem.fxml"));


                nodesConsistOf.add(fxmlLoader.load());
                ConsistOfItemController consistOfItemController = (ConsistOfItemController) fxmlLoader.getController();


                consistOfItemController.setMainController(this);
                consistOfItemController.assignTextLabel(ProgramID,ExerciseName);

                pnConsistOfItems.getChildren().add(nodesConsistOf.get(j));
                i++;
            }
            norConsistOf.setText(String.valueOf(i));
            // -------------------------------


            sql = "SELECT * FROM Make_With";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesMakeWith = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertMakeWith.fxml"));

            if(i==0){
                nodesMakeWith.add(fxmlLoaderToAddInsert.load());
                MakeWithItemController makeWithItemController = fxmlLoaderToAddInsert.getController();
                makeWithItemController.setMainController(this);
                makeWithItemController.createInsertBox();
                pnMakeWithItems.getChildren().add(nodesMakeWith.get(i));
            }
            while(rs.next()){
                //Retrieve by column name
                final int j = i+1;
                String SportToolsName = rs.getString("SportToolsName");
                String ExerciseName = rs.getString("ExerciseName");

                fxmlLoader = new FXMLLoader(getClass().getResource("../ItemsFXML/MakeWithItem.fxml"));


                nodesMakeWith.add(fxmlLoader.load());
                MakeWithItemController makeWithItemController = (MakeWithItemController) fxmlLoader.getController();

                makeWithItemController.setMainController(this);
                makeWithItemController.assignTextLabel(SportToolsName,ExerciseName);

                pnMakeWithItems.getChildren().add(nodesMakeWith.get(j));
                i++;
            }
            norMakeWith.setText(String.valueOf(i));

            // -------------------------------


            sql = "SELECT * FROM `System_User`";
            rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            i=0;

            ArrayList<Node> nodesSystemUser = new ArrayList<>();
            fxmlLoaderToAddInsert = new FXMLLoader(getClass().getResource("../InsertItems/InsertSystemUser.fxml"));

            if(i==0){
                nodesSystemUser.add(fxmlLoaderToAddInsert.load());
                SystemUserItemController systemUserItemController = fxmlLoaderToAddInsert.getController();
                systemUserItemController.setMainController(this);
                pnSystemUserItems.getChildren().add(nodesSystemUser.get(i));
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
                systemUserItemController.setMainController(this);

                pnSystemUserItems.getChildren().add(nodesSystemUser.get(j));
                i++;
            }
            norSystemUser.setText(String.valueOf(i));



        } catch (IOException e) {
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Mouse enteredlar eklenmeli ------------------> <----------------------------

    @FXML
    public void methodToMouseEnteredBtnCustomers(){
        this.btnCustomers.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnCustomers(){
        this.btnCustomers.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnTrainer(){
        this.btnTrainers.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnTrainer(){
        this.btnTrainers.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnProfession(){
        this.btnProfession.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnProfession(){
        this.btnProfession.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnProgram(){
        this.btnProgram.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnProgram(){
        this.btnProgram.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnSportTool(){
        this.btnSportTools.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnSportTool(){
        this.btnSportTools.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnBeProfession(){
        this.btnBeProfession.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnBeProfession(){
        this.btnBeProfession.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnEnrollTime(){
        this.btnEnrollTime.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnEnrollTime(){
        this.btnEnrollTime.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnLesson(){
        this.btnLesson.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnLesson(){
        this.btnLesson.setStyle("-fx-background-color : #2B2B2B");
    }


    @FXML
    public void methodToMouseEnteredBtnMembershipType(){
        this.btnMembershipType.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnMembershipType(){
        this.btnMembershipType.setStyle("-fx-background-color : #2B2B2B");
    }
    @FXML
    public void methodToMouseEnteredBtnPurchase(){
        this.btnPurchase.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnPurchase(){
        this.btnPurchase.setStyle("-fx-background-color : #2B2B2B");
    }
    @FXML
    public void methodToMouseEnteredBtnUsedIn(){
        this.btnUsedIn.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnUsedIn(){
        this.btnUsedIn.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnPhysicalAilment(){
        this.btnPhysicalAilment.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnPhysicalAilment(){
        this.btnPhysicalAilment.setStyle("-fx-background-color : #2B2B2B");
    }


    @FXML
    public void methodToMouseEnteredBtnHas(){
        this.btnHas.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnHas(){
        this.btnHas.setStyle("-fx-background-color : #2B2B2B");
    }


    @FXML
    public void methodToMouseEnteredBtnAskedFor(){
        this.btnAskedFor.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnAskedFor(){
        this.btnAskedFor.setStyle("-fx-background-color : #2B2B2B");
    }


    @FXML
    public void methodToMouseEnteredBtnNotFit(){
        this.btnNotFit.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnNotFit(){
        this.btnNotFit.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnExercise(){
        this.btnExercise.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnExercise(){
        this.btnExercise.setStyle("-fx-background-color : #2B2B2B");
    }

    @FXML
    public void methodToMouseEnteredBtnConsistOf(){
        this.btnConsistOf.setStyle("-fx-background-color : #4C5052");
    }

    @FXML
    public void methodToMouseExitedBtnConsistOf(){
        this.btnConsistOf.setStyle("-fx-background-color : #2B2B2B");
    }
    @FXML
    public void methodToMouseEnteredBtnMakeWith(){
        this.btnMakeWith.setStyle("-fx-background-color : #4C5052");
    }
    @FXML
    public void methodToMouseExitedBtnMakeWith(){
        this.btnMakeWith.setStyle("-fx-background-color : #2B2B2B");
    }
    @FXML
    public void methodToMouseEnteredBtnSystemUser(){
        this.btnSystemUser.setStyle("-fx-background-color : #4C5052");
    }
    @FXML
    public void methodToMouseExitedBtnSystemUser(){
        this.btnSystemUser.setStyle("-fx-background-color : #2B2B2B");
    }



    public void handleClicks(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnCustomers) {

            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnTrainers) {
            pnlTrainer.toFront();
        }

        if(actionEvent.getSource() == btnProfession){
            pnlProfession.toFront();
        }
        if(actionEvent.getSource() == btnProgram){
            pnlProgram.toFront();
        }
        if (actionEvent.getSource() == btnSportTools) {
            pnlSportTools.toFront();

        }
        if (actionEvent.getSource() == btnBeProfession) {
            pnlBeProfession.toFront();

        }
        if (actionEvent.getSource() == btnLesson) {
            pnlLesson.toFront();

        }
        if (actionEvent.getSource() == btnEnrollTime) {
            pnlEnrollTime.toFront();

        }
        if (actionEvent.getSource() == btnMembershipType) {
            pnlMembershipType.toFront();

        }
        if (actionEvent.getSource() == btnPurchase) {
            pnlPurchase.toFront();


        }
        if (actionEvent.getSource() == btnUsedIn) {
            pnlUsedIn.toFront();

        }
        if (actionEvent.getSource() == btnHas) {
            pnlHas.toFront();

        }
        if (actionEvent.getSource() == btnAskedFor) {
            pnlAskedFor.toFront();

        }
        if (actionEvent.getSource() == btnNotFit) {
            pnlNotFit.toFront();

        }
        if (actionEvent.getSource() == btnExercise) {
            pnlExercise.toFront();

        }
        if (actionEvent.getSource() == btnConsistOf) {
            pnlConsistOf.toFront();

        }
        if (actionEvent.getSource() == btnMakeWith) {
            pnlMakeWith.toFront();

        }
        if (actionEvent.getSource() == btnSystemUser) {
            pnlSystemUser.toFront();

        }
        if (actionEvent.getSource() == btnPhysicalAilment) {
            pnlPhysicalAilment.toFront();
        }

    }
}