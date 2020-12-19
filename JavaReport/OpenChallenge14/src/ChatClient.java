import java.io.*; // 입출력 스트림 이용을 위해 포함하는 패키지
import java.net.*; // Socket, ServerSocket을 이용하기 위한 패키지
import javax.swing.*; // 스윙컴포넌트를 사용하기 위한 패키지
import java.awt.*; // 그래픽 처리 등을 위한 패키지
import java.awt.event.*; // awt 이벤트 처리를 위한 패키지

class CReceiveThread extends Thread{ // 서버로부터 메시지를 입력받을 스레드 클래스이다.
	private ChatClient c;
	
	public CReceiveThread(ChatClient c) { // ChatClient의 모든 기능(변수 등)을 이용하기 위한 설정
		this.c = c;
	}
	
	public void run() {
		while(true) { // 무한루프
			c.receive(); // 계속적으로 메시지를 입력받기 위해 대기한다. 
		}
	}
}

public class ChatClient extends JFrame{
   private JTextArea ta = new JTextArea(15,41); // 주고받은 메시지가 보여질 공간이다.
   private JTextField tf = new JTextField(29); // 전송할 메시지를 입력할 컴포넌트이다.
   private Socket socket = null; // 클라이언트 소켓이다.
   
   private int connect() { // 서버와의 연결을 위한 메소드로 연결에 실패하면 0을 리턴하여 프로그램을 종료하도록 한다. 
      try {
         socket = new Socket("localhost", 9999); // 클라이언트 소켓을 생성하여 서버에 연결한다. 127.0.0.1이나 실제 IP주소도 가능하다.
         ta.append("서버 연결 완료\n"); // ta에 연결이 완료되었음을 출력한다.
      }catch(IOException e) {
         ta.append("서버 연결 실패"); // 서버와의 연결이 실패했음을 알린다.
         return 0;
      } 
      setSize(520,360); // 컴포넌트가 종종 보이지 않을 때가 있어서 설정하였다.
      return 1; // 정상적 연결
   }
   
   public void receive() { // 서버로부터 메시지를 받기 위한 메소드이다.
      BufferedReader in = null; // 버퍼를 제공해주는 입력 스트림.
      
      try {
         in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 소켓의 입력스트림을 문자스트림에 연결한 후 버퍼스트림에 연결하여 성능을 향상시킴.  
         
         String inputMessage = in.readLine(); // 소켓입력스트림으로 부터 한 줄 읽어온다.
         ta.append("  서버 : "+inputMessage+"\n"); // 입력된 메시지를 ta에 출력한다.
         
      }catch(IOException e) { // 서버가 종료하였거나 스트림 생성 실패
         ta.append("error\n"); 
         System.exit(0);
      }
   }
   
   private void send(String outputMessage) { // 메시지를 서버로 보내기 위한 메소드이다.
      try {
    	 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// 소켓의 출력스트림을 문자스트림에 연결한 후 버퍼스트림에 연결하여 성능을 향상시킴
      
         if(outputMessage.equalsIgnoreCase("1024_EXIT_MESSAGE_PROGRAM")) { // 종료 버튼이 클릭된 경우
            out.write(outputMessage+"\n"); // 출력스트림을 이용해 서버에 메시지를 보낸다.
            out.flush(); // 버퍼가 찰 때까지 데이터를 모아두지 않고 출력스트림에 바로 출력하라고 지시.

            try {
               if(socket != null) socket.close(); 
            }catch(IOException e) {
               ta.append(e.getMessage());
               return;
            }
            finally {
				System.exit(0); // 프로그램을 종료한다.
			}
         }
         ta.append("클라이언트 : "+tf.getText()+"\n"); // ta에 t의 텍스트를 추가한다.
         tf.setText(""); // tf를 빈 칸으로 설정
         out.write(outputMessage+"\n");
         out.flush();
         
      }catch(IOException e) {
         ta.append("보내기 오류\n");
         System.exit(0);
      }
   }
   
   public ChatClient() { 
      setTitle("클라이언트 채팅창"); // 프레임의 제목을 설정한다.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램이 종료되도록 한다.
      setSize(530,365); // 창의 크기를 설정한다.
      setVisible(true); // 프레임이 보여지도록 한다.
      
      Container c = getContentPane(); // 컨텐트팬 c 생성
      c.setBackground(new Color(0XE6E6FA));
      c.setLayout(new FlowLayout());

      ta.setEditable(false); // JTextArea를 비활성화
      c.add(new JScrollPane(ta));
      c.add(tf);
      
      JButton sendButton = new JButton("전송"); // 전송버튼
      c.add(sendButton);
      JButton b = new JButton("종료"); // 종료버튼
      c.add(b);
      
      int con = connect(); // 서버와 연결하기 위한 메소드이다.
      if(con == 0) { // 서버와 연결을 실패하면 프로그램을 종료한다.
    	  System.exit(0);
      }
      
      sendButton.addActionListener(new ActionListener() { // 전송 버튼에 Action 리스너를 설정한다.
			public void actionPerformed(ActionEvent e) {
				String s = tf.getText(); // s를 현재 tf의 내용으로 설정
				if(!s.equals(""))  // tf에 입력이 있으면 
					send(s); // send 메소드의 인자로 s를 넘김.
			}
		});
      
      b.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  send("1024_EXIT_MESSAGE_PROGRAM"); // 채팅 종료 코드를 보낸다. 사용자가 입력하지 않을 문자열을 사용하였다. 
    	  }
      });
      
      tf.addActionListener(new ActionListener() { // 메시지를 입력할 JTextField에 액션리스너를 단다
         public void actionPerformed(ActionEvent e) { // 액션이벤트가 입력되면
            JTextField t = (JTextField)e.getSource(); // 이벤트가 발생한 JTextField 컴포넌트를 알아내고 레퍼런스를 t로 설정한다.
            String s = t.getText(); // t에 입력되어 있는 텍스트를 얻어낸다.
            if(!s.equals(""))  // tf에 입력이 있으면 
            	send(s); // send() 메소드에 s를 전달한다
         }
      });
      
   }
   
   public static void main(String[] args) {
      ChatClient c = new ChatClient(); // 프레임을 띄우기 위해 생성자를 호출한다.
      
      CReceiveThread th = new CReceiveThread(c);
      th.start();
   }
}