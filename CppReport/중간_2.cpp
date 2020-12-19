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
	int x_size, y_size, z_size; //3차원 배열의 크기를 지정할 변수 
	int i, j, k; //for문에 사용할 변수 
	int n = 1; 
	double area; 
	
	cout<<"3차원 배열의 x의 크기를 입력하시오 : ";
	cin>>x_size;
	cout<<"3차원 배열의 y의 크기를 입력하시오 : ";
	cin>>y_size;
	cout<<"3차원 배열의 z의 크기를 입력하시오 : ";
	cin>>z_size;
	
	Circle circles[x_size][y_size][z_size];
	cout<<"======================================================"<<endl;
	
	for(i = 0 ; i < x_size ; i++){
		for(j = 0 ; j < y_size ; j++){
			for(k = 0 ; k < z_size ; k++){
				
				circles[i][j][k].setRadius(n);
				
				cout<<"circles["<<i<<"]["<<j<<"]["<<k<<"]에 입력된 반지름 값은 ";
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
				
				cout<<"circles["<<i<<"]["<<j<<"]["<<k<<"]의 면적은 ";
				cout<<area<<endl;
			}
		}
	}
}
