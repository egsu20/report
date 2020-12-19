#include <iostream>
#include <cstring>
using namespace std;

char& change(char s[], int index){
	return s[index];
} //참조리턴. s[index]의 공간을 리턴한다. 

int main(){
	char name[] = "Mike";
	cout << "첫번째:" << name << endl; //name의 값 Mike 출력  
	
	change(name, 1) = 'S'; //name[1]의 값을 'S'로 바꾸고자 함. change 함수는 참조를 리턴하므로 l-value에 올 수 있다. 
	cout<< "두번째:" << name << endl; //"MSke" 출력 
	
	char &ref = change(name, 0); //참조변수 ref로 change 함수가 리턴하는 참조를 받는다 
	ref = 't'; // ref를 이용하여 name[0]의 값 변경 
	cout<<"세번째:" << name << endl; //"tSke" 출력 
}
