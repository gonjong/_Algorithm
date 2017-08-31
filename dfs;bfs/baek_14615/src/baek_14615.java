import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class baek_14615 {

	int N;
	int M;
	int T;
	boolean visit[];
	boolean possible = false;
	ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_14615().solution();
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
		
		visit = new boolean[N];
		graph = new ArrayList<ArrayList<Integer>>(N);

		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		try {
			StringTokenizer st;

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());

				graph.get(Integer.parseInt(st.nextToken())-1).add(num-1);
			}

			T = Integer.parseInt(br.readLine());
			for(int i=0; i<T; i++) {
				int C = Integer.parseInt(br.readLine());
//				bfs(C-1);
				Arrays.fill(visit, false);
				dfs(N-1);
				possible = visit[0];
				possible &= visit[C-1];
				if(possible) {
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

	void bfs(int num) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(num);
		
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

	void dfs(int num) {
		visit[num] = true;
		//		System.out.println(num);

		ArrayList<Integer> canGo = graph.get(num);

		for(int i=0; i<canGo.size(); i++) {
			int x = canGo.get(i);

			if(!visit[x]) {
				dfs(x);

			}
		}

	}

	void bfs1(int boom) {

		boolean save = false;
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(0);

		while(!q.isEmpty()) {
			int city = q.poll();

			if(city==boom) {
				save = true;
				break;
			}

			ArrayList<Integer> canGo = graph.get(city);

			for(int i=0; i<canGo.size(); i++) {
				int x = canGo.get(i);

				if(!visit[x]) {
					visit[x] = true;
					q.add(x);
				}
			}
		}

		if(!save) {
			System.out.println("Destroyed the CTp");
			return;
		}

		save = false;
		q.clear();
		Arrays.fill(visit, false);
		q.add(boom-1);

		while(!q.isEmpty()) {
			int city = q.poll();

			if(city==N-1) {
				save = true;
				break;
			}


			ArrayList<Integer> canGo = graph.get(city);

			for(int i=0; i<canGo.size(); i++) {
				int x = canGo.get(i);

				if(!visit[x]) {
					visit[x] = true;
					q.add(x);
				}
			}
		}

		if(!save) {
			System.out.println("Destroyed the CTP");
		}
		else {
			System.out.println("Defend the CTP");
		}
	}

}


