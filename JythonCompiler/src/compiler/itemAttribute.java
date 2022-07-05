package compiler;

public class itemAttribute {
    private int line;
    private String name;
    private String structureType;
    private boolean isClass;
    private String classType;
    private boolean isVariable;
    private String variableType;
    private boolean isMethod;
    private String returnType;
    private boolean isDefined;

    public void setLine(int line){
        this.line = line;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setStructureType(String structureType){
        this.structureType = structureType;
    }
    public void setIsClass(boolean isClass){
        this.isClass = isClass;
    }
    public void setClassType(String classType){
        this.classType = classType;
    }
    public void setIsVariable(boolean isVariable){
        this.isVariable = isVariable;
    }
    public void setVariableType(String variableType){
        this.variableType = variableType;
    }
    public void setIsMethod(boolean isMethod){
        this.isMethod = isMethod;
    }
    public void setReturnType(String returnType){
        this.returnType = returnType;
    }
    public void setIsIsDefined(boolean isDefined){
        this.isDefined = isDefined;
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
    public String getReturnType(String returnType){
        return this.returnType;
    }
    public boolean getIsIsDefined(boolean isDefined){
        return this.isDefined;
    }
}
