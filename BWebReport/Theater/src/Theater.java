import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*1961043 이지수 Theater*/
class Movie{
	private String title, director, actor; // 제목, 감독, 배우
	private int rating, price; // 평점, 가격
	
	public void setMovieInfo(String title, String director, String actor, int rating, int price) { // 영화 정보 설정
		this.title = title; // 멤버 변수 title을 지역 변수 title로 설정한다.
		this.director = director; // 멤버 변수 director을 지역 변수 director로 설정한다.
		this.actor = actor; // 멤버 변수 actor을 지역 변수 actor로 설정한다.
		this.rating = rating; // 멤버 변수 rating을 지역 변수 rating로 설정한다.
		this.price = price; // 멤버 변수 price을 지역 변수 price로 설정한다.
	}
	public void printMovieInfo(JTextArea show) { // 영화 정보 출력
		
		show.append("< " + title + " >\n"); // 제목 출력
		show.append("감독 : " + director + "\n"); // 감독 출력
		show.append("배우 : " + actor + "\n"); // 배우 출력
		show.append("평점(1~5) : " + rating + "\n"); // 평점 출력
		show.append("가격 : " + price + "\n\n"); // 가격 출력
	}
}

class Person{
	private int age; // 나이
	private String name, gender, address, phone; // 이름, 성별, 주소, 핸드폰번호
	public String review=""; // 리뷰를 저장할 변수
	Movie movie; // 영화 정보를 저장할 변수

	public void setPersonInfo(String name, String gender, String address, String phone, int age) { // 고객 정보 설정
		this.name = name; // 멤버 변수 name을 지역 변수 name로 설정한다.
		this.gender = gender; // 멤버 변수 gender을 지역 변수 gender로 설정한다.
		this.address = address; // 멤버 변수 address을 지역 변수 address로 설정한다.
		this.phone = phone; // 멤버 변수 phone을 지역 변수 phone로 설정한다.
		this.age = age; // 멤버 변수 age을 지역 변수 age로 설정한다.
	}
	
	public void printPersonInput(JTextArea show) { // TextArea에 고객 정보 출력
		show.append("------------- 회원정보 ------------\n");
		show.append("이름 : " + name + "\n"); // 이름 출력
		show.append("나이 : " + age + "\n"); // 나이 출력
		show.append("성별 : " + gender + "\n"); // 성별 출력
		show.append("주소 : " + address + "\n"); // 주소 출력
		show.append("전화번호 : " + phone + "\n\n"); // 전화번호 출력
	}
}

class PersonReview extends Person { // 고객의 리뷰 클래스
	public void setReview(JTextArea show) { // 리뷰 저장
		super.review = show.getText(); // JTexrArea로부터 리뷰 얻어옴 
	}
	
	public void printPersonReview(JTextArea show, Movie movie) { // JTextArea에 영화정보&리뷰 출력
		this.movie = movie; // movie 객체 설정
		super.movie.printMovieInfo(show); // 영화정보 출력 
		show.append(super.review); // 리뷰 출력
	}
	
}

public class Theater extends JFrame{ // JFrame을 상속받아 화면 구성
	static ArrayList<Movie> movieList; // 영화 정보를 저장할 리스트
	static Person jisu; // 회원 정보를 담을Person 객체 생성
	static PersonReview review; // 회원 리뷰를 저장할 객체 
	
	public Theater() {
		setTitle("Movie App"); // 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// 창 닫힘 시 프로그램 종료
		Container c = getContentPane(); // 컴포넌트를 부착할 컨테이너
		c.setLayout(new FlowLayout()); // 배치관리자 설정
		
		// 버튼 생성
		JButton movie = new JButton("전체 영화"); 
		JButton person = new JButton("회원정보"); 
		JButton rmInfo = new JButton("관람 영화&리뷰");
		JButton reviewB = new JButton("리뷰 작성");
		
		// 정보를 출력하고 입력받을 JTextArea 선언 17*40 글자 입력 가능
		JTextArea show = new JTextArea(17,40); 
		
		movie.addActionListener(new ActionListener() { // movie 버튼 클릭
			public void actionPerformed(ActionEvent e) {		
				show.setText("");
				
				for(int i = 0 ; i < movieList.size() ; i++) { // 영화 정보 출력
					Movie m = movieList.get(i); // ArrayList의 i번째 정보 얻어옴
					m.printMovieInfo(show); // 해당 영화 정보 출력
				}
			}
		});
		person.addActionListener(new ActionListener() { // person 버튼 클릭
			public void actionPerformed(ActionEvent e) {
				show.setText("");
				jisu.printPersonInput(show); // 회원 정보 출력
			}
		});
		rmInfo.addActionListener(new ActionListener() { // rmInfo 버튼 클릭
			public void actionPerformed(ActionEvent e) {
				show.setText("");
				// 회원이 1번 영화(캡틴 아메리카)를 보았다고 가정하고 정보 출력
				review.printPersonReview(show, movieList.get(1)); 
			}
		});
		
		reviewB.addActionListener(new ActionListener() {// reviewB 버튼 클릭
			public void actionPerformed(ActionEvent e) {
				if(reviewB.getText().equals("완료")) { // reviewB 버튼의 글자가 완료
					review.setReview(show); // 사용자의 입력을 저장
					reviewB.setText("리뷰 작성"); // reviewB 버튼의 글자를 원래대로
				}
				else {
					reviewB.setText("완료"); // reviewB 버튼의 글자를 '완료'로
					show.setText("-------- Review --------\n");
				}				
			}
		});
		
		// 컨텐트팬에 컴포넌트 부착
		c.add(movie); c.add(person); c.add(rmInfo);
		c.add(reviewB);	c.add(new JScrollPane(show));
		
		setVisible(true); // 프레임 출력
		setSize(500,400); // 프레임 크기 설정
	}
	
	public static void main(String[] args) {
		// 용이한 정보 출력을 위한 ArrayList
		movieList = new ArrayList<Movie>();
		
		// 세 개의 Movie 객체 생성
		Movie ironman = new Movie(); 
		Movie captain = new Movie();
		Movie hulk = new Movie();
		
		// Movie 객체 정보 수정
		ironman.setMovieInfo("아이언맨", "존 패브로", "로다주", 5, 10000);
		captain.setMovieInfo("캡틴아메리카", "조 존스턴", "크리스에반스", 3, 8000);
		hulk.setMovieInfo("헐크", "이안", "마크러팔로", 4, 9000);

		// movieList에 영화정보 추가
		movieList.add(ironman);
		movieList.add(captain);
		movieList.add(hulk);
		
		jisu = new Person(); // Person 객체 생성
		jisu.setPersonInfo("이지수", "여성", "대전", "010-1111-1111",22); // 회원 정보 추가
		
		review = new PersonReview(); // 리뷰 객체 생성
		
		new Theater(); // 생성자 호출 - 프레임 구성 시작
	}
}
