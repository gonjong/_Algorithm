
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class iori03 {
	
	private static int N;
	private static int M;
	
	private static Map<Integer, Set<Integer>> map = new HashMap<>();
	private static Map<Integer, Set<Integer>> back = new HashMap<>();
	
	private static Map<Integer, Set<Integer>> gMap = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (a == b) {
				continue;
			}
			
			Set<Integer> edges = map.get(a);
			if (edges == null) {
				edges = new HashSet<>();
				map.put(a, edges);
			}
			edges.add(b);
			
			edges = back.get(b);
			if (edges == null) {
				edges = new HashSet<>();
				back.put(b, edges);
			}
			edges.add(a);
		}
		
		int[] group = new int[N+1];
		int[] count = new int[getGroup(group)+1];
		for (int i = 1; i <= N; i++) {
			if (group[i] > 0) {
				count[group[i]]++;
			}
			
			Set<Integer> edges = back.get(i);
			if (edges == null) {
				continue;
			}
			for (int e : edges) {
				if (group[i] == group[e]) {
					continue;
				}
				Set<Integer> gEdges = gMap.get(group[i]);
				if (gEdges == null) {
					gEdges = new HashSet<>();
					gMap.put(group[i], gEdges);
				}
				gEdges.add(group[e]);
			}
		}
		
		boolean[] gVisit = new boolean[count.length];
		Set<Integer>[] gSubSet = new Set[count.length];
		for (int g = 1; g < count.length; g++) {
			dfs(gVisit, gSubSet, g);
		}
		
		Set<Integer> maxGid = max(count, gSubSet);
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (maxGid.contains(group[i])) {
				result.add(i);
			}
		}
		Collections.sort(result);
		System.out.println(result.toString().replaceAll("\\]|\\[|,", ""));
	}

	private static Set<Integer> max(int[] count, Set<Integer>[] gSubSet) {
		Set<Integer> result = new HashSet<Integer>();
		int max = 0;
		for (int g = 1; g < count.length; g++) {
			int cnt = count[g];
			if (gSubSet[g] != null) {
				for (int sub : gSubSet[g]) {
					cnt += count[sub];
				}
			}
			if (cnt == max) {
				result.add(g);
			} else if (cnt > max) {
				result.clear();
				max = cnt;
				result.add(g);
			}
		}
		return result;
	}

	private static void dfs(boolean[] gVisit, Set<Integer>[] gSubSet, int g) {
		if (gVisit[g]) {
			return;
		}
		
		gVisit[g] = true;
		Set<Integer> gEdges = gMap.get(g);
		if (gEdges == null) {
			return;
		}
		
		gSubSet[g] = new HashSet<>();
		for (int next : gEdges) {
			dfs(gVisit, gSubSet, next);
			gSubSet[g].add(next);
			if (gSubSet[next] != null) {
				gSubSet[g].addAll(gSubSet[next]);
			}
		}
	}

	private static int getGroup(int[] group) {
		Stack<Integer> stack = new Stack<>();

		boolean[] visit = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			number(visit, stack, i);
		}
		
		int gid = 0;
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			if (group[cur] > 0) {
				continue;
			}
			gid++;
			group(group, gid, cur);
		}
		
		return gid;
	}

	private static void group(int[] group, int gid, int cur) {
		if (group[cur] > 0) {
			return;
		}
		group[cur] = gid;
		Set<Integer> edges = back.get(cur);
		if (edges == null) {
			return;
		}
		for (int next : edges) {
			group(group, gid, next);
		}
	}

	private static void number(boolean[] visit, Stack<Integer> stack, int cur) {
		if (visit[cur]) {
			return;
		}
		visit[cur] = true;
		Set<Integer> edges = map.get(cur);
		if (edges != null) {
			for (int next : edges) {
				number(visit, stack, next);
			}
		}
		stack.push(cur);
	}
}
