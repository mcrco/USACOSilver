import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("unchecked")
public class revegetate {
	
	static ArrayList<pasture>[] otherPasturesOf;  
	static int[] seedOf;
	static boolean valid = true;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("revegetate.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		otherPasturesOf = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			otherPasturesOf[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			String type = st.nextToken();
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			otherPasturesOf[a].add(new pasture(b, type));
			otherPasturesOf[b].add(new pasture(a, type));
		}
		in.close();

		seedOf = new int[n];
		for (int i = 0; i < n; i++) {
			seedOf[i] = -1;
		} 
		
		int numGroups = 0;
		for (int i = 0; i < n; i++) {
			if (seedOf[i] == -1) {
				numGroups++;
				plant(i, 0);
			}
		}
		
		String ans = "1";
		if (valid) {
			for (int i = 0; i < numGroups; i++) {
				ans += 0;
			}
		}
		else ans = "0";
	
		PrintWriter out = new PrintWriter(new FileWriter("revegetate.out"));
		out.println(ans);
		out.close();
	}

	static class pasture {
		int index;
		boolean hasSameSeed;

		public pasture(int i, String type) {
			index = i;	
			if (type.equals("S")) hasSameSeed = true;
			else hasSameSeed = false;
		}
	}

	private static void plant(int currPasture, int seed) {
		if (seedOf[currPasture] != -1) {
			if (seedOf[currPasture] != seed) valid = false;
			return;
		}

		seedOf[currPasture] = seed;

		for (pasture otherPasture : otherPasturesOf[currPasture]) {
			int otherSeed;
			if (otherPasture.hasSameSeed) otherSeed = seed;
			else otherSeed = seed ^ 1;

			plant(otherPasture.index, otherSeed);
		}
	} 
}
