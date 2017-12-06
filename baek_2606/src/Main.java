/*
 * 2017.10.22
 * BaekJoon 2606 : dfs
 * @author gonjong
 * 그래프가 쌍방향인지 한방향인지 주의!
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	int N;
	int M;
	ArrayList<ArrayList<Integer>> arr;
	boolean visit[];
	int answer = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main().sol();
	}
	
	void sol() {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		visit = new boolean[N];
		arr = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<N; i++) {
			arr.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			int a = sc.nextInt();
			a--;
			int b = sc.nextInt();
			b--;
			
			arr.get(a).add(b);
			arr.get(b).add(a);
		}
			
		dfs(0);
		System.out.println(answer);
	}
	
	void dfs(int v) {
		visit[v] = true;
		ArrayList<Integer> list = arr.get(v);
		
		for(int i=0 ; i<list.size(); i++) {
			if(!visit[list.get(i)]) {
				answer++;
				dfs(list.get(i));
			}
		}
	}

}
