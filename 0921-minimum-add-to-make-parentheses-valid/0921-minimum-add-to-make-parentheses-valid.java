class Solution {
    public int minAddToMakeValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        
        int count = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                st.push(ch);
            }
            else {
                // ch = )
                if(st.isEmpty()) {
                    count++;
                }
                else {
                    // opening bracket is present in stack
                    st.pop();
                }
            }
        }
        int finalAns = st.size() + count;

        return finalAns;
    }
}