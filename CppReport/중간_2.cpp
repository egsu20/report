#include <iostream>
using namespace std;

class Circle{
private:
	int radius;
	
public:
	Circle();
	double getArea();
	int getRadius();
	void setRadius(int r);
};
Circle::Circle(){
	radius = 1;
}
void Circle::setRadius(int r){
	radius=r;
}
int Circle::getRadius(){
	return radius;
}
double Circle::getArea(){
	double area = 3.14*radius*radius;
	return area;
}

int main(){
	int x_size, y_size, z_size; //3���� �迭�� ũ�⸦ ������ ���� 
	int i, j, k; //for���� ����� ���� 
	int n = 1; 
	double area; 
	
	cout<<"3���� �迭�� x�� ũ�⸦ �Է��Ͻÿ� : ";
	cin>>x_size;
	cout<<"3���� �迭�� y�� ũ�⸦ �Է��Ͻÿ� : ";
	cin>>y_size;
	cout<<"3���� �迭�� z�� ũ�⸦ �Է��Ͻÿ� : ";
	cin>>z_size;
	
	Circle circles[x_size][y_size][z_size];
	cout<<"======================================================"<<endl;
	
	for(i = 0 ; i < x_size ; i++){
		for(j = 0 ; j < y_size ; j++){
			for(k = 0 ; k < z_size ; k++){
				
				circles[i][j][k].setRadius(n);
				
				cout<<"circles["<<i<<"]["<<j<<"]["<<k<<"]�� �Էµ� ������ ���� ";
				cout<<circles[i][j][k].getRadius()<<endl;
				
				n++;
			}
		}
	}
	cout<<"======================================================"<<endl;
	
	for(i = 0 ; i < x_size ; i++){
		for(j = 0 ; j < y_size ; j++){
			for(k = 0 ; k < z_size ; k++){
				
				area = circles[i][j][k].getArea();
				
				cout<<"circles["<<i<<"]["<<j<<"]["<<k<<"]�� ������ ";
				cout<<area<<endl;
			}
		}
	}
}
