/**
 * Created by s1309096 on 08/02/17.
 */
import com.mu.*;
import org.antlr.v4.runtime.*;
import java.io.File;


public class main {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRFileStream(args[0]);
        ANTLRInputStream input2 = new ANTLRInputStream("");
        MuGrammarLexer lexer = new MuGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MuGrammarParser parser = new MuGrammarParser(tokens);
        parser.setBuildParseTree(true);
        RuleContext tree = parser.form();
        //tree.inspect(parser); // show in gui
        //tree.save(parser, "/tmp/R.ps"); // Generate postscript
        System.out.println(tree.toStringTree(parser));
    }
}
