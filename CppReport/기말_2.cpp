#include <iostream>
using namespace std;

void swap(int *a, int *b) { // 포인터타입의 매개변수 
	int tmp; //  a와 b를 교환하는 과정에서 사용할 변수 
	
	tmp = *a; // a의 값을 tmp 변수로 저장 
	*a = *b; // b의 값을 a 변수로 저장 
	*b = tmp; // tmp(a)의 값을 b의 변수로 저장 
	
	// 주소에 의한 호출로 매개변수 a, b가 가리키는 주소의 값을 변경함. 
}

int main(){
	int m = 4, n = 5;
	swap(&m, &n); //주소를 넘김. 주소에 의한 호출 
	cout<<"m값 : "<< m <<endl;
	cout<<"n값 : "<< n <<endl;
}
