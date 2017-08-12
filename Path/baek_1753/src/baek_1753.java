/*
 * 2017.05.21
 * baek_1753 : 최단경로 dijkstra
 * @author : gonjong
 * 경로 가중치가 양수이면 dijkstra로 해야 시간초과가 안남
 * 경로 가중치가 음수가 있으면 bellman-ford 사용
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class baek_1753 {

	static int V;
	static int E;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		int K = sc.nextInt();
		HashMap<Integer, ArrayList<graph2>> graph = new HashMap<Integer, ArrayList<graph2>>();

		for(int i=1; i<=V;i++){
			ArrayList<graph2> n = new ArrayList<graph2>();
			
			graph.put(i, n);
		}
		for(int i=0; i<E; i++){
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			
			ArrayList<graph2> from = graph.get(x);
			from.add(new graph2(x,y,z));
			graph.put(x, from);
		}
		
		new baek_1753().dijkstra(graph, K);

	}

	void dijkstra(HashMap<Integer, ArrayList<graph2>> graph, int k){
		
		int dist[] = new int[V+1];
		Arrays.fill(dist, 3000001);
		
		dist[k]=0;

		PriorityQueue<Integer> x = new PriorityQueue<Integer>();
		x.add(k);
		
		while(!x.isEmpty()){
			ArrayList<graph2> temp = graph.get(x.poll());
			
			for(int i=0; i<temp.size(); i++){
				int v = temp.get(i).v;
				int u = temp.get(i).u;
				int w = temp.get(i).w;
				
				if(dist[u]>dist[v]+w){
					dist[u] = dist[v]+w;
					x.add(u);
				}
			}
			
		}
		
		for(int i=1; i<=V; i++){
			if(dist[i]==3000001){
				System.out.println("INF");
			}
			else{
				System.out.println(dist[i]);	
			}
		}
	}
	/*
	void bellmanFord(HashMap<Integer, ArrayList<graph2>> graph, int k){
		int dist[] = new int[V+1];
		Arrays.fill(dist, 3000001);
		
		dist[k] = 0;
		
		for(int h=0; h<E; h++){
	
			for(int v=1; v<=V; v++){
				ArrayList<graph2> x = graph.get(v);
				
				for(int i=0; i<x.size();i++){
					if(dist[x.get(i).u] > dist[x.get(i).v]+x.get(i).w){
						dist[x.get(i).u] = dist[x.get(i).v]+x.get(i).w;
					}
				}	
			}
				
		}
		
		for(int i=1; i<=V; i++){
			if(dist[i]==3000001){
				System.out.println("INF");
			}
			else{
				System.out.println(dist[i]);	
			}
		}
	}
	*/

}

class graph2 implements Comparable<graph2>{
	int v;
	int u;
	int w;
	graph2(){
		
	}
	graph2(int v, int u, int w){
		this.v = v;
		this.u = u;
		this.w = w;
	}
	@Override
	public int compareTo(graph2 arg0) {
		// TODO Auto-generated method stub
		return this.w<arg0.w ? -1:1;
	}
}