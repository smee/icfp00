package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TokenList {
  private static final boolean DEBUG = false;
  LinkedList<TokenGroup> tokengroups;

  public TokenList() {
    this.tokengroups = new LinkedList<TokenGroup>();
  }
  public void add(final TokenGroup tg) {
    tokengroups.add(tg);
  }
  public void addAll(final List<TokenGroup> tl) {
    tokengroups.addAll(tl);
  }
  public List<TokenGroup> getGroups() {
    return tokengroups;
  }
  public Iterator<TokenGroup> iterator() {
    return tokengroups.iterator();
  }
  public void run() {
    final GmlStack s = new GmlStack();
    final GmlEnvironment e = new GmlEnvironment();

    for (final TokenGroup tg : tokengroups) {
      if (TokenList.DEBUG) {
        System.out.println("eval: " + tg);
      }
      tg.evalToken(s, e);
    }
  }
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (final TokenGroup tg : tokengroups) {
      sb.append(tg.toString()).append(" ");
    }
    return sb.toString();

  }
}
