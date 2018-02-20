/*
 * 2018.02.20
 * BaekJoon 1504 : dijkstra
 * @author gonjong
 * 특정 경로를 통과하는 최단거리를 구하려면 경우의 수를 잘 파악해서 나눌 것.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

	int N;
	int E;
	int m1, m2;
	
	int[][] dist;
	ArrayList<ArrayList<graph>> arr;

	HashMap<Integer, Boolean> hash = new HashMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().sol();
	}
	
	//플로이드 워셜문제 아니었음.
	void floydWarshell() {
	
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(dist[i][j]>dist[i][k]+dist[k][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
	}
	
	int dijkstra(int start, int end) {
		
		int[] dist = new int[N+1];
//		boolean[] visit = new boolean[N+1];
		Arrays.fill(dist, 100000001);
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(start);
//		visit[start]=true;
	
		dist[start] = 0;
		
		while(!q.isEmpty()) {
			int num = q.poll();
			ArrayList<graph> nodeList = arr.get(num);
			
			for(int i=0; i<nodeList.size(); i++) {
				graph node = nodeList.get(i);
				
				if( dist[node.b]>dist[num]+node.c) {
					dist[node.b] = dist[num]+node.c;
					q.add(node.b);
				}
			}
		}
		
//		System.out.println(start+"-"+end+":"+dist[end]);
		return dist[end];
	}
	
	void sol() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String[] s = br.readLine().split(" ");
			
			N = Integer.parseInt(s[0]);
			E = Integer.parseInt(s[1]);
				
			arr = new ArrayList<ArrayList<graph>>(N+1);
			dist = new int[N+1][N+1];
			
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					dist[i][j] = 100000001;
				}
			}
			for(int i=0; i<N+1; i++) {
				arr.add(new ArrayList<graph>());
			}
			
			for(int i=0; i<E; i++) {
				s = br.readLine().split(" ");
				int a = Integer.parseInt(s[0]);
				int b = Integer.parseInt(s[1]);
				int c = Integer.parseInt(s[2]);
				
				arr.get(a).add(new graph(b, c));
				arr.get(b).add(new graph(a, c));
				dist[a][b] = c;
				dist[b][a] = c;
			}

			s = br.readLine().split(" ");
			m1 = Integer.parseInt(s[0]);
			m2 = Integer.parseInt(s[1]);
			
		}catch(IOException e) {
			System.out.println(e);
		}

		int ans = -1;
		
		
		//나누는 방법 기억.
		if((m1==1 && m2==N) || (m1==N && m2==1)) {
			ans = dijkstra(1, N);
		}
		else if(m1==1 || m1==N) {
			ans = dijkstra(1, m2)+dijkstra(m2, N);
		}
		else if(m2==1 || m2==N) {
			ans = dijkstra(1, m1)+dijkstra(m1, N);
		}
		else {
			int t1 = dijkstra(1, m1)+dijkstra(m1, m2)+dijkstra(m2, N);
			int t2 = dijkstra(1, m2)+dijkstra(m2, m1)+dijkstra(m1, N); 
			ans = t1>t2? t2:t1;
		}
		
		if(ans>100000001) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);	
		}
		
	}

	class graph implements Comparable<graph>{
		int b, c;
		int a;
		
		graph(int b, int c){
			this.b = b;
			this.c = c;
		}
		graph(int a, int b, int c){
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(graph arg0) {
			// TODO Auto-generated method stub
			return this.c<arg0.c?-1:1;
		}
	}
}
