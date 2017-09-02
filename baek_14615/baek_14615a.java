import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class baek_14615a {

	int N;
	int M;
	int T;
	boolean visitF[];
	boolean visitB[];

	ArrayList<ArrayList<Integer>> forward;
	ArrayList<ArrayList<Integer>> backward;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_14615a().solution();
	}

	void solution() {

		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		visitF = new boolean[N];
		forward = new ArrayList<ArrayList<Integer>>(N);
		visitB = new boolean[N];
		backward = new ArrayList<ArrayList<Integer>>(N);
		
		for(int i=0; i<N; i++) {;
			forward.add(new ArrayList<Integer>());
			backward.add(new ArrayList<Integer>());
		}

		try {
			StringTokenizer st;

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				forward.get(x-1).add(y-1);
				backward.get(y-1).add(x-1);
			}

			
			T = Integer.parseInt(br.readLine());
			
			for(int i=0; i<T; i++) {
				int C = Integer.parseInt(br.readLine());
				
				bfs(1, visitF, forward);
				bfs(N-1, visitB, backward);
				if(visitF[C] && visitB[C]) {
					System.out.println("Defend the CTP");
				}
				else {
					System.out.println("Destroyed the CTP");			
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	void bfs(int num, boolean[] visit, ArrayList<ArrayList<Integer>> graph) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(num);
		visit[num] = true; //빼먹으면 안됨
		while(!q.isEmpty()) {
			int city = q.poll();
		
			ArrayList<Integer> canGo = graph.get(city);

			for(int i=0; i<canGo.size(); i++) {
				int x = canGo.get(i);

				if(!visit[x]) {
					visit[x] = true;
					q.add(x);
				}
			}
		}
	}

	void dfs(int num, boolean[] visit, ArrayList<ArrayList<Integer>> graph) {
		visit[num] = true;
		//		System.out.println(num);

		ArrayList<Integer> canGo = graph.get(num);

		for(int i=0; i<canGo.size(); i++) {
			int x = canGo.get(i);

			if(!visit[x]) {
				dfs(x, visit, graph);

			}
		}

	}
}


