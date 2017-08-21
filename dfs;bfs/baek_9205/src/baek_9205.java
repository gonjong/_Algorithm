/*
 * 2017.08.20
 * BaekJoon 9205 : dfs, bfs
 * @author gonjong
 * 문제 파악 제대로 : 맨해튼 거리/유클리드 거리
 * 답 출력 happy, sad 대소문자 정확히!!
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class baek_9205 {

	int t;
	int n;
	boolean happy = false;
	pos sPos;
	pos rPos;
	ArrayList<pos> pPos = new ArrayList<pos>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_9205().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		t = sc.nextInt();
		for(int i=0; i<t; i++) {
			
			n = sc.nextInt();
			pPos.clear();
			happy = false;
			sPos = new pos(sc.nextInt(), sc.nextInt());
			for(int j=0; j<n; j++) {
				pPos.add(new pos(sc.nextInt(), sc.nextInt()));
			}
			rPos = new pos(sc.nextInt(), sc.nextInt());
			
			canGo(sPos.x, sPos.y);
//			bfscanGo();
			if(!happy) {
				System.out.println("sad");
			}
			else {
				System.out.println("happy");
			}
		}
		
	}
	
	void bfscanGo() {
		Queue<pos> q= new LinkedList<pos>();
		
		q.add(sPos);
		
		while(!q.isEmpty()) {
			
			pos curr = q.poll();
			
			if(Math.abs(curr.x-rPos.x) + Math.abs(curr.y-rPos.y)<=1000.0) {
				happy = true;
				break;
			}
			
			for(int i=0; i<pPos.size();i++) {
				if(!pPos.get(i).visit && (Math.abs(curr.x-pPos.get(i).x) + Math.abs(curr.y-pPos.get(i).y)<=1000.0)) {
					pPos.get(i).visit = true;
					q.add(pPos.get(i));
				}
			}
			
		}
		
	}
	
	void canGo(int x, int y) {
		if(Math.abs(x-rPos.x) + Math.abs(y-rPos.y)<=1000.0) {
			happy = true;
			return;
		}
		
		for(int i=0; i<pPos.size(); i++) {
			if(!pPos.get(i).visit && (Math.abs(x-pPos.get(i).x) + Math.abs(y-pPos.get(i).y)<=1000.0)) {
				pPos.get(i).visit = true;
				canGo(pPos.get(i).x, pPos.get(i).y);
			}
		}
	}

}

class pos {
	int x;
	int y;
	boolean visit = false;
	pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}
