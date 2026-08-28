class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        char mid = 0;
        int odd = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) != 0) {
                odd++;
                mid = (char) ('a' + i);
            }
        }

        if (odd > 1) return "";

        int[] halfCnt = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        int[] rem = halfCnt.clone();
        boolean possible = true;

        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';

            if (rem[c] == 0) {
                possible = false;
                break;
            }

            rem[c]--;
        }

        if (possible) {
            String left = target.substring(0, half);
            String ans = build(left, mid);

            if (ans.compareTo(target) > 0) {
                return ans;
            }
        }

        for (int i = half - 1; i >= 0; i--) {
            rem = halfCnt.clone();
            boolean valid = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (rem[c] == 0) {
                    valid = false;
                    break;
                }

                rem[c]--;
            }

            if (!valid) continue;

            int cur = target.charAt(i) - 'a';

            for (int c = cur + 1; c < 26; c++) {
                if (rem[c] == 0) continue;

                rem[c]--;

                StringBuilder left = new StringBuilder(target.substring(0, i));
                left.append((char) ('a' + c));

                for (int x = 0; x < 26; x++) {
                    while (rem[x] > 0) {
                        left.append((char) ('a' + x));
                        rem[x]--;
                    }
                }

                return build(left.toString(), mid);
            }
        }

        return "";
    }

    private String build(String left, char mid) {
        return left + (mid == 0 ? "" : String.valueOf(mid))
                + new StringBuilder(left).reverse();
    }
}