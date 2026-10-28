class Solution {
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<String> stringStack = new ArrayDeque<>();

        int num = 0;
        StringBuilder curr = new StringBuilder();

        for(char ch : s.toCharArray()) {
            // Case 1
            if(Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            // Case 2
            else if(ch == '[') {
                countStack.push(num);
                stringStack.push(curr.toString());
                num = 0;
                curr = new StringBuilder();
            }

            // Case 3
            else if(ch == ']') {
                int repeatCount = countStack.pop();
                StringBuilder prev = new StringBuilder(stringStack.pop());

                for(int i = 1; i <= repeatCount; i++) {
                    prev.append(curr);
                }
                curr = prev;
            }
            
            // Case 4 --> ch -> alphabet string
            else {
                curr.append(ch);
            }
        }
        return curr.toString();
    }
}