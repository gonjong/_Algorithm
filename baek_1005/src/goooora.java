/*
 * @author goooora
 */

import java.io.*;
import java.util.*;

public class goooora {
	private static int T, N, K, W;
	private static int[] cache, tm;
	private static boolean[][] ord;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int answer;
		T = Integer.parseInt(in.readLine());
		for (int i = 0 ; i < T ; i++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cache = new int[N + 1];
			tm = new int[N + 1];
			ord = new boolean[N + 1][N + 1];
			st = new StringTokenizer(in.readLine());
			for (int j = 1 ; j <= N ; j++) {
				cache[j] = -1;
				tm[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0 ; j < K ; j++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				ord[x][y] = true;
			}
			W = Integer.parseInt(in.readLine());
			answer = getAnswer();
			System.out.println(answer);
		}
	}
	private static int getAnswer() {
		return dfs(W);
	}
	private static int dfs(int d) {
		int max = 0;
		if (cache[d] != -1) {
			return cache[d];
		}
		for (int i = 1 ; i <= N ; i++) {
			if (i == d) {
				continue;
			}
			if (ord[i][d] == true) {
				int t = dfs(i);
				max = (t > max) ? t : max;
			}
		}
		cache[d] = max + tm[d];
		return cache[d];
	}
}
