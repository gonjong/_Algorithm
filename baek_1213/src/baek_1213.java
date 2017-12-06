/*
 * 2017.10.07
 * BaekJoon 1213 : 펠린드롬
 * @author gonjong
 * 펠린드롬은 무조건 stack 생각
 */
import java.util.Scanner;
import java.util.Stack;

public class baek_1213 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_1213().solution();
	}
	
	void solution() {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		int arr[] = new int[26];
		
		for(int i=0; i<str.length(); i++) {
			arr[str.charAt(i)-'A']++;
		}
		
		Stack<Character> st1 = new Stack<Character>();
		Stack<Character> st2= new Stack<Character>();
		int cnt = 0;
		for(int i=0; i<26; i++) {
			if(arr[i]%2==0) {
				for(int j=arr[i];j>0;j--) {
					if(j>arr[i]/2) {
						st1.push((char)('A'+i));	
					}
					else {
						st2.push((char)('A'+i));
					}
				}
				arr[i] = 0;
			}
			else {
				for(int j=arr[i];j>1;j--) {
					if(j>arr[i]/2+1) {
						st1.push((char)('A'+i));	
					}
					else {
						st2.push((char)('A'+i));
					}
				}
				arr[i] = 1;
			}
		}
		
		for(int i=0; i<26; i++) {
			if(arr[i]==1) {
				cnt++;
				st1.push((char)('A'+i));
			}
		}
	
		if(cnt>=2) {
			System.out.println("I'm Sorry Hansoo");
		}
		else {
			while(!st2.isEmpty()) {
				st1.push(st2.pop());
			}
		
			while(!st1.isEmpty()) {
				System.out.print(st1.pop());	
			}
			
		}
	}

}
