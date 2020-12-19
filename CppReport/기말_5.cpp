#include <iostream>
using namespace std;

class Circle{
	int radius;
public:
	Circle();
	Circle(int r);
	~Circle();
	void setRadius(int r){
		radius = r;
	}
	double getArea(){
		return 3.14 * radius * radius;
	}
}; 

Circle::Circle(){
	radius = 1;
}

Circle::Circle(int r){ //매개변수 있는 생성자. radius를 r로 설정 
	radius = r;
}

Circle::~Circle(){ //객체 소멸시 실행되는 소멸자 
	cout<< "소멸자 실행 radius = " << radius <<endl;	
}

int main(){
	int n, radius; //생성할 원의 개수를 저장할 변수 n과 입력받을 반지름 값을 저장할 radius 변수 
	
	while(true){
		cout << endl << "생성하고자 하는 원의 개수를 입력하세요(0입력시 종료):";
		cin >> n;
		
		if(n == 0){ // 사용자가 0을 입력하면 반복문을 빠져나와 프로그램을 종료함. 
			cout<< "프로그램을 종료합니다." << endl;
			break;
		} 
		
		Circle *p = new Circle[n]; //n개의 Circle 배열 동적 할당 
		 
		for(int i = 0 ; i < n ; i++){
			cout<< "원[" << i << "]의 반지름을 입력하세요: ";
			cin>> radius;
			p[i].setRadius(radius); //Circle 클래스의 setRadius(int r) 함수를 이용해 객체의 radius 값을 설정 
		}
		
		for(int i = 0 ; i < n ; i++){
			cout<< "원[" << i << "]의 넓이: ";
			cout<<p[i].getArea()<<endl; // Circle의 getArea() 함수를 이용해 객체의 넓이 출력.  
		}
		
		delete [] p; // 동적으로 할당받은 메모리를 반환. 이 문장을 포함하여야 소멸자가 실행된다. 
	}
}
