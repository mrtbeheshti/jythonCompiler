package compiler;
import compiler.itemAttribute;
import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Scope{
    private Hashtable<String,itemAttribute> symbolTable;
    private Scope parent=null;
    private ArrayList<Scope> children;
    private int entryLine;

    public Scope(int line){
        this.symbolTable=new Hashtable<String, itemAttribute>();
        this.entryLine = line;
    }
    public Object[] getSymbolTableKeys(){
        return this.symbolTable.keySet().toArray();
    }
    public void setParent(Scope parent){
        this.parent=parent;
    }
    public Scope getParent(){
        return this.parent;
    }
    public void appendChild(Scope child){
        children.add(child);
    }
    public Scope getChild(int index){return this.children.get(index);}
    public void insert(String idefName , itemAttribute attributes){
        symbolTable.put(idefName,attributes);
    }
    public int getEntryLine(){
        return this.entryLine;
    }
    
}
