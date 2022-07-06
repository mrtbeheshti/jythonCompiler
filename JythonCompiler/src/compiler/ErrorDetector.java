package compiler;

import java.util.LinkedList;
import java.util.Queue;

public class ErrorDetector {
    
    private static Scope root;

    public static void detect(Scope root){
        ErrorDetector.root = root;
        duplicateMethodError();
        duplicateFieldError();
        notDefinedClassError();
        notDefinedVariableError();
    }
    private static void duplicateMethodError(){
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

    private static void duplicateFieldError(){
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
    
    private static void notDefinedClassError(){
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
    
    private static void notDefinedVariableError(){
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

    private static void printError(){

    }
}
