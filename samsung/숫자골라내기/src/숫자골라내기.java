/*
 * 2017.08.26
 * Codeground 숫자골라내기 : XOR
 * @author gonjong
 * XOR은 같은 수를 짝수번 계산하면 0, 홀수번 계산하면 한번만 XOR한 값과 동일하게 됨
 * test_case 반복될 떄마다 초기화 시킬 변수 주의!!
 */

import java.util.HashMap;
import java.util.Scanner;

public class 숫자골라내기 {

	static int Answer;
	int T;
	int N;
	HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
	
	public static void main(String args[]) throws Exception	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */		

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
		
		숫자골라내기 main = new 숫자골라내기();
		
		main.Solution();
		
	}
	
	void Solution() {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int test_case = 0; test_case < T; test_case++) {

			Answer = 0;
			N = sc.nextInt();
			
			for(int i=0; i<N; i++) {
				Answer^=sc.nextInt();
			}
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
		
	}
	
	void solution() {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int test_case = 0; test_case < T; test_case++) {

//			Answer = 0;
			N = sc.nextInt();
			
			data.clear();
			for(int i=0; i<N; i++) {
				int a = sc.nextInt();
				if(data.containsKey(a)) {
					int num = data.get(a);
					data.put(a, num+1);
				}
				else {
					data.put(a, 1);
				}
			}
			
			int x = 0;
			
			for(int i : data.keySet()) {
				if(data.get(i)%2!=0) {
					x ^= i;
				}
			}
			
			Answer = x;	
			
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}

}
