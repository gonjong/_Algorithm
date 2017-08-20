import java.util.Scanner;

public class baek_2293 {

	int n;
	int k;
	int coin[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_2293().solution();
	}

	void solution() {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		coin = new int[n][k+1];
		int min = 10001;
		for(int i=0; i<n; i++) {
			coin[i][0] = sc.nextInt();
			if(coin[i][0]<min) {
				min = coin[i][0];
			}
		}
		
		if(k<min) {
			System.out.println("0");
		}
		else {
			for(int j=0;j<n;j++) {
				if(coin[j][0] == 1) {
					coin[j][1]++;
				}
			}
			for(int i=2; i<k; i++) {
				for(int j=0; j<n; j++) {
					int x = i-coin[j][0];
					
					for(int h=0;h<n;h++) {
						
					}
				}
			}	
		}
		int ans = 0;
		for(int i=0; i<n; i++) {
			ans+=coin[i][k];
		}
		System.out.println(ans);
	}
}
