/*
 * 2017.08.07
 * BaekJoon 10799 : Stack
 * @author : gonjong
 * 자르게 되면 남는 규칙을 찾는데 시간이 좀 걸림. 스택원리를 쓴다고 파악하면 이것을 이용해서 만들어갈 것.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class baek_10799 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_10799().solution();
	}

	void solution() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		Stack stack = new Stack();
		boolean left = true;
		int cnt = 0;
		
		try {
			String str = br.readLine();
			
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i)=='(') {
					stack.push(1);
					left = true;
				}
				else if(str.charAt(i)==')') {
					stack.pop();
					
					//이전 괄호가 left이면 자르는 부분, 지금까지 쌓인 막대기를 자른다.
					//이전 괄호가 right이면 짧은 것만 잘리기 때문에 cnt는 1만 증가한다.
					if(left) {				
						cnt += stack.size();
						left = false;
					}
					else {
						cnt+=1;
					}
				}
			}
			
			System.out.println(cnt);
		}catch(IOException e) {
			System.out.println(e);
		}
		
	}
}
