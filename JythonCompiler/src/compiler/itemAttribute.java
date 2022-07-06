package compiler;

import java.util.List;

import gen.jythonParser.ParameterContext;

public class itemAttribute {
    private int line;
    private String name;
    private String structureType;
    private boolean isClass;
    private String classType;
    private boolean isVariable;
    private String variableType;
    private boolean isMethod;
    private List<ParameterContext> parameterList;
    private String returnType;
    private boolean isDefined;

    public itemAttribute(int line, String name,boolean isClass, boolean isVariable, boolean isMethod, boolean isDefined){
        this.line = line;
        this.name = name;
        this.isClass = isClass;
        this.isVariable = isVariable;
        this.isMethod = isMethod;
        this.isDefined = isDefined;
    }

    
    public void setStructureType(String structureType){
        this.structureType = structureType;
    }
    public void setClassType(String classType){
        this.classType = classType;
    }
    public void setVariableType(String variableType){
        this.variableType = variableType;
    }
    public void setParameterList(List<ParameterContext> params){
        this.parameterList = params;
    }
    public void setReturnType(String returnType){
        this.returnType = returnType;
    }

    
    public int getLine(int line){
        return this.line;
    }
    public String getname(String name){
        return this.name;
    }
    public String getStructureType(String structureType){
        return this.structureType;
    }
    public boolean getIsClass(boolean isClass){
        return this.isClass;
    }
    public String getClassType(String classType){
        return this.classType;
    }
    public boolean getIsVariable(boolean isVariable){
        return this.isVariable;
    }
    public String getVariableType(String variableType){
        return this.variableType;
    }
    public boolean getIsMethod(boolean isMethod){
        return this.isMethod;
    }
    public List<ParameterContext> getParameterList(){
        return this.parameterList;
    }
    public String getReturnType(String returnType){
        return this.returnType;
    }
    public boolean getIsIsDefined(boolean isDefined){
        return this.isDefined;
    }
}
