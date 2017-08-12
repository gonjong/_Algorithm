/*
 * 2017.04.24
 * baek_1931 : Greedy Algorithm
 * @author : gonjong
 * 구간 정렬과 관련하여 end를 기준으로, 같은 경우 start를 기준으로
 * 정렬하여 최적의 값을 구한다.
 */

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class baek_1931 {

	static int n = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();	
		PriorityQueue<time> sort = new PriorityQueue<time>();
		
		for(int i=0; i<n; i++){
			sort.add(new time(i, sc.nextInt(), sc.nextInt()));
		}
		
		setRoom(sort);
	}
	
	static void setRoom(PriorityQueue<time> s){
		ArrayList<Integer> start = new ArrayList<Integer>();
		int cnt = 1;
		time temp = s.poll();
		start.add(temp.start);
		int end = temp.end;
		
		while(!s.isEmpty()){
			temp = s.poll();

			if(temp.start>=end){
				cnt++;
				end = temp.end;
			}
						/*
						 * 이미 정렬되어 있으므로 start가 end보다 큰 경우가 
						 * 다음으로 들어올 수 있는 최적의 회의시간이다.
						 */
			
		}
		System.out.println(cnt);
	}
}

/*
 * 우선순위 큐 
 * 회의 끝나는 시간이 늦을 수록, 같을 경우 시작 시간이 빠를 수록 우선순위를 가진다.
 */
class time implements Comparable<time>{
	int roomNum;
	int start;
	int end;
	
	time(int n, int s, int e){
		this.roomNum = n;
		this.start = s;
		this.end = e;
	}
	
	@Override
	public int compareTo(time arg0) {
		// TODO Auto-generated method stub
		
		if(this.end<arg0.end)
			return -1;
		else if(this.end>arg0.end){
			return 1;
		}
		else{
			if(this.start<arg0.start){
				return -1;
			}
			else if(this.start>arg0.start){
				return 1;
			}
			else{
				return -1;
			}
		}
	
	}
	
	
}
