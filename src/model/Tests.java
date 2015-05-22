package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.StringReader;

import lexer.GmlLexer;

public class Tests {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(final String[] args) throws IOException {
    final TokenList prog = parseFile("intercubes.gml");// System.out.println(prog);
    prog.run();
  }

  private static TokenList parseFile(final String filename) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(Tests.class.getClassLoader()
        .getResourceAsStream(filename)));
    final StringBuilder sb = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null) {
      sb.append(line).append("\n");
    }
    final GmlLexer lexer = new GmlLexer();
    return lexer.parseTokenList(new PushbackReader(new StringReader(sb.toString())));

  }

  private static TokenList test1() {
    final TokenList prog = new TokenList();
    final TokenList reverse = new TokenList();
    prog.add(new GmlString("2 < 1?"));
    prog.add(new GmlOperator(Operator.print));
    reverse.add(new GmlBinder(new GmlIdentifier("x")));
    reverse.add(new GmlBinder(new GmlIdentifier("y")));
    reverse.add(new GmlIdentifier("x"));
    reverse.add(new GmlIdentifier("y"));

    prog.add(new GmlInteger(1));
    prog.add(new GmlInteger(2));
    prog.add(new GmlOperator(Operator.lessi));
    prog.add(new GmlOperator(Operator.print));

    prog.add(new GmlString("1 < 2?"));
    prog.add(new GmlOperator(Operator.print));
    prog.add(new GmlInteger(1));
    prog.add(new GmlInteger(2));
    prog.add(new GmlFunction(reverse));
    prog.add(new GmlOperator(Operator.apply));
    prog.add(new GmlOperator(Operator.lessi));
    prog.add(new GmlOperator(Operator.print));
    return prog;
  }

}
