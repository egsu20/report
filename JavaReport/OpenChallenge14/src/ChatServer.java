import java.io.*; // ����½�Ʈ�� �̿��� ���� �����ϴ� ��Ű��
import java.net.*; // Socket, ServerSocket�� �̿��ϱ� ���� ��Ű��
import javax.swing.*; // ���� ������Ʈ�� ����ϱ� ���� ��Ű��
import java.awt.*; // �׷���ó�� ���� ���� ��Ű��
import java.awt.event.*; // awt �̺�Ʈó���� ���� ��Ű��

class SReceiveThread extends Thread{ // Ŭ���̾�Ʈ�κ��� �޽����� �Է¹��� ������ Ŭ�����̴�.
	private ChatServer s;

	public SReceiveThread(ChatServer s) { // ChatServer�� ��� ���(������)�� �̿��ϱ� ���� ����
		this.s = s;
	}

	public void run() {
		while(true) { // ���ѷ���
			s.receive(); // ��������� �޽����� �Է¹ޱ� ���� ����Ѵ�. 
		}
	}
}

public class ChatServer extends JFrame{
	private JTextArea ta = new JTextArea(15,41); // �ְ���� �޽����� ������ �����̴�.
	private JTextField tf = new JTextField(35); // ������ �޽����� �Է��� ������Ʈ�̴�.
	private ServerSocket listener = null; // ���������̴�.
	private Socket socket = null; // Ŭ���̾�Ʈ ���� �����̴�.
	
	private int connect() { // Ŭ���̾�Ʈ���� ������ ���� �޼ҵ�� ������ ���еǸ� 0�� �����Ͽ� ���α׷��� �����ϵ��� �Ѵ�. 
		try {
			listener = new ServerSocket(9999); // ������ ��Ʈ��ȣ�� 9999�� �����Ѵ�.
			socket = listener.accept(); // Ŭ���̾�Ʈ�� ������ ��ٸ��ٰ� ������ ��û�Ǹ� �����ϰ� Ŭ���̾�Ʈ ���� ������ ����.
			ta.append("Ŭ���̾�Ʈ�κ��� ���� �Ϸ�\n"); // ta�� ������ �Ϸ�Ǿ����� �����.
		}catch(IOException e) { 
			ta.append("Ŭ���̾�Ʈ�� ���� ����\n"); // ������ ���е��� ���
			return 0;
		}		
		setSize(520,360); // ������Ʈ�� ���� ������ ���� ���� �־ �����Ͽ���.
		return 1; // ������ ����
	}
	
	public void receive() { // Ŭ���̾�Ʈ�κ��� �޽����� �ޱ� ���� �޼ҵ��̴�.
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ������ �Է½�Ʈ���� ���ڽ�Ʈ���� ������ �� ���۽�Ʈ���� �����Ͽ� ������ ����Ŵ.
			String inputMessage = in.readLine(); // �޽����� �� �� ������ �о�´�.
			
			if(inputMessage.equalsIgnoreCase("1024_EXIT_MESSAGE_PROGRAM")) { // Ŭ���̾�Ʈ�� �����ư�� ���� ���
				ta.append("Ŭ���̾�Ʈ���� ������ �����Ͽ���"); // ta�� ���� ���Ḧ �˸���.
				
				try {
					socket.close(); // Ŭ���̾�Ʈ ���� ������ �ݴ´�.
					listener.close(); // ���� ������ �ݴ´�.
				}catch(IOException e) {
					ta.append("Ŭ���̾�Ʈ�� ä�� �� ������ �߻��߽��ϴ�.");
				}
				System.exit(0);
			}
			
			ta.append("  Ŭ���̾�Ʈ : "+inputMessage+"\n"); // �Էµ� �޽����� ta�� ����Ѵ�.
			
		}catch(IOException e) { // Ŭ���̾�Ʈ�� �����Ͽ��ų� ��Ʈ�� ���� ����
			ta.append("error\n");
			System.exit(0);
		}
	}
	
	private void send(String outputMessage) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// ������ ��½�Ʈ���� ���ڽ�Ʈ���� ������ �� ���۽�Ʈ���� �����Ͽ� ������ ����Ŵ.  
			
			ta.append("���� : "+tf.getText()+"\n"); // ta�� tf�� �ؽ�Ʈ�� �߰��Ѵ�.
	        tf.setText(""); // tf�� �� ĭ���� ����
	        
			out.write(outputMessage+"\n"); // ��½�Ʈ���� �̿��� Ŭ���̾�Ʈ�� �޽����� ������.
			out.flush(); // ���۰� �� ������ �����͸� ��Ƶ��� �ʰ� ��½�Ʈ���� �ٷ� ����϶�� ����.
		}catch(IOException e) {
			ta.append("���������\n");
			System.exit(0);
		}
	}
	
	public ChatServer() { // ������
		setTitle("���� ä�� â"); // �������� ������ �����Ѵ�.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // â�� ������ ���α׷��� ����ǵ��� �Ѵ�.
	    setSize(530,365); // â�� ũ�⸦ �����Ѵ�.		
	    setVisible(true); // �������� ���������� �Ѵ�.
		
		Container c = getContentPane();
		c.setBackground(new Color(0XE6E6FA)); // ����� ������� �����Ѵ�.
		c.setLayout(new FlowLayout()); 
		
		ta.setEditable(false); // JTextArea�� ��Ȱ��ȭ
        c.add(new JScrollPane(ta));
        c.add(tf); // ����Ʈ�ҿ� ta�� tf�� �����Ѵ�.
      
        JButton sendButton = new JButton("����");
        c.add(sendButton); // ������ ���� ��ư�� �����Ѵ�.
		
	    int con = connect(); // Ŭ���̾�Ʈ�� �����ϱ� ���� �޼ҵ带 ȣ���Ѵ�.
		if(con == 0) { // Ŭ���̾�Ʈ�� ������ ���еǸ� ���α׷��� �����Ѵ�.
			System.exit(0);
    	}
	    
		sendButton.addActionListener(new ActionListener() { // ���� ��ư�� Action �����ʸ� �����Ѵ�.
			public void actionPerformed(ActionEvent e) {
				String s = tf.getText(); // s�� ���� tf�� �������� ����
				if(!s.equals(""))  // tf�� �Է��� ������ 
					send(s); // send �޼ҵ��� ���ڷ� s�� �ѱ�.
			}
		});
		
		tf.addActionListener(new ActionListener() { // tf���� ����Ű�� �Էµ� ���
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource(); // �̺�Ʈ�� �߻��� JTextField ������Ʈ�� �˾Ƴ��� ���۷����� t�� �����Ѵ�.
	            String s = t.getText(); // t�� �ԷµǾ� �ִ� �ؽ�Ʈ�� ����.
	            if(!s.equals(""))  // tf�� �Է��� ������ 
	             send(s); // send() �޼ҵ忡 s�� �����Ѵ�
			}
		});
	}
	
	public static void main(String[] args) {
		ChatServer s = new ChatServer(); // ������ ȣ��
		
		SReceiveThread th = new SReceiveThread(s); // �޽����� ���� ������ ��ü�� �����Ѵ�.
		th.start(); // �����带 �����Ѵ�.
	}
}
