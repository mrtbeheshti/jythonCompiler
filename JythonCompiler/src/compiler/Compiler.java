package compiler;

import gen.jythonLexer;
import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Compiler {
    public static void main(String[] args) throws IOException {
        String path = "D:\\mr\\Compiler\\Proj1\\Jython\\JythonCompiler\\sample\\test.jy";
        CharStream stream = CharStreams.fromFileName(path);
        jythonLexer lexer=new jythonLexer(stream);
        TokenStream tokens = new CommonTokenStream(lexer);
        jythonParser parser = new jythonParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program();
        ParseTreeWalker walker=new ParseTreeWalker();
        jythonListener listener=new ProgramPrinter();

        walker.walk(listener,tree);

    }
}
