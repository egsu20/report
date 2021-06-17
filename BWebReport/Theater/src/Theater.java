import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*1961043 ������ Theater*/
class Movie{
	private String title, director, actor; // ����, ����, ���
	private int rating, price; // ����, ����
	
	public void setMovieInfo(String title, String director, String actor, int rating, int price) { // ��ȭ ���� ����
		this.title = title; // ��� ���� title�� ���� ���� title�� �����Ѵ�.
		this.director = director; // ��� ���� director�� ���� ���� director�� �����Ѵ�.
		this.actor = actor; // ��� ���� actor�� ���� ���� actor�� �����Ѵ�.
		this.rating = rating; // ��� ���� rating�� ���� ���� rating�� �����Ѵ�.
		this.price = price; // ��� ���� price�� ���� ���� price�� �����Ѵ�.
	}
	public void printMovieInfo(JTextArea show) { // ��ȭ ���� ���
		
		show.append("< " + title + " >\n"); // ���� ���
		show.append("���� : " + director + "\n"); // ���� ���
		show.append("��� : " + actor + "\n"); // ��� ���
		show.append("����(1~5) : " + rating + "\n"); // ���� ���
		show.append("���� : " + price + "\n\n"); // ���� ���
	}
}

class Person{
	private int age; // ����
	private String name, gender, address, phone; // �̸�, ����, �ּ�, �ڵ�����ȣ
	public String review=""; // ���並 ������ ����
	Movie movie; // ��ȭ ������ ������ ����

	public void setPersonInfo(String name, String gender, String address, String phone, int age) { // �� ���� ����
		this.name = name; // ��� ���� name�� ���� ���� name�� �����Ѵ�.
		this.gender = gender; // ��� ���� gender�� ���� ���� gender�� �����Ѵ�.
		this.address = address; // ��� ���� address�� ���� ���� address�� �����Ѵ�.
		this.phone = phone; // ��� ���� phone�� ���� ���� phone�� �����Ѵ�.
		this.age = age; // ��� ���� age�� ���� ���� age�� �����Ѵ�.
	}
	
	public void printPersonInput(JTextArea show) { // TextArea�� �� ���� ���
		show.append("------------- ȸ������ ------------\n");
		show.append("�̸� : " + name + "\n"); // �̸� ���
		show.append("���� : " + age + "\n"); // ���� ���
		show.append("���� : " + gender + "\n"); // ���� ���
		show.append("�ּ� : " + address + "\n"); // �ּ� ���
		show.append("��ȭ��ȣ : " + phone + "\n\n"); // ��ȭ��ȣ ���
	}
}

class PersonReview extends Person { // ���� ���� Ŭ����
	public void setReview(JTextArea show) { // ���� ����
		super.review = show.getText(); // JTexrArea�κ��� ���� ���� 
	}
	
	public void printPersonReview(JTextArea show, Movie movie) { // JTextArea�� ��ȭ����&���� ���
		this.movie = movie; // movie ��ü ����
		super.movie.printMovieInfo(show); // ��ȭ���� ��� 
		show.append(super.review); // ���� ���
	}
	
}

public class Theater extends JFrame{ // JFrame�� ��ӹ޾� ȭ�� ����
	static ArrayList<Movie> movieList; // ��ȭ ������ ������ ����Ʈ
	static Person jisu; // ȸ�� ������ ����Person ��ü ����
	static PersonReview review; // ȸ�� ���並 ������ ��ü 
	
	public Theater() {
		setTitle("Movie App"); // ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// â ���� �� ���α׷� ����
		Container c = getContentPane(); // ������Ʈ�� ������ �����̳�
		c.setLayout(new FlowLayout()); // ��ġ������ ����
		
		// ��ư ����
		JButton movie = new JButton("��ü ��ȭ"); 
		JButton person = new JButton("ȸ������"); 
		JButton rmInfo = new JButton("���� ��ȭ&����");
		JButton reviewB = new JButton("���� �ۼ�");
		
		// ������ ����ϰ� �Է¹��� JTextArea ���� 17*40 ���� �Է� ����
		JTextArea show = new JTextArea(17,40); 
		
		movie.addActionListener(new ActionListener() { // movie ��ư Ŭ��
			public void actionPerformed(ActionEvent e) {		
				show.setText("");
				
				for(int i = 0 ; i < movieList.size() ; i++) { // ��ȭ ���� ���
					Movie m = movieList.get(i); // ArrayList�� i��° ���� ����
					m.printMovieInfo(show); // �ش� ��ȭ ���� ���
				}
			}
		});
		person.addActionListener(new ActionListener() { // person ��ư Ŭ��
			public void actionPerformed(ActionEvent e) {
				show.setText("");
				jisu.printPersonInput(show); // ȸ�� ���� ���
			}
		});
		rmInfo.addActionListener(new ActionListener() { // rmInfo ��ư Ŭ��
			public void actionPerformed(ActionEvent e) {
				show.setText("");
				// ȸ���� 1�� ��ȭ(ĸƾ �Ƹ޸�ī)�� ���Ҵٰ� �����ϰ� ���� ���
				review.printPersonReview(show, movieList.get(1)); 
			}
		});
		
		reviewB.addActionListener(new ActionListener() {// reviewB ��ư Ŭ��
			public void actionPerformed(ActionEvent e) {
				if(reviewB.getText().equals("�Ϸ�")) { // reviewB ��ư�� ���ڰ� �Ϸ�
					review.setReview(show); // ������� �Է��� ����
					reviewB.setText("���� �ۼ�"); // reviewB ��ư�� ���ڸ� �������
				}
				else {
					reviewB.setText("�Ϸ�"); // reviewB ��ư�� ���ڸ� '�Ϸ�'��
					show.setText("-------- Review --------\n");
				}				
			}
		});
		
		// ����Ʈ�ҿ� ������Ʈ ����
		c.add(movie); c.add(person); c.add(rmInfo);
		c.add(reviewB);	c.add(new JScrollPane(show));
		
		setVisible(true); // ������ ���
		setSize(500,400); // ������ ũ�� ����
	}
	
	public static void main(String[] args) {
		// ������ ���� ����� ���� ArrayList
		movieList = new ArrayList<Movie>();
		
		// �� ���� Movie ��ü ����
		Movie ironman = new Movie(); 
		Movie captain = new Movie();
		Movie hulk = new Movie();
		
		// Movie ��ü ���� ����
		ironman.setMovieInfo("���̾��", "�� �к��", "�δ���", 5, 10000);
		captain.setMovieInfo("ĸƾ�Ƹ޸�ī", "�� ������", "ũ�������ݽ�", 3, 8000);
		hulk.setMovieInfo("��ũ", "�̾�", "��ũ���ȷ�", 4, 9000);

		// movieList�� ��ȭ���� �߰�
		movieList.add(ironman);
		movieList.add(captain);
		movieList.add(hulk);
		
		jisu = new Person(); // Person ��ü ����
		jisu.setPersonInfo("������", "����", "����", "010-1111-1111",22); // ȸ�� ���� �߰�
		
		review = new PersonReview(); // ���� ��ü ����
		
		new Theater(); // ������ ȣ�� - ������ ���� ����
	}
}
