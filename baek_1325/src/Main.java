/*
 * 2017.10.23
 * BaekJoon 1325 : dfs
 * @author gonjong
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	int N;
	int M;
	int cnt = 0;
	boolean visit[];
	ArrayList<asc> answer;
	ArrayList<ArrayList<Integer>> arr;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new Main().sol();
	}
	
	void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		arr = new ArrayList<ArrayList<Integer>>(N);
		visit = new boolean[N];
		answer = new ArrayList<asc>(N);
		
		for(int i=0; i<N; i++) {
			arr.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			int a = Integer.parseInt(s[0])-1;
			int b = Integer.parseInt(s[1])-1;
			
			arr.get(b).add(a);
		}
		for(int i=0; i<N; i++) {
			Arrays.fill(visit, false);
			cnt=0;
			dfs(i);
			answer.add(new asc(i+1, cnt));
		}
		answer.sort(new asc());
		System.out.print(answer.get(0).computer);
			
		for(int i=1; i<answer.size(); i++) {
			if(answer.get(i-1).hack==answer.get(i).hack) {
				System.out.print(" "+ answer.get(i).computer);
			}
			else {
				break;
			}
		}
	}
	class asc implements Comparator<asc>{

		int computer;
		int hack;
		asc(){
			
		}
		asc(int c, int h){
			this.computer = c;
			this.hack = h;
		}
		@Override
		public int compare(asc o1, asc o2) {
			// TODO Auto-generated method stub
			if(o1.hack>o2.hack) {
				return -1;
			}
			else if(o1.hack<o2.hack) {
				return 1;
			}
			else {
				return o1.computer<o2.computer?-1:1;
			}
		}

		
		
	}
	void dfs(int v) {
		cnt++;
		visit[v] = true;
		ArrayList<Integer> list = arr.get(v);
		for(int i=0; i<list.size(); i++) {
			if(!visit[list.get(i)]) {
				
				dfs(list.get(i));
			}
		}
	}
	
}
