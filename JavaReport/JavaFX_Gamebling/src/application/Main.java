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


public class Main extends Application { // Application 클래스를 상속받아 JavaFX 프로그램을 만든다.
	@Override
	public void start(Stage primaryStage) throws Exception{ // start() 메소드에서 발생하는 예외를 thorws로 처리한다.
		FlowPane root = new FlowPane();
		root.setPrefSize(350, 230);
		root.setAlignment(Pos.CENTER);
		
		TilePane numPane = new TilePane();
		numPane.setAlignment(Pos.CENTER); 
		numPane.setPrefWidth(350); 
		numPane.setPrefHeight(143);
		numPane.setHgap(30); // numPane의 컨트롤들의 가로 간격을 30씩 띄웁니다.
		
		Label numArr[] = new Label[3]; // 숫자를 출력할 Label을 배열로 관리
		
		for(int i = 0 ; i < 3 ; i++) {
			numArr[i] = new Label("0"); // 0으로 초기화된 Label 객체 생성
			numArr[i].setFont(Font.font("Arial",FontPosture.ITALIC,36)); // 글꼴 : Arial, 글씨가 서있는 타입 : ITALIC, 크기 : 36픽셀
			numArr[i].setTextFill(Color.YELLOW); // 숫자의 색을 노란색으로 설정한다.
			numArr[i].setPrefWidth(70);
			numArr[i].setPrefHeight(40);
			numArr[i].setAlignment(Pos.CENTER);
			numArr[i].setStyle("-fx-background-color: purple"); // 숫자레이블의 배경색을 보라색으로 설정한다.
			numPane.getChildren().add(numArr[i]);
		}
		
		Label la = new Label("시작합니다.");
		
		root.getChildren().addAll(numPane,la);
		
		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				int n1 = (int)(Math.random() * 5); //0~4의 랜덤한 숫자
			    int n2 = (int)(Math.random() * 5);
			    int n3 = (int)(Math.random() * 5);
			    
			    KeyCode code = event.getCode();
			    
			    if(code == KeyCode.ENTER) {
			        numArr[0].setText(Integer.toString(n1)); //numberLabel의 값을 변경
			        numArr[1].setText(Integer.toString(n2));
			        numArr[2].setText(Integer.toString(n3));
			  
			        if(n1 == n2 && n2 == n3) { //세 라벨의 수가 모두 같은지 판별
			          la.setText("축하합니다!!"); // label을 변경
			        }
			  
			        else {
			          la.setText("아쉽군요");
			        }
			    }
			}
		});
		
		 // 다음은 위 문장을 람다식으로 구현한 방법으로 동일한 실행결과를 보인다.
		 /*scene.setOnKeyPressed(event->{
				int n1 = (int)(Math.random() * 5); //0~4의 랜덤한 숫자
			    int n2 = (int)(Math.random() * 5);
			    int n3 = (int)(Math.random() * 5);
			    
			    KeyCode code = event.getCode();
			    
			    if(code == KeyCode.ENTER) {
			        numArr[0].setText(Integer.toString(n1)); //numberLabel의 값을 변경
			        numArr[1].setText(Integer.toString(n2));
			        numArr[2].setText(Integer.toString(n3));
			  
			        if(n1 == n2 && n2 == n3) { //세 라벨의 수가 모두 같은지 판별
			          la.setText("축하합니다!!"); // label을 변경
			        }
			  
			        else {
			          la.setText("아쉽군요");
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
