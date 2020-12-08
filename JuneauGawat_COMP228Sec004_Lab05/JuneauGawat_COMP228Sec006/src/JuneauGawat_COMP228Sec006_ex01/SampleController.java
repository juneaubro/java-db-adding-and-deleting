package JuneauGawat_COMP228Sec006_ex01;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class SampleController implements Initializable{
	
	 	@FXML
	    private TextField playerTablePhoneNumberTextArea;

	    @FXML
	    private Pane playerGameTablePane;

	    @FXML
	    private TextField playerTableFirstNameTextArea;

	    @FXML
	    private Button gameTableInsertButton;

	    @FXML
	    private TextField playerTableLastNameTextArea;

	    @FXML
	    private TextField playerAndGameTablePlayerGameIDTextArea;

	    @FXML
	    private TextField playerAndGameTablePlayingDateTextArea;

	    @FXML
	    private TextField playerAndGameTableScoreTextArea;

	    @FXML
	    private TextField playerTableProvinceTextArea;

	    @FXML
	    private Button playerAndGameTableInsertButton;

	    @FXML
	    private Pane gameTablePane;

	    @FXML
	    private TextField playerTableAddressTextArea;

	    @FXML
	    private Pane playerTablePane;

	    @FXML
	    private TextField playerAndGameTableGameIDTextArea;

	    @FXML
	    private TextField playerTablePostalCodeTextArea;

	    @FXML
	    private Button playerTableInsertButton;
	    
	    @FXML
	    private Button refreshBtn;

	    @FXML
	    private TextField gameTableGameTitleTextArea;
	    
	    @FXML
	    private TextField playerID;
	    
	    @FXML
	    private TextField gameID;
	    
	    @FXML
	    private TextField playerIDView;

	    @FXML
	    private TextField gameIDView;
	    
	    private Connection con;
		
		private PreparedStatement pst;
		
		private ResultSet rs;
		
		private Statement st;
		
		@FXML
	    private Button gameDelBtn;

	    @FXML
	    private Button playerDelBtn;
	    
	    @FXML
	    private Button playerAndGameDelBtn;
	    
	    @FXML
	    private TextField gameIDSearch;
	    
	    @FXML
	    private TextField playerIDSearch;
	    
	    @FXML
	    private TextField playerAndGameIDSearch;
	    
	    @FXML
	    private ListView<String> console;
	    
	    @FXML
	    private ListView<String> playerAndGameTable;
	    
	    @FXML
	    private ListView<String> gameTable;
	    
	    @FXML
	    private ListView<String> playerTable;
	    
	    private ObservableList<String> gameInfo = FXCollections.observableArrayList();
	    
	    private ObservableList<String> playerAndGameInfo = FXCollections.observableArrayList();
	    
	    private ObservableList<String> playerInfo = FXCollections.observableArrayList();

		@FXML
	    void typingGameIDTextArea(KeyEvent event) {
			gameIDView.setText(gameID.getText());
	    }
		
		@FXML
	    void typingPlayerIDTextArea(KeyEvent event) {
			playerIDView.setText(playerID.getText());
	    }
		
		@FXML
	    void typingGameIDTextArea2(KeyEvent event) {
			gameID.setText(gameIDView.getText());
	    }

	    @FXML
	    void typingPlayerIDTextArea2(KeyEvent event) {
	    	playerID.setText(playerIDView.getText());
	    }
		
	    @FXML
	    void insertGameTable(ActionEvent event) throws SQLException {
	    	String gameTitle = gameTableGameTitleTextArea.getText();
	    	String sql = "insert into game values('"+gameID.getText()+"','"+gameTitle+"')";
	    	
	    	try {
				pst = con.prepareStatement(sql);
				if(pst.executeUpdate() == 1) {
					System.out.println("Row inserted.");
					console.setItems(FXCollections.observableArrayList("Row inserted."));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.setItems(FXCollections.observableArrayList(e.getMessage()));
			}
	    	finally {
	    		pst.close();
	    	}
	    	
	    	rs = st.executeQuery("select * from game");
			gameTable.getItems().clear();
			while(rs.next()) {
				gameInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\n");
			}
			gameTable.setItems(gameInfo);
	    }

	    @FXML
	    void insertPlayerTable(ActionEvent event) throws SQLException {
	    	String fName = playerTableFirstNameTextArea.getText();
	    	String lName = playerTableLastNameTextArea.getText();
	    	String address = playerTableAddressTextArea.getText();
	    	String pC = playerTablePostalCodeTextArea.getText();
	    	String province = playerTableProvinceTextArea.getText();
	    	String pNum = playerTablePhoneNumberTextArea.getText();
	    	String sql = "insert into player values('"+playerID.getText()+"','"+fName+"','"+lName+"','"+address+"','"+pC+"','"+province+"','"+pNum+"')";
	    	
	    	try {
				pst = con.prepareStatement(sql);
				if(pst.executeUpdate() == 1) {
					System.out.println("Row inserted.");
					console.setItems(FXCollections.observableArrayList("Row inserted."));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.setItems(FXCollections.observableArrayList(e.getMessage()));
			}
	    	finally {
	    		pst.close();
	    	}
	    	
	    	rs = st.executeQuery("select * from player");
			playerTable.getItems().clear();
			while(rs.next()) {
				playerInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\n");
			}
			playerTable.setItems(playerInfo);
	    }

	    @FXML
	    void insertPlayerAndGameTable(ActionEvent event) throws SQLException {
	    	String playerGameID = playerAndGameTablePlayerGameIDTextArea.getText();
	    	String playingDate = playerAndGameTablePlayingDateTextArea.getText();
	    	String score = playerAndGameTableScoreTextArea.getText();
	    	String sql = "insert into playerandgame values('"+playerGameID+"','"+gameID.getText()+"','"+playerID.getText()+"','"+playingDate+"','"+score+"')";
	    	
	    	try {
				pst = con.prepareStatement(sql);
				if(pst.executeUpdate() == 1) {
					System.out.println("Row inserted.");
					console.setItems(FXCollections.observableArrayList("Row inserted."));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.setItems(FXCollections.observableArrayList(e.getMessage()));
			}
			finally {
	    		pst.close();
	    	}
	    	
	    	rs = st.executeQuery("select * from playerandgame");
			playerAndGameTable.getItems().clear();
			while(rs.next()) {
				playerAndGameInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\n");
			}
			playerAndGameTable.setItems(playerAndGameInfo);
	    }

		@Override
		public void initialize(URL url, ResourceBundle rb) {
			try {
				con = DriverManager.getConnection("jdbc:oracle:thin:@ 199.212.26.208:1521:SQLD"," COMP228_M20_001_8", "password");
				System.out.println("Connected to database.");
				console.setItems(FXCollections.observableArrayList("Connected to database."));
				st = con.createStatement();
				
				// getting game table
				rs = st.executeQuery("select * from game");
				System.out.println("Get request sent to DB.");
				console.setItems(FXCollections.observableArrayList("Get request sent to DB."));
				while(rs.next()) {
					gameInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\n");
				}
				System.out.println("Get request recieved.");
				console.setItems(FXCollections.observableArrayList("Get request recieved."));
				
				// getting player table
				rs = st.executeQuery("select * from player");
				System.out.println("Get request sent to DB.");
				console.setItems(FXCollections.observableArrayList("Get request sent to DB."));
				while(rs.next()) {
					playerInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\n");
				}
				System.out.println("Get request recieved.");
				console.setItems(FXCollections.observableArrayList("Get request recieved."));
				
				// getting player and game table
				rs = st.executeQuery("select * from playerandgame");
				System.out.println("Get request sent to DB.");
				console.setItems(FXCollections.observableArrayList("Get request sent to DB."));
				while(rs.next()) {
					playerAndGameInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\n");
				}
				System.out.println("Get request recieved.");
				console.setItems(FXCollections.observableArrayList("Get request recieved."));
				
				gameTable.setItems(gameInfo);
				playerTable.setItems(playerInfo);
				playerAndGameTable.setItems(playerAndGameInfo);
				
				System.out.println("Data displayed.");
				System.out.println("Connected to database.");
				console.setItems(FXCollections.observableArrayList("Data displayed."));
				console.setItems(FXCollections.observableArrayList("Connected to database."));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.setItems(FXCollections.observableArrayList(e.getMessage()));
			}
		}
		
		@FXML
	    void playerAndGameDelButtonClick(ActionEvent event) throws SQLException {
			String sql = "DELETE FROM playerandgame WHERE player_game_id='"+playerAndGameIDSearch.getText()+"'";
			
			try {
				pst = con.prepareStatement(sql);
				if(pst.executeUpdate() == 1) {
					System.out.println("Row deleted from PLAYERANDGAME table.");
					console.setItems(FXCollections.observableArrayList("Row deleted from PLAYERANDGAME table."));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.setItems(FXCollections.observableArrayList(e.getMessage()));
			}
			finally {
	    		pst.close();
	    	}
			
			rs = st.executeQuery("select * from playerandgame");
			playerAndGameTable.getItems().clear();
			while(rs.next()) {
				playerAndGameInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\n");
			}
			playerAndGameTable.setItems(playerAndGameInfo);
	    }
		
		@FXML
	    void playerDelButtonClick(ActionEvent event) throws SQLException {
			String sql = "DELETE FROM player WHERE player_id='"+playerIDSearch.getText()+"'";
			
			try {
				pst = con.prepareStatement(sql);
				if(pst.executeUpdate() == 1) {
					System.out.println("Row deleted from PLAYER table.");
					console.setItems(FXCollections.observableArrayList("Row deleted from PLAYER table."));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.setItems(FXCollections.observableArrayList(e.getMessage()));
			}
			finally {
	    		pst.close();
	    	}
			
			rs = st.executeQuery("select * from player");
			playerTable.getItems().clear();
			while(rs.next()) {
				playerInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\n"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\n");
			}
			playerTable.setItems(playerInfo);
	    }
		
		@FXML
	    void gameDelButtonClick(ActionEvent event) throws SQLException {
			String sql = "DELETE FROM game WHERE game_id='"+gameIDSearch.getText()+"'";
			
			try {
				pst = con.prepareStatement(sql);
				if(pst.executeUpdate() == 1) {
					System.out.println("Row deleted from GAME table.");
					console.setItems(FXCollections.observableArrayList("Row deleted from GAME table."));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				console.setItems(FXCollections.observableArrayList(e.getMessage()));
			}
			finally {
	    		pst.close();
	    	}
			
			rs = st.executeQuery("select * from game");
			gameTable.getItems().clear();
			while(rs.next()) {
				gameInfo.addAll(rs.getString(1)+"\t\t"+rs.getString(2)+"\n");
			}
			gameTable.setItems(gameInfo);
	    }
}
