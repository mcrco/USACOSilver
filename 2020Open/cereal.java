import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cereal {
	
	static int n;
	static int m;
	static int[] firstChoiceOf;
	static int[] secondChoiceOf;
	static int[] cowGettingCereal;
	static int[] ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		firstChoiceOf = new int[n];
		secondChoiceOf = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			firstChoiceOf[i] = Integer.parseInt(st.nextToken()) - 1;
			secondChoiceOf[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		br.close();
		
		cowGettingCereal = new int[m];
		for (int i = 0; i < m; i++) {
			//N means not chosen by any cow
			cowGettingCereal[i] = n; 
		}

		ans = new int[n];
		for (int numCows = 1; numCows <= n; numCows++) {
			if (numCows == 1) ans[n - numCows] = 1;
			else ans[n - numCows] = ans[n - numCows + 1] + 1;
			
			if (cowGetsScrewedOverBy(n - numCows)) ans[n - numCows]--;
		}

		PrintWriter out = new PrintWriter(new FileWriter("cereal.out"));
		for (int i = 0; i < n; i++) {
			out.println(ans[i]);
		}
		out.close();
	}

	private static boolean cowGetsScrewedOverBy(int cow) {
		//check if cereal is chosen
		if (cow == n) return false;
		
		
		if (cowGettingCereal[firstChoiceOf[cow]] > cow) {
			int unfortunateCow = cowGettingCereal[firstChoiceOf[cow]];
			cowGettingCereal[firstChoiceOf[cow]] = cow;
			return cowGetsScrewedOverBy(unfortunateCow);
		} 

		if (cowGettingCereal[secondChoiceOf[cow]] > cow) {
			int unfortunateCow = cowGettingCereal[secondChoiceOf[cow]]; 
			cowGettingCereal[secondChoiceOf[cow]] = cow;
			return cowGetsScrewedOverBy(unfortunateCow);
		}

		return true;
	}
}
