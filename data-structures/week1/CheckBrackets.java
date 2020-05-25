import java.util.Scanner;
import java.util.Stack;

public class CheckBrackets {
    private static boolean matchBrackets(char a, char b) {
	return a == '(' ? b == ')' : (a == '[' ? b == ']' : (a == '{' ? b == '}' : false));
    }

    private static int checkBrackets(String s) {
	int N = s.length();
	Stack<Integer> stack = new Stack<>();
	for (int i = 0; i < N; i++) {
	    char c = s.charAt(i);
	    if (c == '(' || c == '[' || c == '{') {
		stack.push(i);
	    } else if (c == ')' || c == ']' || c == '}') {
		if (stack.empty() || !matchBrackets(s.charAt(stack.pop()), c))
		    return i + 1;
	    }
	}

	int unMatchedOpeningIndex = -1;
	while (!stack.empty()) {
	    unMatchedOpeningIndex = stack.pop();
	}
	if (unMatchedOpeningIndex != -1)
	    return unMatchedOpeningIndex + 1;

	System.out.println("Success");
	return 0;
    }

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	String s = in.next();
	int result = checkBrackets(s);
	if (result > 0)
	    System.out.println(result);
	in.close();
    }
}
