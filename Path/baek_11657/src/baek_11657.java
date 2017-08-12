/*
 * 2017.05.12
 * baek_11657 : Bellman-Ford
 * @author : gonjong
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class baek_11657 {

	static int N;
	static int M;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		baek_11657 x = new baek_11657();
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	//도시 수
		M = sc.nextInt();	//버스 노선 수
		
		HashMap<Integer, ArrayList<node>> graph = new HashMap<Integer, ArrayList<node>>();
		
		for(int i=1; i<=N; i++){
			graph.put(i, new ArrayList<node>());
		}
		
		for(int i=0; i<M; i++){
			int v = sc.nextInt();
			int u = sc.nextInt();
			int c=  sc.nextInt();
			
			ArrayList<node> temp = graph.get(v);
			temp.add(new node(u,c));
			graph.put(v, temp);
		}
		
		x.bellmanFord(graph);
		
	}
	
	void bellmanFord(HashMap<Integer, ArrayList<node>> graph ){
		long dist[] = new long[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean hasCycle = false;
		
		dist[1]=0;
		
		for(int k=0; k<M; k++){
			for(int vertex : graph.keySet()){				
				ArrayList<node> adj = graph.get(vertex);
				
				for(int e=0; e<adj.size(); e++){
					if(dist[adj.get(e).next]>dist[vertex]+adj.get(e).cost){
						dist[adj.get(e).next]=dist[vertex]+adj.get(e).cost;
					}
				}	
			}
		}
		
		for(int vertex : graph.keySet()){
			ArrayList<node> adj = graph.get(vertex);
			
			for(int e=0; e<adj.size(); e++){
				if(dist[adj.get(e).next]>dist[vertex]+adj.get(e).cost){
					hasCycle = true;
				}
			}
		}
		
		if(hasCycle){
			System.out.println("-1");
		}
		else{
			for(int i=2; i<dist.length; i++){
				if(dist[i]==Integer.MAX_VALUE){
					System.out.println("-1");
				}
				else{
					System.out.println(dist[i]);	
				}
			}
		}
	}

}

class node{
	int next;
	int cost;
	
	node(int v, int c){
		this.next = v;
		this.cost = c;
	}
}
