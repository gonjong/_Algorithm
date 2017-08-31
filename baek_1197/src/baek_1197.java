/*
 * 2017.09.01
 * BaekJoon 1197 : kruskal
 * @author gonjong
 * 
 * kruskal : union, find 연산을 이용해서 해결
 * union : find값끼리 결합
 * find : 루트까지 올라가서 반환
 * 두번빼 방식은 왜 안되는 걸까..?
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class baek_1197 {

	int V;
	int E;
	long sum = 0;
	HashMap<Integer, Integer> vertex = new HashMap<Integer, Integer>();
	ArrayList<node> edge = new ArrayList<node>();
	int group[];
	PriorityQueue<node> q = new PriorityQueue<node>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new baek_1197().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		group = new int[10001];
		Arrays.fill(group, -1);
		for(int i=0; i<E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			
			edge.add(new node(A,B,C));
			q.add(new node(A, B, C));
		}
		
		edge.sort(new asc());
		kruskal2();
		
		System.out.println(sum);
	}
	
	/*
	 * 같은 집합으로 합친다.
	 */
	void union(int a, int b) {
	
		if(a<b) {
			group[find(b)] = a;
		}
		else {
			group[find(a)] = b;
		}
		
	}
	/*
	 * 루트인 노드 번호를 반환
	 */
	int find(int x) {
		while(group[x]!=-1) {
			x = group[x];
		}
		return x; 
	}
	
	/*
	 * 최소비용 알고리즘 : kruskal
	 * 모든 간선을 최소비용 순으로 우선순위를 매긴다.
	 * find값이 다르면 합쳐서 하나의 그룹으로
	 * 같으면 간선을 포함시키지 않는다.(최소 비용에 값을 추가하지 않는다.
	 */
	void kruskal2() {
		while(!q.isEmpty()){
			node t = q.poll();
//			System.out.println(t.A+","+t.B+":"+find(t.A)+","+find(t.B));
			if(find(t.A)!=find(t.B)) {
				union(t.A, t.B);
				sum += t.weight;
			}
			
		}
	}
	void kruskal() {
		
		for(int i=0; i<edge.size(); i++) {
			node t = edge.get(i);
			if(!(vertex.containsKey(t.A) && vertex.containsKey(t.B))) {
				vertex.put(t.A, t.A);
				vertex.put(t.B, t.B);
				sum += t.weight;
			}
		}
	}
}

class asc implements Comparator<node>{

	@Override
	public int compare(node arg0, node arg1) {
		// TODO Auto-generated method stub

		return arg0.weight<arg1.weight? -1: 1;
	}
	
}

class node implements Comparable<node>{
	int A;
	int B;
	int weight;
	
	node(int v, int u, int w){
		this.A = v;
		this.B = u;
		this.weight = w;
	}

	@Override
	public int compareTo(node o) {
		// TODO Auto-generated method stub
		return this.weight<o.weight?-1 : 1;
	}
}
