/*
 * 2018.02.08
 * BaekJoon 10216
 * @author gonjong
 * 같은 그룹인지 확인할 때 : union&find 이용.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main2 {

	int N;
	int T;
	ArrayList<pos> arr;
	ArrayList<Integer> check;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main2().sol();
	}

	void sol() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			T = Integer.parseInt(br.readLine());

		}catch(IOException e) {
			System.out.println(e);
		}

		for(int i=0; i<T; i++) {
			int ans = 0;

			try {
				N = Integer.parseInt(br.readLine());
			}catch(IOException e) {
				System.out.println(e);
			}
			arr = new ArrayList<pos>(N);
			check = new ArrayList<Integer>(N);

			for(int j=0; j<N; j++) {
				String[] temp = null;

				try {
					temp = br.readLine().split(" ");

				}catch(IOException e) {
					System.out.println(e);
				}
				arr.add(j, new pos(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
			}

			for(int j=0; j<N; j++) {
				check.add(-1);
			}
			
			for(int j=0; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					if(find(j)!=find(k)) {
						if(isGroup(arr.get(j), arr.get(k))) {
							union(j, k);	
						}
					}
				}
			}

			for(int j=0; j<N; j++) {
//				System.out.print(check.get(j)+ " ");
				if(check.get(j)==-1) {
					ans++;
				}
			}
//			System.out.println();

			System.out.println(ans);

		}
	}

	int find(int p1) {
		while(check.get(p1) != -1) {
			p1 = check.get(p1);
		}

		return p1;
	}

	
	void union(int index1, int index2) {
		check.set(index1, find(index2));	//실수 주의!! 한쪽의 find값으로 루트를 대입시켜주어야 함
	}

	boolean isGroup(pos p1, pos p2) {
		if(dist(p1, p2)<=(p1.R+p2.R)*(p1.R+p2.R)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	long dist(pos p1, pos p2) {
		return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
	}

	class pos{
		int x;
		int y;
		int R;

		pos(int x, int y, int r){
			this.x = x;
			this.y = y;
			this.R = r;
		}
	}
}
