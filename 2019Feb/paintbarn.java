import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class paintbarn {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] layersAt = new int[1001][1001];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int bottom = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int top = Integer.parseInt(st.nextToken());

			for (int y = bottom; y < top; y++) {
				layersAt[left][y]++;
				layersAt[right][y]--;
			}
		}
		br.close();
		
		for (int y = 0; y < 1001; y++) {
			for (int x = 1; x < 1001; x++) {
				layersAt[x][y] += layersAt[x - 1][y];
			}
		}
		
		int ans = 0;
		for (int x = 0; x < 1001; x++) {
			for (int y = 0; y < 1001; y++) {
				if (layersAt[x][y] == k) ans++;
			}
		}

		PrintWriter out = new PrintWriter(new FileWriter("paintbarn.out"));
		out.println(ans);
		out.close();
	}
}
