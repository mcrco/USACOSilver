import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class herding {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("herding.in"));
		int n = Integer.parseInt(in.readLine());

		int[] posOfCow = new int[n];
		for (int i = 0; i < n; i++) {
			posOfCow[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		Arrays.sort(posOfCow);

		int totalGaps = posOfCow[n - 1] - posOfCow[0] - (n - 2);
		int sacrificedGap = Math.min(posOfCow[1] - posOfCow[0], posOfCow[n - 1] - posOfCow[n - 2]);
		int maxMoves = totalGaps - sacrificedGap;

		int minMoves = maxMoves;
		int end = 0;
		for (int start = 0; start < n; start++) {
			while (end < n - 1 && posOfCow[end + 1] < posOfCow[start] + n) end++;
			minMoves = Math.min(minMoves, n - (end - start + 1)); 
		}
		//special case when there is a line of (n - 1) cows at either end and 2+ gap
		//minMoves should be 2 in this case, but ^algorithm will count as 1
		if (minMoves == 1) {
			if (posOfCow[n - 2] - posOfCow[0] == n - 2 && posOfCow[n - 1] - posOfCow[n - 2] > 2) minMoves = 2;
			if (posOfCow[n - 1] - posOfCow[1] == n - 2 && posOfCow[1] - posOfCow[0] > 2) minMoves = 2;
		}

		PrintWriter out = new PrintWriter(new FileWriter("herding.out"));
		out.println(minMoves);
		out.println(maxMoves);
		out.close();
	}
} 
