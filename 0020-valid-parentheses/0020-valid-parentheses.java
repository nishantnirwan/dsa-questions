class Solution {
    public boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();

        for(char ch : s.toCharArray()) {
            // Open Bracket
            if(ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            }
            else {
                // Closing Bracket
                if(st.isEmpty()) return false;

                if(ch == ')' && st.peek() != '(') return false;
                if(ch == ']' && st.peek() != '[') return false;
                if(ch == '}' && st.peek() != '{') return false;

                else {
                    st.pop();
                }
            }
        }
        if(st.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}