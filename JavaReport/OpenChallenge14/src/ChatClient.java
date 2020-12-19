import java.io.*; // ����� ��Ʈ�� �̿��� ���� �����ϴ� ��Ű��
import java.net.*; // Socket, ServerSocket�� �̿��ϱ� ���� ��Ű��
import javax.swing.*; // ����������Ʈ�� ����ϱ� ���� ��Ű��
import java.awt.*; // �׷��� ó�� ���� ���� ��Ű��
import java.awt.event.*; // awt �̺�Ʈ ó���� ���� ��Ű��

class CReceiveThread extends Thread{ // �����κ��� �޽����� �Է¹��� ������ Ŭ�����̴�.
	private ChatClient c;
	
	public CReceiveThread(ChatClient c) { // ChatClient�� ��� ���(���� ��)�� �̿��ϱ� ���� ����
		this.c = c;
	}
	
	public void run() {
		while(true) { // ���ѷ���
			c.receive(); // ��������� �޽����� �Է¹ޱ� ���� ����Ѵ�. 
		}
	}
}

public class ChatClient extends JFrame{
   private JTextArea ta = new JTextArea(15,41); // �ְ���� �޽����� ������ �����̴�.
   private JTextField tf = new JTextField(29); // ������ �޽����� �Է��� ������Ʈ�̴�.
   private Socket socket = null; // Ŭ���̾�Ʈ �����̴�.
   
   private int connect() { // �������� ������ ���� �޼ҵ�� ���ῡ �����ϸ� 0�� �����Ͽ� ���α׷��� �����ϵ��� �Ѵ�. 
      try {
         socket = new Socket("localhost", 9999); // Ŭ���̾�Ʈ ������ �����Ͽ� ������ �����Ѵ�. 127.0.0.1�̳� ���� IP�ּҵ� �����ϴ�.
         ta.append("���� ���� �Ϸ�\n"); // ta�� ������ �Ϸ�Ǿ����� ����Ѵ�.
      }catch(IOException e) {
         ta.append("���� ���� ����"); // �������� ������ ���������� �˸���.
         return 0;
      } 
      setSize(520,360); // ������Ʈ�� ���� ������ ���� ���� �־ �����Ͽ���.
      return 1; // ������ ����
   }
   
   public void receive() { // �����κ��� �޽����� �ޱ� ���� �޼ҵ��̴�.
      BufferedReader in = null; // ���۸� �������ִ� �Է� ��Ʈ��.
      
      try {
         in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // ������ �Է½�Ʈ���� ���ڽ�Ʈ���� ������ �� ���۽�Ʈ���� �����Ͽ� ������ ����Ŵ.  
         
         String inputMessage = in.readLine(); // �����Է½�Ʈ������ ���� �� �� �о�´�.
         ta.append("  ���� : "+inputMessage+"\n"); // �Էµ� �޽����� ta�� ����Ѵ�.
         
      }catch(IOException e) { // ������ �����Ͽ��ų� ��Ʈ�� ���� ����
         ta.append("error\n"); 
         System.exit(0);
      }
   }
   
   private void send(String outputMessage) { // �޽����� ������ ������ ���� �޼ҵ��̴�.
      try {
    	 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// ������ ��½�Ʈ���� ���ڽ�Ʈ���� ������ �� ���۽�Ʈ���� �����Ͽ� ������ ����Ŵ
      
         if(outputMessage.equalsIgnoreCase("1024_EXIT_MESSAGE_PROGRAM")) { // ���� ��ư�� Ŭ���� ���
            out.write(outputMessage+"\n"); // ��½�Ʈ���� �̿��� ������ �޽����� ������.
            out.flush(); // ���۰� �� ������ �����͸� ��Ƶ��� �ʰ� ��½�Ʈ���� �ٷ� ����϶�� ����.

            try {
               if(socket != null) socket.close(); 
            }catch(IOException e) {
               ta.append(e.getMessage());
               return;
            }
            finally {
				System.exit(0); // ���α׷��� �����Ѵ�.
			}
         }
         ta.append("Ŭ���̾�Ʈ : "+tf.getText()+"\n"); // ta�� t�� �ؽ�Ʈ�� �߰��Ѵ�.
         tf.setText(""); // tf�� �� ĭ���� ����
         out.write(outputMessage+"\n");
         out.flush();
         
      }catch(IOException e) {
         ta.append("������ ����\n");
         System.exit(0);
      }
   }
   
   public ChatClient() { 
      setTitle("Ŭ���̾�Ʈ ä��â"); // �������� ������ �����Ѵ�.
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // â�� ������ ���α׷��� ����ǵ��� �Ѵ�.
      setSize(530,365); // â�� ũ�⸦ �����Ѵ�.
      setVisible(true); // �������� ���������� �Ѵ�.
      
      Container c = getContentPane(); // ����Ʈ�� c ����
      c.setBackground(new Color(0XE6E6FA));
      c.setLayout(new FlowLayout());

      ta.setEditable(false); // JTextArea�� ��Ȱ��ȭ
      c.add(new JScrollPane(ta));
      c.add(tf);
      
      JButton sendButton = new JButton("����"); // ���۹�ư
      c.add(sendButton);
      JButton b = new JButton("����"); // �����ư
      c.add(b);
      
      int con = connect(); // ������ �����ϱ� ���� �޼ҵ��̴�.
      if(con == 0) { // ������ ������ �����ϸ� ���α׷��� �����Ѵ�.
    	  System.exit(0);
      }
      
      sendButton.addActionListener(new ActionListener() { // ���� ��ư�� Action �����ʸ� �����Ѵ�.
			public void actionPerformed(ActionEvent e) {
				String s = tf.getText(); // s�� ���� tf�� �������� ����
				if(!s.equals(""))  // tf�� �Է��� ������ 
					send(s); // send �޼ҵ��� ���ڷ� s�� �ѱ�.
			}
		});
      
      b.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  send("1024_EXIT_MESSAGE_PROGRAM"); // ä�� ���� �ڵ带 ������. ����ڰ� �Է����� ���� ���ڿ��� ����Ͽ���. 
    	  }
      });
      
      tf.addActionListener(new ActionListener() { // �޽����� �Է��� JTextField�� �׼Ǹ����ʸ� �ܴ�
         public void actionPerformed(ActionEvent e) { // �׼��̺�Ʈ�� �ԷµǸ�
            JTextField t = (JTextField)e.getSource(); // �̺�Ʈ�� �߻��� JTextField ������Ʈ�� �˾Ƴ��� ���۷����� t�� �����Ѵ�.
            String s = t.getText(); // t�� �ԷµǾ� �ִ� �ؽ�Ʈ�� ����.
            if(!s.equals(""))  // tf�� �Է��� ������ 
            	send(s); // send() �޼ҵ忡 s�� �����Ѵ�
         }
      });
      
   }
   
   public static void main(String[] args) {
      ChatClient c = new ChatClient(); // �������� ���� ���� �����ڸ� ȣ���Ѵ�.
      
      CReceiveThread th = new CReceiveThread(c);
      th.start();
   }
}