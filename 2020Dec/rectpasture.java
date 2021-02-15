import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rectpasture {

	static int n;
	static int[][] numCowsAt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[] xCoords = new int[n];
		int[] yCoords = new int[n];
		point[] cows = new point[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			xCoords[i] = x;
			yCoords[i] = y;
			cows[i] = new point(x, y);
		}

		Arrays.sort(xCoords);
		Arrays.sort(yCoords);
		for (int i = 0; i < n; i++) {
			cows[i].x = positionOf(cows[i].x, xCoords);
			cows[i].y = positionOf(cows[i].y, yCoords);
		}
		
		numCowsAt = new int[n][n];
		for (int i = 0; i < n; i++) {
			numCowsAt[cows[i].x][cows[i].y]++;
		}
		
		fillPrefixSum();

		long ans = n + 1;
		for (int top = 0; top < n; top++) {
			for (int bottom = 0; bottom < n; bottom++) {
				if (cows[top].y <= cows[bottom].y) continue;
				
				point bottomLeft1 = new point(0, cows[bottom].y);
				point topRight1 = new point(Math.min(cows[bottom].x, cows[top].x) - 1, cows[top].y);
				point bottomLeft2 = new point(Math.max(cows[bottom].x, cows[top].x) + 1, cows[bottom].y);
				point topRight2 = new point(n - 1, cows[top].y);
				
				ans += (cowsBetween(bottomLeft1, topRight1) + 1) * (cowsBetween(bottomLeft2,topRight2) + 1); 
			}
		}

		System.out.println(ans);
	}

	private static int positionOf(int coord, int[] coords) {
		int low = 0;
		int high = n;

		while (low < high) {
			int mid = (low + high) / 2;

			if (coords[mid] < coord) {
				low = mid + 1;
			}
			else { 
				high = mid;
			}
		}

		return low;
	}

	private static void fillPrefixSum() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (r - 1 >= 0) numCowsAt[r][c] += numCowsAt[r - 1][c];
				if (c - 1 >= 0)	numCowsAt[r][c] += numCowsAt[r][c - 1];
				if (r - 1 >= 0 && c - 1 >= 0) numCowsAt[r][c] -= numCowsAt[r - 1][c - 1];
			}
		}
	}

	static class point {
		int x;
		int y;

		public point(int a, int b) {
			x = a;
			y = b;
		}

		public String toString() {
			return x + " " + y;
		}
	}

	private static int cowsBetween(point bottomLeft, point topRight) {
		if (bottomLeft.x < 0 || bottomLeft.y < 0 || bottomLeft.x > n - 1 || bottomLeft.y > n - 1) return 0;
		if (topRight.x < 0 || topRight.y < 0 || topRight.x > n - 1 || topRight.y > n - 1) return 0;
		
		int ret = numCowsAt[topRight.x][topRight.y];
		if (bottomLeft.x > 0) ret -= numCowsAt[bottomLeft.x - 1][topRight.y];
		if (bottomLeft.y > 0) ret -= numCowsAt[topRight.x][bottomLeft.y - 1];
		if (bottomLeft.x > 0 && bottomLeft.y > 0) ret += numCowsAt[bottomLeft.x - 1][bottomLeft.y - 1];

		return ret;
	}
}
