package compiler;

import java.util.LinkedList;
import java.util.List;

import gen.jythonParser.ParameterContext;

public class itemAttribute {
    private int line;
    private String name;
    private String structureType;
    private boolean isImport;
    private boolean isClass;
    private String classType;
    private List<String> classParents;
    private boolean isVariable;
    private String variableType;
    private boolean isMethod;
    private List<ParameterContext> parameterList;
    private String returnType;
    private boolean isDefined;
    private boolean isConstructor;
    private int parameteIndex;

    public itemAttribute(int line, String name,boolean isImport, boolean isClass, boolean isVariable, boolean isMethod, boolean isDefined, boolean isConstructor){
        this.line = line;
        this.name = name;
        this.isClass = isClass;
        this.isVariable = isVariable;
        this.isMethod = isMethod;
        this.isDefined = isDefined;
        this.isImport = isImport;
        this.isConstructor = isConstructor;
        if(isClass){
            classParents = new LinkedList<String>();
        }
    }

    
    public void setStructureType(String structureType){
        this.structureType = structureType;
    }
    public void setClassType(String classType){
        if(this.isClass)
            this.classType = classType;
    }
    public void setVariableType(String variableType){
        if(this.isVariable)
            this.variableType = variableType;
    }
    public void setParameterList(List<ParameterContext> params){
        if(this.isMethod || this.isConstructor)
            this.parameterList = params;
    }
    public void setReturnType(String returnType){
        if(this.isMethod)
            this.returnType = returnType;
    }
    public void setParameterIndex(int index){
        if(this.isVariable)
            this.parameteIndex = index;
    }
    public void appendParent(String parent){
        if(this.isClass){
            this.classParents.add(parent);
            
        }
    }

    

    public int getParameterIndex(){
        return this.parameteIndex;
    }
    public int getLine(){
        return this.line;
    }
    public String getname(){
        return this.name;
    }
    public boolean getIsImport(){
        return this.isImport;
    }
    public String getStructureType(){
        return this.structureType;
    }
    public boolean getIsClass(){
        return this.isClass;
    }
    public String getClassType(){
        return this.classType;
    }
    public boolean getIsVariable(){
        return this.isVariable;
    }
    public String getVariableType(){
        return this.variableType;
    }
    public boolean getIsMethod(){
        return this.isMethod;
    }
    public boolean getIsConstructor(){
        return this.isConstructor;
    }
    public List<ParameterContext> getParameterList(){
        return this.parameterList;
    }
    public String getReturnType(){
        return this.returnType;
    }
    public boolean getIsIsDefined(){
        return this.isDefined;
    }
}
