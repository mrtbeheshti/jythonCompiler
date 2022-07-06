package compiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import gen.jythonParser.ParameterContext;
import org.stringtemplate.v4.ST;

public class itemAttribute {
    private int line;
    private String name;
    private String structureType;
    private boolean isImport;
    private boolean isClass;
    private String classType;
    private ArrayList<String> classParents;
    private boolean isVariable;
    private String variableType;
    private boolean isMethod;
    private ArrayList<String> parameterList;
    private String returnType;
    private boolean isDefined;
    private boolean isConstructor;

    public itemAttribute(int line, String name,boolean isImport, boolean isClass, boolean isVariable, boolean isMethod, boolean isDefined, boolean isConstructor){
        this.line = line;
        this.name = name;
        this.isClass = isClass;
        this.isVariable = isVariable;
        this.isMethod = isMethod;
        this.isDefined = isDefined;
        this.isImport = isImport;
        this.isConstructor = isConstructor;
        if(isMethod || isConstructor){
            this.parameterList = new ArrayList<String>();
        }
        if(isClass){
            this.classParents = new ArrayList<String>();
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
    public void addParameter(String param){
        this.parameterList.add(param);
    }
    public void setParameterList(ArrayList<String> params){
        if(this.isMethod || this.isConstructor)
            this.parameterList = params;
    }
    public void setReturnType(String returnType){
        if(this.isMethod)
            this.returnType = returnType;
    }
    public void appendParent(String parent){
        if(this.isClass){
            this.classParents.add(parent);
            
        }
    }
    public void setName(String name){
        this.name = name;
    }

    

    public int getLine(){
        return this.line;
    }
    public String getName(){
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
    public List<String> getParameterList(){
        return this.parameterList;
    }
    public String getReturnType(){
        return this.returnType;
    }
    public boolean getIsIsDefined(){
        return this.isDefined;
    }
    public String toString(){
        String ret_str= this.structureType +" (name: " + this.name + ") " ;
        if(this.isClass){
            ret_str += "(parent: " ;
            for (int i=0; i< this.classParents.size(); i++){
                ret_str += this.classParents.get(i);
                if(i<this.classParents.size())
                    ret_str += ", ";
            }
            ret_str += ")";
        }
        else if (this.isConstructor){
            ret_str += "[parameter list: ";
            for (int i=0; i<this.parameterList.size();i++){
                ret_str += "[type: " + this.parameterList.get(i) + ", index: " + i+1 +"]" ;
            }
            ret_str+="]";
        }
        else if (this.isImport) {
        }
        else if (this.isMethod) {
          ret_str += "(return type: [";
          switch (this.returnType){
              case "int":
              case "float":
              case "string":
              case "bool": ret_str += this.returnType; break;
              default: ret_str += "class type= " + this.returnType;
          }
          ret_str += "])";
        }
        else if (this.isVariable) {
            ret_str += " (type: [";
            switch (this.variableType){
                case "int":
                case "float":
                case "string":
                case "bool": ret_str += this.variableType; break;
                default: ret_str += "type= " + this.variableType;
            }
            ret_str +="], [isDefined: " + (this.isDefined?"True":"False") + "])";
        }
        return ret_str;
    }
}
