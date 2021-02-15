import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class spacedout {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] beautyOf = new int[n][n];
		for (int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				beautyOf[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] maxSumOfRow = new int[n];
		int[] maxSumOfColumn = new int[n];

		for (int r = 0; r < n; r++) {
			int evenSum = 0;
			int oddSum = 0;

			for (int c = 0; c < n; c++) {
				if (c % 2 == 0) evenSum += beautyOf[r][c];
				else oddSum += beautyOf[r][c];
			}

			maxSumOfRow[r] = Math.max(evenSum, oddSum);
		}

		for (int c = 0; c < n; c++) {
			int evenSum = 0;
			int oddSum = 0;

			for (int r = 0; r < n; r++) {
				if (r % 2 == 0) evenSum += beautyOf[r][c];
				else oddSum += beautyOf[r][c];
			}

			maxSumOfColumn[c] = Math.max(evenSum, oddSum);
		}

		int ansIfAlternateRows = 0;
		int ansIfAlternateColumns = 0;
		for (int i = 0; i < n; i++) {
			ansIfAlternateRows += maxSumOfRow[i];
			ansIfAlternateColumns += maxSumOfColumn[i];
		}

		System.out.println(Math.max(ansIfAlternateRows, ansIfAlternateColumns));
	}
}
