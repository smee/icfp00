package model;

public class GmlFunction extends TokenGroup {

  private static final boolean DEBUG = false;
  private final TokenList tl;
  private final GmlEnvironment env;

  public GmlFunction(final TokenList tl) {
    this.tl = tl;
    this.env = null;
  }
  private GmlFunction(final GmlFunction fun, final GmlEnvironment env) {
    this.tl = new TokenList();
    tl.addAll(fun.tl.getGroups());
    this.env = env;
  }

  @Override
  public void evalToken(final GmlStack stack, final GmlEnvironment env) {
    stack.push(new GmlFunction(this, new GmlEnvironment(env)));
  }

  public void executeFunction(final GmlStack s) {
    final GmlEnvironment env = new GmlEnvironment(this.env);
    for (final TokenGroup g : tl.getGroups()) {
      if (GmlFunction.DEBUG) {
        System.out.println("funeval: " + g);
      }
      g.evalToken(s, env);
    }
  }
  public boolean isConstant(){
	  for(TokenGroup tg:tl){
		  if(tg instanceof GmlIdentifier)
			  return false;
		  else if(tg instanceof GmlFunction && !((GmlFunction)tg).isConstant())
			  return false;
	  }
	  return true;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("{ ");
    for (final TokenGroup tg : tl.getGroups()) {
      sb.append(tg).append(" ");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append(" }");
    sb.append(isConstant()?"/*constant!*/":"");
    return sb.toString();
  }
}
