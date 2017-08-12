/*
 * baek_1463 : 1로 만들기, 동적 프로그래밍
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class baek_1463 {

	static int N;		//입력값
	static int MAX = Integer.MAX_VALUE;		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
				
		}catch(IOException e){
			
		}
		
		int a[] = new int[N+1];		//index별 최소로 1을 만드는 횟수를 저장
		int min = MAX;
		a[1] = 0;
		for(int i=2; i<N+1; i++){
			min = MAX;
									//3으로 나누어 떨어지는 경우
									//i/3까지 최소 연산수에 1을 더한 값과 min을 비교한다.
			if((i%3)==0){					
				if(min > a[i/3]+1){
					min = a[i/3]+1;
				}
			}
									//2로 나누어 떨어지는 경우
									//i/2까지 최소 연산수에 1을 더한 값과 min을 비교한다.
			if((i%2)==0){
				if(min > a[i/2]+1){
					min = a[i/2]+1;
				}
			}	
									//나머지의 경우, i-1까지의 최소 연산수에 1을 더한 값과 min을 비교한다.
			if(min>a[i-1]+1){
				min = a[i-1]+1;
			}

			a[i] = min;			//세가지 경우 중 가장 작은 횟수가 최소 연산수이다.

		}

		System.out.println(a[N]);
	}
}
