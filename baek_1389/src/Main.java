/*
 * 2017.10.21
 * BaekJoon 1389 : BFS
 * @author gonjong
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	int N;
	int M;
	ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
	int min = 5001;
	int minID = 101;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}

	void sol() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for(int i=0; i<N; i++) {
			arr.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			a--;
			int b = sc.nextInt();
			b--;
			if(!arr.get(a).contains(b)) {
				arr.get(a).add(b);
			}
			if(!arr.get(b).contains(a)) {
				arr.get(b).add(a);	
			}
		}
		
		for(int i=0; i<N; i++) {
			int sum = 0;
			for(int j=0; j<N; j++) {
				if(i!=j) {
					sum+=bfs(i, j);
				}
			}
			if(sum<min) {
				min = sum;
				minID = i;
			}
			else if(sum==min && minID>i) {
				minID = i;
			}
		}
		System.out.println(minID+1);
	}

	int bfs(int v, int d) {
		Queue<graph> q = new LinkedList<graph>();
		q.add(new graph(v, 0));
		boolean visit[] = new boolean[N];
		visit[v] = true;
		graph g = new graph();
		while(!q.isEmpty()) {
			g = q.poll();
			if(g.v==d) {
				break;
			}
			ArrayList<Integer> temp = arr.get(g.v);
			for(int i=0; i<temp.size(); i++){
				int next = temp.get(i);
				
				if(!visit[next]) {
					visit[next] = true;
					q.add(new graph(next, g.d+1));
				}
			}
		}
		return g.d;
	}
	
	class graph{
		int v;
		int d;
		graph(){
			
		}
		graph(int v, int d){
			this.v = v;
			this.d = d;
		}
	}
}
