/*
 * 2017.08.01
 * BaekJoon 14615 : bfs
 * (1) 시간 초과 원인
 * 입출력이 많아, 간선이 많은 경우, test_case가 새롭게 반복되어야 하는 것인지 판단.
 * 반복되지 않아도 되는 것이면 bfs를 한번만 돌리고 답을 냄
 * (2) 답이 틀린 경우 원인
 * N->1까지 bfs 결과로만 판단할 경우 문제, 1이 true인 경우는 C를 통과하지 않고 온 경우일 수 있는데 그 경우도 visit[1] = True가 됨
 * 따라서 노드에서 나가는 경우(1)와 노드에서 들어오는 오는 경우(N)를 한번씩 계산하고 test_case는 이미 만든 결과로 구해야 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class baek_14615 {

	int N;
	int M;
	int T;
	boolean visitF[];
	boolean visitB[];

	ArrayList<ArrayList<Integer>> forward;
	ArrayList<ArrayList<Integer>> backward;
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

			bfs(0, visitF, forward);
			bfs(N-1, visitB, backward);
			
			T = Integer.parseInt(br.readLine());
			
			for(int i=0; i<T; i++) {
				int C = Integer.parseInt(br.readLine());
			
				if(visitF[C-1] && visitB[C-1]) {
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


