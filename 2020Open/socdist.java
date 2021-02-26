import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class socdist {
	
	static int n;
	static int m;
	static interval[] intervals;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("socdist.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		intervals = new interval[n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			intervals[i] = new interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		in.close();

		long low = 0L;
		long high = (long)10e18;
		while (low < high) {
			long d = (low + high) / 2;
			System.out.println(d);
			if (valid(d)) {
				low = d + 1;
			}
			else {
				high = d;
			}
		}

		if (!valid(low)) low--;

		PrintWriter out = new PrintWriter(new FileWriter("socdist.out"));
		out.print(low);
		out.close();
	}

	static class interval {
		int start;
		int end;

		public interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	private static boolean valid(long d) {
		long numCowsLeft = n;
		for (int i = 0; i < m; i++) {
			numCowsLeft -= ((intervals[i].end - intervals[i].start) / d + 1);
		}

		return numCowsLeft <= 0;
	}
}
