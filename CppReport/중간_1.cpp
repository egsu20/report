#include <iostream>
#include <cstring>
using namespace std;

int main(){
	
	char firstID[10], firstPW[10]; //�ʱ� ���̵�� ��й�ȣ�� ������ ���� 
	char inputID[10], inputPW[10]; //�α��ο� ����� ���� 
	int checkID, checkPW; //strcmp()�� ���� ������ ���� 
	
	cout<<"�ʱ� ���̵� �Է��ϼ��� : ";
	cin>>firstID;
	cout<<"�ʱ� ��й�ȣ�� �Է��ϼ��� : ";
	cin>>firstPW;
	cout<<"========================================================="<<endl<<endl;
	
	cout<<"�α����� ���� ���̵�� �н����带 ������� �Է��ϼ���."<<endl<<endl;
	
	while(true){
		cout<<"���̵� �Է��ϼ��� : ";
		cin>>inputID;
		cout<<"��й�ȣ�� �Է��ϼ��� : ";
		cin>>inputPW;
		
		checkID = strcmp(firstID, inputID); 
		checkPW = strcmp(firstPW, inputPW); //�ʱⰪ�� �Է°��� ���ٸ� 0 ���� 
		
		if(checkID == 0 && checkPW == 0){
			cout<<"�ùٸ� ���̵�� �н����带 �Է��߽��ϴ�."<<endl;
			break;
		}
		
		else if(checkID == 0 && checkPW != 0){
			cout<<"�н����尡 Ʋ���ϴ�. �ٽ� �Է��ϼ���."<<endl<<endl;
		}
		
		else if(checkID != 0 && checkPW == 0){
			cout<<"���̵� Ʋ���ϴ�. �ٽ� �Է��ϼ���."<<endl<<endl;
		}
		
		else{
			cout<<"���̵�� �н����尡 ��� Ʋ���ϴ�. �ٽ� �Է��ϼ���."<<endl<<endl;
		}
	}	
}
