package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;


public class Main extends Application { // Application Ŭ������ ��ӹ޾� JavaFX ���α׷��� �����.
	@Override
	public void start(Stage primaryStage) throws Exception{ // start() �޼ҵ忡�� �߻��ϴ� ���ܸ� thorws�� ó���Ѵ�.
		FlowPane root = new FlowPane();
		root.setPrefSize(350, 230);
		root.setAlignment(Pos.CENTER);
		
		TilePane numPane = new TilePane();
		numPane.setAlignment(Pos.CENTER); 
		numPane.setPrefWidth(350); 
		numPane.setPrefHeight(143);
		numPane.setHgap(30); // numPane�� ��Ʈ�ѵ��� ���� ������ 30�� ���ϴ�.
		
		Label numArr[] = new Label[3]; // ���ڸ� ����� Label�� �迭�� ����
		
		for(int i = 0 ; i < 3 ; i++) {
			numArr[i] = new Label("0"); // 0���� �ʱ�ȭ�� Label ��ü ����
			numArr[i].setFont(Font.font("Arial",FontPosture.ITALIC,36)); // �۲� : Arial, �۾��� ���ִ� Ÿ�� : ITALIC, ũ�� : 36�ȼ�
			numArr[i].setTextFill(Color.YELLOW); // ������ ���� ��������� �����Ѵ�.
			numArr[i].setPrefWidth(70);
			numArr[i].setPrefHeight(40);
			numArr[i].setAlignment(Pos.CENTER);
			numArr[i].setStyle("-fx-background-color: purple"); // ���ڷ��̺��� ������ ��������� �����Ѵ�.
			numPane.getChildren().add(numArr[i]);
		}
		
		Label la = new Label("�����մϴ�.");
		
		root.getChildren().addAll(numPane,la);
		
		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				int n1 = (int)(Math.random() * 5); //0~4�� ������ ����
			    int n2 = (int)(Math.random() * 5);
			    int n3 = (int)(Math.random() * 5);
			    
			    KeyCode code = event.getCode();
			    
			    if(code == KeyCode.ENTER) {
			        numArr[0].setText(Integer.toString(n1)); //numberLabel�� ���� ����
			        numArr[1].setText(Integer.toString(n2));
			        numArr[2].setText(Integer.toString(n3));
			  
			        if(n1 == n2 && n2 == n3) { //�� ���� ���� ��� ������ �Ǻ�
			          la.setText("�����մϴ�!!"); // label�� ����
			        }
			  
			        else {
			          la.setText("�ƽ�����");
			        }
			    }
			}
		});
		
		 // ������ �� ������ ���ٽ����� ������ ������� ������ �������� ���δ�.
		 /*scene.setOnKeyPressed(event->{
				int n1 = (int)(Math.random() * 5); //0~4�� ������ ����
			    int n2 = (int)(Math.random() * 5);
			    int n3 = (int)(Math.random() * 5);
			    
			    KeyCode code = event.getCode();
			    
			    if(code == KeyCode.ENTER) {
			        numArr[0].setText(Integer.toString(n1)); //numberLabel�� ���� ����
			        numArr[1].setText(Integer.toString(n2));
			        numArr[2].setText(Integer.toString(n3));
			  
			        if(n1 == n2 && n2 == n3) { //�� ���� ���� ��� ������ �Ǻ�
			          la.setText("�����մϴ�!!"); // label�� ����
			        }
			  
			        else {
			          la.setText("�ƽ�����");
			        }
			    }
		 });*/
		
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Gambling");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
