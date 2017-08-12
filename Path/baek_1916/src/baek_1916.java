/*
 * 2017.04.15
 * baek_1916 : Dijkstra
 * @author : gonjong
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;


public class baek_1916 {
	static int n; // 도시 개수
	static int m; // 버스 개수
	static int start; // 시작위치
	static int end; //도착 위치
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<Integer, ArrayList<graph>> graph = new HashMap<Integer, ArrayList<graph>>();
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
			
		ArrayList<graph> connect = new ArrayList<graph>();
		
		int i = 0;
		while(i<m){
			int nodeNum = sc.nextInt();

			connect = graph.get(nodeNum);
			if(connect == null){
				connect = new ArrayList<graph>();
			}
			connect.add(new graph(sc.nextInt(), sc.nextInt()));
			graph.put(nodeNum, connect);
			i++;
		}			//인접리스트 표현방식 그래프 표현
		
		start = sc.nextInt();
		end = sc.nextInt();
		
		int dist[] = new int[n+1];
		dijkstra(graph, dist, start, end);
		
	}

	
	/*
	 * 최소비용(최단거리)을 구하는 알고리즘 : Dijkstra 알고리즘
	 */
	static void dijkstra(HashMap<Integer, ArrayList<graph>> graph, int dist[], int start, int end){
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		ArrayList<graph> node = new ArrayList<graph>();
		PriorityQueue<graph> uncheck = new PriorityQueue<>();
		
		int u=start;
		int v = 0;
		uncheck.add(new graph(start,0));	//초기값
		
		while(!uncheck.isEmpty()){

			u = uncheck.poll().end;
			node = graph.get(u);
			if(node == null)
				continue;
			for(int i=0; i<node.size(); i++){
				v = node.get(i).end;
				if(dist[v]>dist[u]+node.get(i).distance){			//거리가 더 작아지는 경우 변경
					dist[v] = dist[u] + node.get(i).distance;
					uncheck.add(new graph(v, dist[v]));
				}	
			}
			
		}
		
		System.out.println(dist[end]);
			
		
	}
	
}

class graph implements Comparable<graph>{
	int end;
	int distance;
	
	graph(int e, int d){
		this.end = e;
		this.distance = d;
	}

	@Override
	public int compareTo(graph arg0) {
		// TODO Auto-generated method stub
		return this.distance<arg0.distance ? -1:1;
	}
}
