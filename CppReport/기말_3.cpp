#include <iostream>
#include <cstring>
using namespace std;

char& change(char s[], int index){
	return s[index];
} //��������. s[index]�� ������ �����Ѵ�. 

int main(){
	char name[] = "Mike";
	cout << "ù��°:" << name << endl; //name�� �� Mike ���  
	
	change(name, 1) = 'S'; //name[1]�� ���� 'S'�� �ٲٰ��� ��. change �Լ��� ������ �����ϹǷ� l-value�� �� �� �ִ�. 
	cout<< "�ι�°:" << name << endl; //"MSke" ��� 
	
	char &ref = change(name, 0); //�������� ref�� change �Լ��� �����ϴ� ������ �޴´� 
	ref = 't'; // ref�� �̿��Ͽ� name[0]�� �� ���� 
	cout<<"����°:" << name << endl; //"tSke" ��� 
}
