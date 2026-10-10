class Solution {
    public int longestValidParentheses(String s) {
        Deque<Integer> st = new ArrayDeque<>();
        int maxLen = 0;
        st.push(-1);

        for(int i = 0 ; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == '(') {
                st.push(i);
            }

            else {
                st.pop();
                if(st.isEmpty()) {
                    st.push(i);
                }
                else {
                    int currLen = i - st.peek();

                    maxLen = Math.max(maxLen, currLen);
                }
            }
        }
        return maxLen;
    }
}