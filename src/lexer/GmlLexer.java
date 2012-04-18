package lexer;

import java.io.IOException;
import java.io.PushbackReader;

import model.GmlBinder;
import model.GmlBoolean;
import model.GmlFunction;
import model.GmlIdentifier;
import model.GmlInteger;
import model.GmlOperator;
import model.GmlReal;
import model.GmlString;
import model.Operator;
import model.TokenArray;
import model.TokenGroup;
import model.TokenList;

public class GmlLexer {
    private boolean inComment = false;
    private boolean inString;
    int c = -1;
    private final boolean DEBUG = true;
    public GmlLexer(){

    }

    public TokenList parseTokenList(PushbackReader r) throws IOException{
        TokenList l=new TokenList();

        while((c = r.read()) != -1){
            if(shouldSkip(c))
                continue;
            else if(c == '"')
                l.add(parseString(r));
            else if(Character.isDigit(c) || c == '-'){
                r.unread(c);
                l.add(parseNumber(r));
            }else if(c == '{')
                l.add(parseFunction(r));
            else if(c == '}')
                break;
            else if(c == '[')
                l.add(parseArray(r));
            else if(c == ']')
                break;
            else if(c == '/')
                l.add(parseBinder(r));
            else{
                r.unread(c);
                String string = readString(r);
                if(string.equals("true"))
                    l.add(new GmlBoolean(true));
                else
                if(string.equals("false"))
                    l.add(new GmlBoolean(false));
                else if(string.equals("if")){
                    l.add(new GmlOperator(Operator.If));
                }else
                try{
                    Operator op = Operator.valueOf(string);
                    l.add(new GmlOperator(op));
                }catch(IllegalArgumentException ex){
                    //has to be an identifier
                    l.add(new GmlIdentifier(string));
                }

            }
        }
        return l;
    }

    private GmlBinder parseBinder(PushbackReader r) throws IOException {
        return new GmlBinder(parseIdentifier(r));
    }

    private String readString(PushbackReader r) throws IOException{
        StringBuilder sb = new StringBuilder();
        while((c = r.read()) != -1)
            if(shouldSkip(c))
                break;
            else if(Character.isJavaIdentifierPart(c) || c == '-')
                sb.append((char)c);
            else{
                r.unread(c);
                break;
            }
        return sb.toString();
    }
    private GmlIdentifier parseIdentifier(PushbackReader r) throws IOException {
        return new GmlIdentifier(readString(r));
    }

    private TokenArray parseArray(PushbackReader r) throws IOException {
        return new TokenArray(parseTokenList(r));
    }

    private GmlFunction parseFunction(PushbackReader r) throws IOException {
        return new GmlFunction(parseTokenList(r));
    }

    private TokenGroup parseNumber(PushbackReader r) throws IOException {
        StringBuilder sb =new StringBuilder();
        inString = true;
        while((c = r.read()) != -1)
            if(c == 'e' || c == 'E' || c == '-' || c == '.' || Character.isDigit(c))
                sb.append((char)c);
            else{
                r.unread(c);
                break;
            }
        inString = false;
        try{
            return new GmlInteger(Integer.parseInt(sb.toString()));
        }catch(NumberFormatException e){}
        try{
            return new GmlReal(Double.parseDouble(sb.toString()));
        }catch(NumberFormatException e){}

        System.err.println("Couldn't parse number: "+sb);
        System.exit(1);
        return null;
    }

    private GmlString parseString(PushbackReader r) throws IOException {
        StringBuilder sb = new StringBuilder();
        inString = true;
        while((c = r.read()) != -1)
            if(c == '"')
                break;
            else
                sb.append((char)c);
        inString = false;
        return new GmlString(sb.toString());
    }

    private boolean shouldSkip(int c) {
        if((c == '%' || c=='#') && !inString )
            inComment = true;
        if( inComment && c == '\n')
            inComment = false;
        return inComment || c ==' ' || c == '\t' || c == '\r' || c == '\n';
    }
    @Override
    public String toString() {
    	return String.format("Lexer [%c,inComment=%b,inString=%b]",c,this.inComment, this.inString,this);
    }
}
