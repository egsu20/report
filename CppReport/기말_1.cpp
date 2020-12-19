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
	int n;  // ������ Circle ��ü �� 
	Circle *p; //��ü �迭�� ���� �Ҵ�ޱ� ���� ������ ���� 
	
	cout<<"���ϴ� Circle ��ü ������ �Է��Ͻÿ�:";
	cin>> n;
	
	p = new Circle[n]; // Circle �迭 ���� �Ҵ� 
	
	for(int i = 0 ; i < n ; i++){ // p[0]���� p[n]������ radius ���� ���� 
		p[i].setRadius(i);
	} 
	
	for(int i = 0 ; i < n ; i++){
		cout<<"Circle["<<i<<"]�� ������ "<<p[i].getArea()<<endl; // CircleŬ������ getArea()��� �Լ��� �̿��� ���� ��� 
	}
	
	delete [] p; // �������� �Ҵ���� �迭 ��ȯ 
}
