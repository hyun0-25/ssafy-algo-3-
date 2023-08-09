package recursive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 상어의 위치(r, c) , 속력(s), 이동방향(d), 크기(z)가 주어진다.
 * 
 * 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 
 * 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
 * 상어가 이동한다.
 * 
 * 구현해야할것)
 * 
 * 1. 낚시왕이 for문을 돌며 움직인다.
 * 2. 낚시왕이 있는 열에서 탐색을하여 상어를 잡는다.
 * 3. 상어 클래스를 구현한다.
 * 3-1) 상어의 이동과, 같은 칸에 있을때 먹는 코드 구현
 * 
 * 상어가 같은 칸에 여러마리 있을때 어떻게 구현? -> 어떤 자료구조를 써야할까?
 * 
 * 상어에 isAlive boolean을 추가해서 무식하게 돌아보자
 * 
 * 
 * 시간복잡도)
 * 최악의 경우, 100(C)* 10000 * 1000  (상어탐색)
 * 
 * 
 * 
 * 개선할 점:
 * 	상어 이동 시 speed값만큼 연산을 하게되는데, speed값으로 도착점을 단번에 알 수 있다면 시간을 어마어마하게 단축시킬 수 있을 것 같다.
 * 
 * 배운 점:
 * 	막혔을때는 남에게 설명하듯이 처음부터 로직을 따라가보자. 
 * 
 */
public class BOJ_17143_낚시왕_이주혁 {
	private static int R, C, M, ans;
	private static int[][] map;
	private static Shark[] sharks;
	
	static class Shark {
		int row;
		int col;
		int speed;
		int dx;
		int dy;
		int weight;
		boolean isAlive;
		
		public Shark(int row, int col, int speed, int direct, int weight) {
			
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.weight = weight;
			getDeltas(direct);
			this.isAlive = true;
			
		}
		
		private void getDeltas(int direct) {
			switch(direct) {
			case 1:
				this.dx = -1;
				this.dy = 0;
				break;
			case 2:
				this.dx = 1;
				this.dy = 0;
				break;
			case 3:
				this.dx = 0;
				this.dy = 1;
				break;
			case 4:
				this.dx = 0;
				this.dy = -1;
				break;
			}

		}
		
		private void isAbleToEat(Shark o, int otherIdx, int myIdx) {
			if(this.weight > o.weight) {
				o.isAlive = false;
				map[row][col] = myIdx;
			} 
			else if(this.weight < o.weight){
				this.isAlive = false;
				map[row][col] = otherIdx;
			}
		}
		
	}
	
	private static boolean isOutOfRange(int r, int c, int dx, int dy) {
		
		int nr = r + dx;
		int nc = c + dy;
		
		return nr < 1 || nr > R || nc < 1 || nc > C;

	}
	
	private static void move(int sharkIdx) {
		int r = sharks[sharkIdx].row;
		int c = sharks[sharkIdx].col;
		
		int dx = sharks[sharkIdx].dx;
		int dy = sharks[sharkIdx].dy;
		
		for(int i=0; i<sharks[sharkIdx].speed; i++) {

			if(isOutOfRange(r, c, dx, dy)) {
				dx *= -1;
				dy *= -1;
			}
			
			r += dx;
			c += dy;
			
		}
		
		sharks[sharkIdx].row = r;
		sharks[sharkIdx].col = c;
		sharks[sharkIdx].dx = dx;
		sharks[sharkIdx].dy = dy;
		
		if(map[r][c] != 0 &&  map[r][c] < sharkIdx) {
			sharks[sharkIdx].isAbleToEat(sharks[map[r][c]], map[r][c], sharkIdx);
		} else {
			map[r][c] = sharkIdx;
		}
		
		
	}
	
	private static void sharkMove() {
		for(int i=1; i<=M; i++) {
			
			// 시작점에 오직 혼자 있는 상어라면 0으로 만들고 출발한다.
			if(sharks[i].isAlive && map[sharks[i].row][sharks[i].col] == i) {
				map[sharks[i].row][sharks[i].col] = 0;
				move(i);
				
			// 시작점에 다른 상어가 들와있다면 0으로 만들지 않고 출발한다. 
			} else if(sharks[i].isAlive) {
				move(i);
			}
		}
	}
	
	
	private static void getShark(int col) {
		
		for(int i=1; i<=R; i++) {
			int sharkIdx = map[i][col];
			if(sharkIdx > 0 && sharks[sharkIdx].isAlive) {
				sharks[sharkIdx].isAlive = false;
				ans += sharks[sharkIdx].weight;
				map[i][col] = 0;
				return;
			}
		}

	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		M = Integer.parseInt(input[2]);
		
		map = new int[R+1][C+1];
		sharks = new Shark[M+1];
		for(int i = 1; i <= M; i++) {
			input = br.readLine().split(" ");
			
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int s = Integer.parseInt(input[2]);
			int d = Integer.parseInt(input[3]);
			int z = Integer.parseInt(input[4]);
			
			sharks[i] = new Shark(r, c, s, d, z);
			
			// r행 c열에는 i번쨰 상어가 존재한다.
			map[r][c] = i;
			
		}
		
		for(int i=1; i<=C; i++) {
			getShark(i);
			sharkMove();
		}
		
		System.out.println(ans);
		
	}
}