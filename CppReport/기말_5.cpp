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

Circle::Circle(int r){ //�Ű����� �ִ� ������. radius�� r�� ���� 
	radius = r;
}

Circle::~Circle(){ //��ü �Ҹ�� ����Ǵ� �Ҹ��� 
	cout<< "�Ҹ��� ���� radius = " << radius <<endl;	
}

int main(){
	int n, radius; //������ ���� ������ ������ ���� n�� �Է¹��� ������ ���� ������ radius ���� 
	
	while(true){
		cout << endl << "�����ϰ��� �ϴ� ���� ������ �Է��ϼ���(0�Է½� ����):";
		cin >> n;
		
		if(n == 0){ // ����ڰ� 0�� �Է��ϸ� �ݺ����� �������� ���α׷��� ������. 
			cout<< "���α׷��� �����մϴ�." << endl;
			break;
		} 
		
		Circle *p = new Circle[n]; //n���� Circle �迭 ���� �Ҵ� 
		 
		for(int i = 0 ; i < n ; i++){
			cout<< "��[" << i << "]�� �������� �Է��ϼ���: ";
			cin>> radius;
			p[i].setRadius(radius); //Circle Ŭ������ setRadius(int r) �Լ��� �̿��� ��ü�� radius ���� ���� 
		}
		
		for(int i = 0 ; i < n ; i++){
			cout<< "��[" << i << "]�� ����: ";
			cout<<p[i].getArea()<<endl; // Circle�� getArea() �Լ��� �̿��� ��ü�� ���� ���.  
		}
		
		delete [] p; // �������� �Ҵ���� �޸𸮸� ��ȯ. �� ������ �����Ͽ��� �Ҹ��ڰ� ����ȴ�. 
	}
}
