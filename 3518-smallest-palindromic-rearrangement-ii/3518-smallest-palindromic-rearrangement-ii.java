class Solution {
    private static final long CAP = 2_000_000L;

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        for (char ch : s.toCharArray()) count[ch - 'a']++;

        int[] half = new int[26];
        char mid = 0;
        boolean hasMid = false;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                mid = (char) ('a' + i);
                hasMid = true;
            }
            half[i] = count[i] / 2;
        }

        int halfLen = n / 2;
        long total = multinomial(half, halfLen);
        if (total < k) return "";

        long kk = k;
        int remaining = halfLen;
        StringBuilder sb = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                remaining--;
                long cnt = multinomial(half, remaining);
                if (cnt >= kk) {
                    sb.append((char) ('a' + c));
                    break;
                } else {
                    kk -= cnt;
                    half[c]++;
                    remaining++;
                }
            }
        }

        String halfStr = sb.toString();
        StringBuilder result = new StringBuilder();
        result.append(halfStr);
        if (hasMid) result.append(mid);
        result.append(new StringBuilder(halfStr).reverse());
        return result.toString();
    }

    private long multinomial(int[] counts, int total) {
        long result = 1;
        int remaining = total;
        for (int i = 0; i < 26; i++) {
            if (counts[i] == 0) continue;
            long c = comb(remaining, counts[i]);
            result *= c;
            if (result > CAP) return CAP + 1;
            remaining -= counts[i];
        }
        return result;
    }

    private long comb(int a, int b) {
        if (b < 0 || b > a) return 0;
        b = Math.min(b, a - b);
        long result = 1;
        for (int i = 1; i <= b; i++) {
            result = result * (a - b + i) / i;
            if (result > CAP) return CAP + 1;
        }
        return result;
    }
}