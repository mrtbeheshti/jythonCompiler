package compiler;
import compiler.itemAttribute;
import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Hashtable;

public class Scope{
    private Hashtable<String,Object> symbol_table;
    private Scope parent=null;

    public Scope(){
        this.symbol_table=new Hashtable<String, Object>();
    }
    public Scope setParent(Scope parent){
        this.parent=parent;
        return this;
    }
    public Scope getParent(Scope parent){
        return this.parent;
    }
    public void insert(String idefName , itemAttribute attributes){
        symbol_table.put(idefName,attributes);
    }
}
