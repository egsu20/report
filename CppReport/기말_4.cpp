#include <iostream>
#include <cstring>
using namespace std;

class Rectangle{
	
public:
	int width, height;
	Rectangle(int w, int h);
	bool isSquare();
};

Rectangle::Rectangle(int w, int h){ //매개변수 있는 생성자 
	width = w; 
	height = h;
}

bool Rectangle::isSquare(){
	if(width == height){
		return true;
	}	//width와 height 값이 같으면 true, 다르면 false를 리턴. 두 값이 같다면 정사각형. 
	else{
		return false;
	}
}

int main(){
	int w, h;

	while(true){ //무한 루프 
		cout << "가로와 세로 길이 모두 0을 입력하면 프로그램을 종료합니다."<<endl;

		cout<< "가로 길이를 입력하세요:";
		cin>> w;
		cout<< "세로 길이를 입력하세요:";
		cin>> h;
		
		if(w == 0 && h == 0){ //가로와 세로를 모두 0 을 입력하면 무한 루프를 빠져나온다. 
			cout<< "가로와 세로길이 모두 0을 입력하여 프로그램을 종료합니다."<<endl;
			break;
		}
		
		Rectangle rect(w, h); // 매개변수 있는 생성자 Rectangle(int w, int h) 호출. 
		// 위 문장(43줄)은 Rectangle 클래스의 기본생성자 Rectangle()이 없으므로 Rectangle rect; 형태로 불가능.  
		
		if(rect.isSquare()) { // rect.isSquare()의 값이 true일 경우 실행 
			cout<<"가로 " << w << "cm, 세로 " << h << "cm는 정사각형이다." << endl << endl;
		}
		else{ //rect.isSquare()의 값이 false일 경우 실행  
			cout<<"가로 " << w << "cm, 세로 " << h << "cm는 정사각형이 아니다." << endl << endl;
		}
	}
}
