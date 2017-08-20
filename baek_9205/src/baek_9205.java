import java.util.ArrayList;
import java.util.Scanner;

public class baek_9205 {

	int t;
	int n;
	boolean happy = false;
	pos sPos;
	pos rPos;
	ArrayList<pos> pPos = new ArrayList<pos>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new baek_9205().solution();
	}
	
	void solution() {
		
		Scanner sc = new Scanner(System.in);
		
		t = sc.nextInt();
		for(int i=0; i<t; i++) {
			
			n = sc.nextInt();
			pPos.clear();
			happy = false;
			sPos = new pos(sc.nextInt(), sc.nextInt());
			for(int j=0; j<n; j++) {
				pPos.add(new pos(sc.nextInt(), sc.nextInt()));
			}
			rPos = new pos(sc.nextInt(), sc.nextInt());
			
			canGo(sPos.x, sPos.y);
			if(!happy) {
				System.out.println("bad");
			}	
		}
		
	}
	
	void canGo(int x, int y) {
		if(Math.sqrt((x-rPos.x)*(x-rPos.x)
				+(y-rPos.y)*(y-rPos.y))<=1000.0) {
			System.out.println("Happy");
			happy = true;
			return;
		}
		
		for(int i=0; i<pPos.size(); i++) {
			if(!pPos.get(i).visit && Math.sqrt((x-pPos.get(i).x)*(x-pPos.get(i).x)
					+(y-pPos.get(i).y)*(y-pPos.get(i).y))<=1000.0) {
				pPos.get(i).visit = true;
				canGo(pPos.get(i).x, pPos.get(i).y);
			}
		}
	}

}

class pos{
	int x;
	int y;
	boolean visit = false;
	pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}
