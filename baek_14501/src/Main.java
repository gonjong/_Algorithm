/*
 * 2017.10.18
 * BaekJoon 14501
 * @author gonjong
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	int N;
	int max=0;
	ArrayList<Integer> time;
	ArrayList<Integer> pay;
	ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			new Main().solution();
//			new Main().sol_dp();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	void sol_dp() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int t[] = new int[N+1];
		int p[] = new int[N+1];
		
		int dp[] = new int[N+1];
		for(int i=1; i<=N; i++) {
			String[] s = br.readLine().split(" ");
			t[i] = Integer.parseInt(s[0]);
			p[i] = Integer.parseInt(s[1]);
			dp[i] = p[i];
			
		}
		for(int i=2; i<=N; i++) {
			for(int j=1; j<i; j++) {
				if(i-j>=t[j]) {
					dp[i] = Integer.max(dp[j]+p[i], dp[i]);
				}
			}
		}
		for(int i=1; i<=N; i++) {
			if(max<dp[i] && i+t[i]<=N+1) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}
	
	void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		time = new ArrayList<Integer>(N+1);
		pay = new ArrayList<Integer>(N+1);
		graph = new ArrayList<ArrayList<Integer>>(N+2);
		
		time.add(0);
		pay.add(0);
		for(int i=1; i<=N; i++) {
			String[] s = br.readLine().split(" ");
			
			time.add(Integer.parseInt(s[0]));
			pay.add(Integer.parseInt(s[1]));
		}
		
		for(int i=0; i<=N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for(int i=1; i<=N+1; i++) {
			graph.get(0).add(i);	
		}
		for(int i=1; i<=N; i++) {
			int t = time.get(i);
			for(int j=i+t; j<=N+1; j++) {
				if(j<=N+1) {
					graph.get(i).add(j);	
				}
			}
		}
		dfs(0,0);
		System.out.println(max);
	}
	
	void dfs(int num, int p) {
		ArrayList<Integer> temp = graph.get(num);
		p += pay.get(num);
		
		for(int i=0; i<temp.size(); i++) {
			if(!graph.get(temp.get(i)).isEmpty()) {
				dfs(temp.get(i), p);
			}
			else {
				if(max<p) {
					max = p;
				}
			}
		}
	}
	
	
}
