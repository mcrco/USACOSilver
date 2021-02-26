import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class leftout {

	static int n;
	static int[][] orientation;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("leftout.in"));
		n = Integer.parseInt(in.readLine());

		orientation = new int[n][n];
		for (int r = 0; r < n; r++) {
			String line = in.readLine();
			for (int c = 0; c < n; c++) {
				if (line.charAt(c) == 'R') orientation[r][c] = 1;
				else orientation[r][c] = 0;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (orientation[i][j] != 0) flipRow(i);
				if (orientation[i][j] != 0) flipColumn(j);
			}

			for (int j = 0; j <= i; j++) {
				if (orientation)
			}
		}
	}

	private static void flipRow(int r) {
		for (int c = 0; c < n; c++) {
			orientation[r][c] = (orientation[r][c] + 1) % 2;
		}
	}

	private static void flipColumn(int c) {
		for (int r = 0; r < n; r++) {
			orientation[r][c] = (orientation[r][c] = 1) % 2;
		}
	}


}
