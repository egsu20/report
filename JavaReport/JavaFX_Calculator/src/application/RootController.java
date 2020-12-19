package application;

import java.util.StringTokenizer;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RootController {
   @FXML TextField calField, resultField;
   
   public void calcu() {
	   String calText = calField.getText(); // calField로 부터 값을 얻어 온다.
       if(calText.isEmpty()) return; // calField의 내용이 비어 있으면 단순 리턴한다. calField.getText().isEmpty()와 동일하다.
       
       
       StringTokenizer st = new StringTokenizer(calText, "+-*/", true); // +, -, *, /로 토큰을 분리한다
       if(st.countTokens() < 3 || st.countTokens() % 2 == 0) {// 토큰의 개수가 3개 미만이거나 짝수인 경우 
      	 return; //  단순 리턴한다.
       } // 짝수인 경우는 연산식이 잘못된 경우이다.
       
      
       Vector<Integer> numVector = new Vector<Integer>(); // 피연산자를 저장할 벡터
       Vector<String> opVector = new Vector<String>(); // 연산자를 저장할 벡터
       String calStr="";
        
       try { // 연산에서 발생하는 모든 예외를 처리한다. 예를 들어 연속된 연산자로 인한 예외가 있다.
	         while(st.hasMoreTokens()) {
	        	 calStr = st.nextToken();
	        	 if(calStr.equals("+")||calStr.equals("-")||calStr.equals("*")||calStr.equals("/")) // 토큰이 연산자일 경우
	        		 opVector.add(calStr); // 연산자 Vector에 추가한다.
	        	 else numVector.add(Integer.parseInt(calStr)); // 숫자일 경우 피연산자 Vector에 추가한다.
	         }
	         
	         int result = 0;
	        
	         for(int i = 0 ; i < opVector.size(); i++) {
	        	 String op = opVector.get(i);
	        	 
	        	 switch(op) {
	        	 case "*":
	        		 result = numVector.get(i) * numVector.get(i+1);
	        		 numVector.set(i, result); 
	        		 numVector.set(i+1, result);
	        		 /* i번째 값과 i+1번째 값을 모두 변경해준다.
	        		  연산에 곱셈과 나눗셈만 있다면 i에 대한 처리를 해주지 않아도 되지만 
	        		  덧셈과 뺄셈이 있는 경우엔 필요하다.*/
	        		 break;
	        	 case "/":
	        		 result = numVector.get(i) / numVector.get(i+1);
	        		 numVector.set(i, result);
	        		 numVector.set(i+1, result);
	        		 break;
	        	 }
	         } // 곱셈 연산과 나머지 연산을 모두 수행한 상태 -> 덧셈과 뺄셈 연산만 남음.
	         
	         for(int i = 0 ; i < opVector.size(); i++) {
	        	 String op = opVector.get(i);
	        	 
	        	 switch(op) {
	        	 case "+":
	        		 result = numVector.get(i) + numVector.get(i+1);
	        		 numVector.set(i+1, result);
	        		 break;
	        	 case "-":
	        		 result = numVector.get(i) - numVector.get(i+1);
	        		 numVector.set(i+1, result);
	        		 break;
	        	 }
	         }
	         resultField.setText(Integer.toString(result));
       }catch(Exception e) {
      	 resultField.setText("input error");
       }
   }
   
   public void btnClick(ActionEvent event) {
      Button btn = (Button)event.getSource(); // 이벤트가 발생한 버튼을 얻어낸다.
      String btnText = btn.getText();
      
      if(btnText.equals("CE")) { // 사용자가 CE 버튼을 클릭한 경우
         calField.clear(); // calFiel의 내용을 모두 지운다
         resultField.setText(""); // resultField의 내용을 빈칸으로 설정한다
      }
      else if(btnText.equals("계산")) { // 사용자가 계산 버튼을 클릭한 경우
         calcu(); // calcu()를 호출하여 연산을 수행한다.
      }
      else { // 숫자버튼을 클릭한 경우
         calField.appendText(btnText); // 텍스트를 이어 붙인다. calField.setText(calField.getText()+btnText);를 대신 사용하여도 된다
      }
   }
   public void tfAction(ActionEvent e) {
	   calcu(); // 엔터키가 입력된 경우 calcu()를 호출하여 연산을 수행한다.
   }
}