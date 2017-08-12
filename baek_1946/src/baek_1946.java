/*
 * 2017.07.07
 * BaekJoon 1946 : Greedy Algorithm
 * @author gonjong
 */

import java.util.PriorityQueue;
import java.util.Scanner;


public class baek_1946 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_1946().solution();
	}
	
	void solution(){
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int i=0; i<T; i++){
			int N = sc.nextInt();
			PriorityQueue<score> score = new PriorityQueue<score>();
			int cnt = 0;
			
			for(int j=0; j<N; j++){
				score.add(new score(sc.nextInt(), sc.nextInt()));
			}
			
			//새로 검사하는 값이 인터뷰점수도 기존 그룹의 최소값보다 높다면
			//기존 그룹의 인원이 합격할 수 없게 되므로 그룹으로 들어올 수 없다.
			int minInterviewScore = score.peek().interview;
			score.poll();
			cnt++;
			while(!score.isEmpty()){
				if(score.peek().interview<minInterviewScore){
					cnt++;
					minInterviewScore = score.peek().interview;
				}
				score.poll();
			}
			
			System.out.println(cnt);
		}
	}

	
}

/*
 * 서류 심사 점수, 면접 점수를 우선순위 큐로 관리.
 * 서류 심사 점수값이 작을 수록 우선순위가 높다.
 */
class score implements Comparable<score>{

	int paper;
	int interview;
	
	score(int a, int b){
		this.paper = a;
		this.interview = b;
	}
	
	@Override
	public int compareTo(score o) {
		// TODO Auto-generated method stub
		return this.paper<o.paper?-1:1;
	}
	
}