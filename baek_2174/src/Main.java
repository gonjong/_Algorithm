/*
 * 2017.10.18
 * BaekJoon 2174 : Simulation
 * @author gonjong
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {

	int A;
	int B;
	int NN;
	int M;
	ArrayList<pos> robot;	//robot position
	boolean ok = true;
	
	//direction
	final int E = 1;
	final int W = 2;
	final int N = 3;
	final int S = 4;
	//command
	final int L = 1;
	final int R = 2;
	final int F = 3;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main().solution();	

	}

	void solution() {
		Scanner sc = new Scanner(System.in);

		A = sc.nextInt();
		B = sc.nextInt();

		NN = sc.nextInt();
		M = sc.nextInt();
		robot = new ArrayList<pos>(NN);
		sc.nextLine();
		for(int i=0; i<NN; i++) {

			String str = sc.nextLine();
			StringTokenizer st = new StringTokenizer(str);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();

			robot.add(new pos(x, y, dir));
		}

		for(int i=0; i<M; i++) {
			String str = sc.nextLine();
			StringTokenizer st = new StringTokenizer(str);
			int ro = Integer.parseInt(st.nextToken());
			ro--;
			int command = change_command(st.nextToken());
			int repeat = Integer.parseInt(st.nextToken());

			if(!do_command(ro, command, repeat)) {
				ok = false;
				break;
			}
		}

		if(ok) {
			System.out.println("OK");
		}
	}

	/*
	 * 명령 실행 함수
	 */
	boolean do_command(int r, int c, int rep) {
		pos temp = new pos();
		int crush = -2;
		
		for(int i=0; i<rep; i++) {

			switch(c) {
			case L:
				temp = comm_L(robot.get(r));
				break;
			case R:
				temp = comm_R(robot.get(r));
				break;
			case F:
				temp = comm_F(robot.get(r));
				break;
			}

			if((crush = isCrashRobot(r, temp))>=0) {
				System.out.println("Robot "+ (r+1) + " crashes into robot "+ (crush+1));
				return false;
			}

			if(isCrashWall(temp)) {
				System.out.println("Robot "+ (r+1) +" crashes into the wall");		
				return false;
			}

			robot.set(r, temp);

		}
		return true;

	}
	/*
	 * 로봇과 부딪히는지 확인하는 함수
	 */
	int isCrashRobot(int num, pos curr) {

		for(int i=0; i<robot.size(); i++) {
			pos other = robot.get(i);
			if(num != i ) {
				if(curr.x==other.x && curr.y == other.y) {
					return i;
				}
			}
		}

		return -1;
	}

	/*
	 * 벽과 부딪히는지 확인하는 함수
	 */
	boolean isCrashWall(pos t) {
		if(t.x>0 && t.x<=A && t.y>0 && t.y<=B) {
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/*
	 * L command
	 */
	pos comm_L(pos r) {
		switch(r.d) {
		case E:
			r.d = N;
			break;
		case W:
			r.d = S;
			break;
		case N:
			r.d = W;
			break;
		case S:
			r.d = E;
			break;
		}
		return r;
	}

	/*
	 * R command
	 */
	pos comm_R(pos r) {
		switch(r.d) {
		case E:
			r.d = S;
			break;
		case W:
			r.d = N;
			break;
		case N:
			r.d = E;
			break;
		case S:
			r.d = W;
			break;
		}
		return r;
	}
	
	/*
	 * F command
	 */
	pos comm_F(pos r) {
		switch(r.d) {
		case E:
			r.x++;
			break;
		case W:
			r.x--;
			break;
		case N:
			r.y++;
			break;
		case S:
			r.y--;
			break;
		}
		return r;
	}
	
	/*
	 * Change command from String to Integer
	 */
	int change_command(String comm) {
		int x = -1;
		switch(comm) {
		case "L":
			x = L;
			break;
		case "R":
			x = R;
			break;
		case "F":
			x = F;
			break;
		}
		return x;
	}
	
	/*
	 * Robot position
	 */
	class pos{
		int x;
		int y;
		int d;
		pos(){

		}
		pos(int x, int y, String d){
			this.x = x;
			this.y = y;
			this.d = change_dir(d);
		}

		/*
		 * Change robot direction from String to Integer
		 */
		int change_dir(String d) {
			int x = -1;
			switch(d) {
			case "E":
				x = E;
				break;
			case "W":
				x = W;
				break;
			case "N":
				x = N;
				break;
			case "S":
				x = S;
				break;
			}
			return x;
		}
	}

}
