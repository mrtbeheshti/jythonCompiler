package compiler;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class Scope{
    private String name;
    private Hashtable<String,itemAttribute> symbolTable;
    private Scope parent=null;
    private ArrayList<Scope> children;
    private int entryLine;

    public Scope(int line, String name){
        this.symbolTable=new Hashtable<String, itemAttribute>();
        this.children = new ArrayList<>();
        this.entryLine = line;
        this.name = name;
    }
    public Object[] getSymbolTableKeys(){
        return this.symbolTable.keySet().toArray();
    }
    public itemAttribute getsymbolTableValue(String key){
        return this.symbolTable.get(key);
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
    public String getName(){
        return this.name;
    }
    public String toString(){
        
    }
    
}
