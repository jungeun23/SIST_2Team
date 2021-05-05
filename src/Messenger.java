import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Messenger {
	private String groupChatting;
	private String greeting;

	public static void main(String args[]) {
//		Levenshtein l = new Levenshtein();
//		NormalizedLevenshtein l = new NormalizedLevenshtein();
//		Damerau l = new Damerau();
//		 OptimalStringAlignment l = new OptimalStringAlignment();
//		JaroWinkler l = new JaroWinkler();
//		LongestCommonSubsequence l = new LongestCommonSubsequence();
//		info.debatty.java.stringsimilarity.MetricLCS l = new info.debatty.java.stringsimilarity.MetricLCS(); 
//		 QGram l = new QGram(4);
//		RatcliffObershelp l = new RatcliffObershelp();

//		System.out.println(l.distance("My string", "My $tring"));
//		System.out.println(l.distance("My string", "My $tring"));
//		System.out.println(l.distance("My string", "My $tring"));
//		System.out.println(l.distance("메일", "직장 상사한테 매일을 보내야 하는데 어떻게 하면 되니?"));
//		System.out.println(l.distance("메일", "직장 상사한테 메일을 보내야 하는데 어떻게 하면 되니?"));
//		System.out.println(l.distance("연락처", "직장 상사한테 메일을 보내야 하는데 어떻게 하면 되니?"));
//		System.out.println(l.distance("일정", "직장 상사한테 메일을 보내야 하는데 어떻게 하면 되니?"));

//		SimilarityStrategy strategy = new JaroWinklerStrategy();
//		String target = "메일";
//		String source = "직장 상사한테 메일을 보내야 하는데 어떻게 하면 되니?";
//		StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
//		double score = service.score(source, target); // Score is 0.90
//		System.out.println(score);

//		printSimilarity("메일", "직장 상사한테 매일을 보내야 하는데 어떻게 하면 되니?");

//		System.out.println(getDistance("메일", "직장 상사한테 매일을 보내야 하는데 어떻게 하면 되니?"));
//		System.out.println(getDistance("연락처", "직장 상사한테 매일을 보내야 하는데 어떻게 하면 되니?"));
//		System.out.println(getDistance("일정", "직장 상사한테 매일을 보내야 하는데 어떻게 하면 되니?"));

//		HashMap<CharSequence, Integer> l = new HashMap<CharSequence, Integer>();
//		l.put("메일", 0);
//		HashMap<CharSequence, Integer> l2 = new HashMap<CharSequence, Integer>();
//		l2.put("직장상사한테 메일을 보내야 하는데 어떻게 하면 되니 ?", 0);
//		
//		System.out.println(cosineSimilarity(l, l2));
		
	}

	public static Double cosineSimilarity(final Map<CharSequence, Integer> leftVector,
			final Map<CharSequence, Integer> rightVector) {
		if (leftVector == null || rightVector == null) {
			throw new IllegalArgumentException("Vectors must not be null");
		}

		final Set<CharSequence> intersection = getIntersection(leftVector, rightVector);

		final double dotProduct = dot(leftVector, rightVector, intersection);
		double d1 = 0.0d;
		for (final Integer value : leftVector.values()) {
			d1 += Math.pow(value, 2);
		}
		double d2 = 0.0d;
		for (final Integer value : rightVector.values()) {
			d2 += Math.pow(value, 2);
		}
		double cosineSimilarity;
		if (d1 <= 0.0 || d2 <= 0.0) {
			cosineSimilarity = 0.0;
		} else {
			cosineSimilarity = (double) (dotProduct / (double) (Math.sqrt(d1) * Math.sqrt(d2)));
		}
		return cosineSimilarity;
	}

	/**
	 * Returns a set with strings common to the two given maps.
	 *
	 * @param leftVector  left vector map
	 * @param rightVector right vector map
	 * @return common strings
	 */
	private static Set<CharSequence> getIntersection(final Map<CharSequence, Integer> leftVector,
			final Map<CharSequence, Integer> rightVector) {
		final Set<CharSequence> intersection = new HashSet<>(leftVector.keySet());
		intersection.retainAll(rightVector.keySet());
		return intersection;
	}

	/**
	 * Computes the dot product of two vectors. It ignores remaining elements. It
	 * means that if a vector is longer than other, then a smaller part of it will
	 * be used to compute the dot product.
	 *
	 * @param leftVector   left vector
	 * @param rightVector  right vector
	 * @param intersection common elements
	 * @return the dot product
	 */
	private static double dot(final Map<CharSequence, Integer> leftVector, final Map<CharSequence, Integer> rightVector,
			final Set<CharSequence> intersection) {
		long dotProduct = 0;
		for (final CharSequence key : intersection) {
			dotProduct += leftVector.get(key) * rightVector.get(key);
		}
		return dotProduct;
	}

	public static int getDistance(String s1, String s2) {
		int longStrLen = s1.length() + 1;
		int shortStrLen = s2.length() + 1; 
		int[] cost = new int[longStrLen];
		int[] newcost = new int[longStrLen]; 
		for (int i = 0; i < longStrLen; i++) {
			cost[i] = i;
		} 
		for (int j = 1; j < shortStrLen; j++) {
			newcost[0] = j; 
			for (int i = 1; i < longStrLen; i++) {
				int match = 0;
				if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
					match = 1;
				}
				int replace = cost[i - 1] + match;
				int insert = cost[i] + 1;
				int delete = newcost[i - 1] + 1;
				newcost[i] = Math.min(Math.min(insert, delete), replace);
			} 
			int[] temp = cost;
			cost = newcost;
			newcost = temp;
		}
		return cost[longStrLen - 1];
	}

	private static double similarity1(String s1, String s2) {
		String longer = s1, shorter = s2;

		if (s1.length() < s2.length()) {
			longer = s2;
			shorter = s1;
		}

		int longerLength = longer.length();
		if (longerLength == 0)
			return 1.0;
		return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
	}

	private static int editDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		int[] costs = new int[s2.length() + 1];

		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0) {
					costs[j] = j;
				} else {
					if (j > 0) {
						int newValue = costs[j - 1];

						if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
							newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
						}

						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}

			if (i > 0)
				costs[s2.length()] = lastValue;
		}

		return costs[s2.length()];
	}

	public static void printSimilarity(String s, String t) {
		System.out.println(String.format("%.3f is the similarity between \"%s\" and \"%s\"", similarity1(s, t), s, t));
	}

	public static double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) {
			longer = s2;
			shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) {
			return 1.0; /* both strings have zero length */
		}
		return (longerLength - getLevenshteinDistance(longer, shorter)) / (double) longerLength;
	}

	public static int getLevenshteinDistance(String s, String t) {
		if (s == null || t == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}

		int n = s.length(); // length of s
		int m = t.length(); // length of t

		if (n == 0) {
			return m;
		} else if (m == 0) {
			return n;
		}

		if (n > m) {
			// swap the input strings to consume less memory
			String tmp = s;
			s = t;
			t = tmp;
			n = m;
			m = t.length();
		}

		int p[] = new int[n + 1]; // 'previous' cost array, horizontally
		int d[] = new int[n + 1]; // cost array, horizontally
		int _d[]; // placeholder to assist in swapping p and d

		// indexes into strings s and t
		int i; // iterates through s
		int j; // iterates through t

		char t_j; // jth character of t

		int cost; // cost

		for (i = 0; i <= n; i++) {
			p[i] = i;
		}

		for (j = 1; j <= m; j++) {
			t_j = t.charAt(j - 1);
			d[0] = j;

			for (i = 1; i <= n; i++) {
				cost = s.charAt(i - 1) == t_j ? 0 : 1;
				// minimum of cell to the left+1, to the top+1, diagonally left and up +cost
				d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
			}

			// copy current distance counts to 'previous row' distance counts
			_d = p;
			p = d;
			d = _d;
		}

		// our last action in the above loop was to switch d and p, so p now
		// actually has the most recent cost counts
		return p[n];
	}
}
