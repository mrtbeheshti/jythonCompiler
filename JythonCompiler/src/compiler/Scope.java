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
    private Hashtable<String,itemAttribute> symbol_table;
    private Scope parent=null;
    private ArrayList<Scope> children;

    public Scope(){
        this.symbol_table=new Hashtable<String, itemAttribute>();
    }
    public void setParent(Scope parent){
        this.parent=parent;
    }
    public Scope getParent(Scope parent){
        return this.parent;
    }
    public void appendChild(Scope child){
        children.add(child);
    }
    public Scope getChild(int index){return this.children.get(index);}
    public void insert(String idefName , itemAttribute attributes){
        symbol_table.put(idefName,attributes);
    }
}
