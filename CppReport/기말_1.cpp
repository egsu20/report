#include <iostream>
using namespace std;

class Circle{
	int radius;
public:
	Circle(){
		radius = 1;
	}
	Circle(int r){
		radius = r;
	}
	void setRadius(int r){
		radius = r;
	}
	double getArea();
};

double Circle::getArea(){
	return 3.14 * radius * radius;
}

int main(){
	int n;  // 생성할 Circle 객체 수 
	Circle *p; //객체 배열을 동적 할당받기 위한 포인터 변수 
	
	cout<<"원하는 Circle 객체 개수를 입력하시오:";
	cin>> n;
	
	p = new Circle[n]; // Circle 배열 동적 할당 
	
	for(int i = 0 ; i < n ; i++){ // p[0]부터 p[n]까지의 radius 값을 설정 
		p[i].setRadius(i);
	} 
	
	for(int i = 0 ; i < n ; i++){
		cout<<"Circle["<<i<<"]의 면적은 "<<p[i].getArea()<<endl; // Circle클래스의 getArea()멤버 함수를 이용해 면적 출력 
	}
	
	delete [] p; // 동적으로 할당받은 배열 반환 
}
