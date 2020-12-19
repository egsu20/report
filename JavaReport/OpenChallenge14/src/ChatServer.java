import java.io.*; // 입출력스트림 이용을 위해 포함하는 패키지
import java.net.*; // Socket, ServerSocket을 이용하기 위한 패키지
import javax.swing.*; // 스윙 컴포넌트를 사용하기 위한 패키지
import java.awt.*; // 그래픽처리 등을 위한 패키지
import java.awt.event.*; // awt 이벤트처리를 위한 패키지

class SReceiveThread extends Thread{ // 클라이언트로부터 메시지를 입력받을 스레드 클래스이다.
	private ChatServer s;

	public SReceiveThread(ChatServer s) { // ChatServer의 모든 기능(변수등)을 이용하기 위한 설정
		this.s = s;
	}

	public void run() {
		while(true) { // 무한루프
			s.receive(); // 계속적으로 메시지를 입력받기 위해 대기한다. 
		}
	}
}

public class ChatServer extends JFrame{
	private JTextArea ta = new JTextArea(15,41); // 주고받은 메시지가 보여질 공간이다.
	private JTextField tf = new JTextField(35); // 전송할 메시지를 입력할 컴포넌트이다.
	private ServerSocket listener = null; // 서버소켓이다.
	private Socket socket = null; // 클라이언트 전용 소켓이다.
	
	private int connect() { // 클라이언트와의 연결을 위한 메소드로 연결이 실패되면 0을 리턴하여 프로그램을 종료하도록 한다. 
		try {
			listener = new ServerSocket(9999); // 서버의 포트번호를 9999로 설정한다.
			socket = listener.accept(); // 클라이언트의 접속을 기다리다가 접속이 요청되면 수락하고 클라이언트 전용 소켓을 만듦.
			ta.append("클라이언트로부터 연결 완료\n"); // ta에 연결이 완료되었음을 출력함.
		}catch(IOException e) { 
			ta.append("클라이언트와 연결 실패\n"); // 연결이 실패됐을 경우
			return 0;
		}		
		setSize(520,360); // 컴포넌트가 종종 보이지 않을 때가 있어서 설정하였다.
		return 1; // 정상적 연결
	}
	
	public void receive() { // 클라이언트로부터 메시지를 받기 위한 메소드이다.
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 소켓의 입력스트림을 문자스트림에 연결한 후 버퍼스트림에 연결하여 성능을 향상시킴.
			String inputMessage = in.readLine(); // 메시지를 한 줄 단위로 읽어온다.
			
			if(inputMessage.equalsIgnoreCase("1024_EXIT_MESSAGE_PROGRAM")) { // 클라이언트가 종료버튼을 누른 경우
				ta.append("클라이언트에서 연결을 종료하였음"); // ta를 통해 종료를 알린다.
				
				try {
					socket.close(); // 클라이언트 전용 소켓을 닫는다.
					listener.close(); // 서버 소켓을 닫는다.
				}catch(IOException e) {
					ta.append("클라이언트와 채팅 중 오류가 발생했습니다.");
				}
				System.exit(0);
			}
			
			ta.append("  클라이언트 : "+inputMessage+"\n"); // 입력된 메시지를 ta에 출력한다.
			
		}catch(IOException e) { // 클라이언트가 종료하였거나 스트림 생성 실패
			ta.append("error\n");
			System.exit(0);
		}
	}
	
	private void send(String outputMessage) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// 소켓의 출력스트림을 문자스트림에 연결한 후 버퍼스트림에 연결하여 성능을 향상시킴.  
			
			ta.append("서버 : "+tf.getText()+"\n"); // ta에 tf의 텍스트를 추가한다.
	        tf.setText(""); // tf를 빈 칸으로 설정
	        
			out.write(outputMessage+"\n"); // 출력스트림을 이용해 클라이언트에 메시지를 보낸다.
			out.flush(); // 버퍼가 찰 때까지 데이터를 모아두지 않고 출력스트림에 바로 출력하라고 지시.
		}catch(IOException e) {
			ta.append("보내기오류\n");
			System.exit(0);
		}
	}
	
	public ChatServer() { // 생성자
		setTitle("서버 채팅 창"); // 프레임의 제목을 설정한다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창을 닫으면 프로그램이 종료되도록 한다.
	    setSize(530,365); // 창의 크기를 설정한다.		
	    setVisible(true); // 프레임이 보여지도록 한다.
		
		Container c = getContentPane();
		c.setBackground(new Color(0XE6E6FA)); // 배경을 연보라로 설정한다.
		c.setLayout(new FlowLayout()); 
		
		ta.setEditable(false); // JTextArea를 비활성화
        c.add(new JScrollPane(ta));
        c.add(tf); // 컨텐트팬에 ta와 tf를 부착한다.
      
        JButton sendButton = new JButton("전송");
        c.add(sendButton); // 전송을 위한 버튼을 부착한다.
		
	    int con = connect(); // 클라이언트와 연결하기 위한 메소드를 호출한다.
		if(con == 0) { // 클라이언트와 연결이 실패되면 프로그램을 종료한다.
			System.exit(0);
    	}
	    
		sendButton.addActionListener(new ActionListener() { // 전송 버튼에 Action 리스너를 설정한다.
			public void actionPerformed(ActionEvent e) {
				String s = tf.getText(); // s를 현재 tf의 내용으로 설정
				if(!s.equals(""))  // tf에 입력이 있으면 
					send(s); // send 메소드의 인자로 s를 넘김.
			}
		});
		
		tf.addActionListener(new ActionListener() { // tf에서 엔터키가 입력된 경우
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource(); // 이벤트가 발생한 JTextField 컴포넌트를 알아내고 레퍼런스를 t로 설정한다.
	            String s = t.getText(); // t에 입력되어 있는 텍스트를 얻어낸다.
	            if(!s.equals(""))  // tf에 입력이 있으면 
	             send(s); // send() 메소드에 s를 전달한다
			}
		});
	}
	
	public static void main(String[] args) {
		ChatServer s = new ChatServer(); // 생성자 호출
		
		SReceiveThread th = new SReceiveThread(s); // 메시지를 받을 스레드 객체를 생성한다.
		th.start(); // 스레드를 실행한다.
	}
}
