package model;

import java.util.Stack;

public class GmlStack {

  private static final boolean DEBUG = false;
  Stack<TokenGroup> stack;
  public GmlStack() {
    stack = new Stack<TokenGroup>();
  }
  public TokenGroup pop() {
    if (GmlStack.DEBUG) {
      System.out.println(" pop: " + stack.peek());
    }
    return stack.pop();
  }
  public void push(final TokenGroup t) {
    if (GmlStack.DEBUG) {
      System.out.println(" push: " + t);
    }
    stack.push(t);
  }
  public Object[] toArray() {
    return stack.toArray();
  }
  @Override
  public String toString() {
    return "Stack:" + stack.toString();
  }
}
