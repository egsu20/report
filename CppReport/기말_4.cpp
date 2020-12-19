#include <iostream>
#include <cstring>
using namespace std;

class Rectangle{
	
public:
	int width, height;
	Rectangle(int w, int h);
	bool isSquare();
};

Rectangle::Rectangle(int w, int h){ //�Ű����� �ִ� ������ 
	width = w; 
	height = h;
}

bool Rectangle::isSquare(){
	if(width == height){
		return true;
	}	//width�� height ���� ������ true, �ٸ��� false�� ����. �� ���� ���ٸ� ���簢��. 
	else{
		return false;
	}
}

int main(){
	int w, h;

	while(true){ //���� ���� 
		cout << "���ο� ���� ���� ��� 0�� �Է��ϸ� ���α׷��� �����մϴ�."<<endl;

		cout<< "���� ���̸� �Է��ϼ���:";
		cin>> w;
		cout<< "���� ���̸� �Է��ϼ���:";
		cin>> h;
		
		if(w == 0 && h == 0){ //���ο� ���θ� ��� 0 �� �Է��ϸ� ���� ������ �������´�. 
			cout<< "���ο� ���α��� ��� 0�� �Է��Ͽ� ���α׷��� �����մϴ�."<<endl;
			break;
		}
		
		Rectangle rect(w, h); // �Ű����� �ִ� ������ Rectangle(int w, int h) ȣ��. 
		// �� ����(43��)�� Rectangle Ŭ������ �⺻������ Rectangle()�� �����Ƿ� Rectangle rect; ���·� �Ұ���.  
		
		if(rect.isSquare()) { // rect.isSquare()�� ���� true�� ��� ���� 
			cout<<"���� " << w << "cm, ���� " << h << "cm�� ���簢���̴�." << endl << endl;
		}
		else{ //rect.isSquare()�� ���� false�� ��� ����  
			cout<<"���� " << w << "cm, ���� " << h << "cm�� ���簢���� �ƴϴ�." << endl << endl;
		}
	}
}
