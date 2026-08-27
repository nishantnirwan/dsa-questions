class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {
            int[] temp = cnt.clone();

            for (int j = 0; j < i; j++) {
                if (--temp[target.charAt(j) - 'a'] < 0) {
                    temp = null;
                    break;
                }
            }

            if (temp == null) continue;

            int cur = target.charAt(i) - 'a';

            for (int c = cur + 1; c < 26; c++) {
                if (temp[c] > 0) {
                    temp[c]--;

                    StringBuilder ans = new StringBuilder(target.substring(0, i));
                    ans.append((char) ('a' + c));

                    for (int x = 0; x < 26; x++) {
                        while (temp[x]-- > 0) {
                            ans.append((char) ('a' + x));
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}