#include <iostream>
using namespace std;

void swap(int *a, int *b) { // ������Ÿ���� �Ű����� 
	int tmp; //  a�� b�� ��ȯ�ϴ� �������� ����� ���� 
	
	tmp = *a; // a�� ���� tmp ������ ���� 
	*a = *b; // b�� ���� a ������ ���� 
	*b = tmp; // tmp(a)�� ���� b�� ������ ���� 
	
	// �ּҿ� ���� ȣ��� �Ű����� a, b�� ����Ű�� �ּ��� ���� ������. 
}

int main(){
	int m = 4, n = 5;
	swap(&m, &n); //�ּҸ� �ѱ�. �ּҿ� ���� ȣ�� 
	cout<<"m�� : "<< m <<endl;
	cout<<"n�� : "<< n <<endl;
}
