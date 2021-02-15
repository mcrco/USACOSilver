import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class hps {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		int n = Integer.parseInt(br.readLine());

		int[][] numWins = new int[3][n + 1];
		for (int i = 1; i <= n; i++) {
			String type = br.readLine();
			if (type.equals("H")) numWins[0][i]++;
			if (type.equals("P")) numWins[1][i]++;
			if (type.equals("S")) numWins[2][i]++;
		}
		br.close();

		for (int i = 1; i <= n; i++) {
			for (int type = 0; type < 3; type++) {
				numWins[type][i] += numWins[type][i - 1];
			}
		}
		
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int type1 = 0; type1 < 3; type1++) {
				for (int type2 = 0; type2 < 3; type2++) {
					ans = Math.max(ans, numWins[type1][i] + numWins[type2][n] - numWins[type2][i]);
				}
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("hps.out"));
		out.println(ans);
		out.close();
	}
}
