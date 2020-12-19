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
	   String calText = calField.getText(); // calField�� ���� ���� ��� �´�.
       if(calText.isEmpty()) return; // calField�� ������ ��� ������ �ܼ� �����Ѵ�. calField.getText().isEmpty()�� �����ϴ�.
       
       
       StringTokenizer st = new StringTokenizer(calText, "+-*/", true); // +, -, *, /�� ��ū�� �и��Ѵ�
       if(st.countTokens() < 3 || st.countTokens() % 2 == 0) {// ��ū�� ������ 3�� �̸��̰ų� ¦���� ��� 
      	 return; //  �ܼ� �����Ѵ�.
       } // ¦���� ���� ������� �߸��� ����̴�.
       
      
       Vector<Integer> numVector = new Vector<Integer>(); // �ǿ����ڸ� ������ ����
       Vector<String> opVector = new Vector<String>(); // �����ڸ� ������ ����
       String calStr="";
        
       try { // ���꿡�� �߻��ϴ� ��� ���ܸ� ó���Ѵ�. ���� ��� ���ӵ� �����ڷ� ���� ���ܰ� �ִ�.
	         while(st.hasMoreTokens()) {
	        	 calStr = st.nextToken();
	        	 if(calStr.equals("+")||calStr.equals("-")||calStr.equals("*")||calStr.equals("/")) // ��ū�� �������� ���
	        		 opVector.add(calStr); // ������ Vector�� �߰��Ѵ�.
	        	 else numVector.add(Integer.parseInt(calStr)); // ������ ��� �ǿ����� Vector�� �߰��Ѵ�.
	         }
	         
	         int result = 0;
	        
	         for(int i = 0 ; i < opVector.size(); i++) {
	        	 String op = opVector.get(i);
	        	 
	        	 switch(op) {
	        	 case "*":
	        		 result = numVector.get(i) * numVector.get(i+1);
	        		 numVector.set(i, result); 
	        		 numVector.set(i+1, result);
	        		 /* i��° ���� i+1��° ���� ��� �������ش�.
	        		  ���꿡 ������ �������� �ִٸ� i�� ���� ó���� ������ �ʾƵ� ������ 
	        		  ������ ������ �ִ� ��쿣 �ʿ��ϴ�.*/
	        		 break;
	        	 case "/":
	        		 result = numVector.get(i) / numVector.get(i+1);
	        		 numVector.set(i, result);
	        		 numVector.set(i+1, result);
	        		 break;
	        	 }
	         } // ���� ����� ������ ������ ��� ������ ���� -> ������ ���� ���길 ����.
	         
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
      Button btn = (Button)event.getSource(); // �̺�Ʈ�� �߻��� ��ư�� ����.
      String btnText = btn.getText();
      
      if(btnText.equals("CE")) { // ����ڰ� CE ��ư�� Ŭ���� ���
         calField.clear(); // calFiel�� ������ ��� �����
         resultField.setText(""); // resultField�� ������ ��ĭ���� �����Ѵ�
      }
      else if(btnText.equals("���")) { // ����ڰ� ��� ��ư�� Ŭ���� ���
         calcu(); // calcu()�� ȣ���Ͽ� ������ �����Ѵ�.
      }
      else { // ���ڹ�ư�� Ŭ���� ���
         calField.appendText(btnText); // �ؽ�Ʈ�� �̾� ���δ�. calField.setText(calField.getText()+btnText);�� ��� ����Ͽ��� �ȴ�
      }
   }
   public void tfAction(ActionEvent e) {
	   calcu(); // ����Ű�� �Էµ� ��� calcu()�� ȣ���Ͽ� ������ �����Ѵ�.
   }
}