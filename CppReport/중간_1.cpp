#include <iostream>
#include <cstring>
using namespace std;

int main(){
	
	char firstID[10], firstPW[10]; //초기 아이디와 비밀번호를 저장할 변수 
	char inputID[10], inputPW[10]; //로그인에 사용할 변수 
	int checkID, checkPW; //strcmp()의 값을 저장할 변수 
	
	cout<<"초기 아이디를 입력하세요 : ";
	cin>>firstID;
	cout<<"초기 비밀번호를 입력하세요 : ";
	cin>>firstPW;
	cout<<"========================================================="<<endl<<endl;
	
	cout<<"로그인을 위해 아이디와 패스워드를 순서대로 입력하세요."<<endl<<endl;
	
	while(true){
		cout<<"아이디를 입력하세요 : ";
		cin>>inputID;
		cout<<"비밀번호를 입력하세요 : ";
		cin>>inputPW;
		
		checkID = strcmp(firstID, inputID); 
		checkPW = strcmp(firstPW, inputPW); //초기값과 입력값이 같다면 0 리턴 
		
		if(checkID == 0 && checkPW == 0){
			cout<<"올바른 아이디와 패스워드를 입력했습니다."<<endl;
			break;
		}
		
		else if(checkID == 0 && checkPW != 0){
			cout<<"패스워드가 틀립니다. 다시 입력하세요."<<endl<<endl;
		}
		
		else if(checkID != 0 && checkPW == 0){
			cout<<"아이디가 틀립니다. 다시 입력하세요."<<endl<<endl;
		}
		
		else{
			cout<<"아이디와 패스워드가 모두 틀립니다. 다시 입력하세요."<<endl<<endl;
		}
	}	
}
