package compiler;

import java.util.LinkedList;
import java.util.Queue;

public class ErrorDetector {
    
    public static void duplicateMethodError(Scope root){
        Queue<Scope> fringe = new LinkedList<>();
        LinkedList<String> visitedMethod = new LinkedList<>();
        fringe.add(root);
        while(! fringe.isEmpty()){
            Scope scope = fringe.poll();
            for(Scope child : scope.getChildrenArrayList()){
                fringe.add(child);
            }
            visitedMethod.clear();
            for (Object key : scope.getSymbolTableKeys()){
                itemAttribute attrs = scope.getsymbolTableValue((String)key);
                if(attrs.getStructureType().equals("Method")){
                    if(visitedMethod.contains(attrs.getName())){
                        printError();
                    }else{
                        visitedMethod.add(attrs.getName());
                    }
                }
            }
        }
    }

    public static void duplicateFieldError(Scope root){
        Queue<Scope> fringe = new LinkedList<>();
        LinkedList<String> visitedField = new LinkedList<>();
        fringe.add(root);
        while(! fringe.isEmpty()){
            Scope scope = fringe.poll();
            for(Scope child : scope.getChildrenArrayList()){
                fringe.add(child);
            }
            visitedField.clear();
            for (Object key : scope.getSymbolTableKeys()){
                itemAttribute attrs = scope.getsymbolTableValue((String)key);
                if(attrs.getStructureType().contains("Field")){
                    if(visitedField.contains(attrs.getName())){
                        printError();
                    }else{
                        visitedField.add(attrs.getName());
                    }
                }
            }
        }

    }
    
    public static void notDefinedClassError(Scope child, String className){
        while(child != null){
            for (Object key : child.getSymbolTableKeys()){
                itemAttribute attrs = child.getsymbolTableValue((String)key);
                if(attrs.getStructureType().equals("Class")){
                    if(attrs.getName().equals(className)){
                        return;
                    }
                }
            child = child.getParent();
            }
        }
        printError();
    }
    
    public static void notDefinedVariableError(Scope child, String varName){
        while(child != null){
            for (Object key : child.getSymbolTableKeys()){
                itemAttribute attrs = child.getsymbolTableValue((String)key);
                if(attrs.getStructureType().contains("Field")){
                    if(attrs.getName().equals(varName)){
                        return;
                    }
                }
            child = child.getParent();
            }
        }
        printError();
    }

    private static String printError(String type, String line, String column, String text){
        System.out.println("Error" + type + " : in line " + line + ":" + column + " , " + text;);
    }
}
