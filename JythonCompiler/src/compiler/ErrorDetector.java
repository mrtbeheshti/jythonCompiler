package compiler;

import java.util.LinkedList;
import java.util.Queue;

public class ErrorDetector {
    
    public static Scope duplicateMethodError(Scope child, String methodName, int line, int column){
        Scope temp = child;
        while(child != null){
            for (Object key : child.getSymbolTableKeys()){
                itemAttribute attrs = child.getsymbolTableValue((String)key);
                if(attrs.getStructureType().equals("Method")){
                    if(attrs.getName().equals(methodName)){
                        printError("type", String.valueOf(line), String.valueOf(column), "text");
                        temp.getsymbolTableValue(methodName).setName(methodName + "_" + String.valueOf(line));
                        return temp;
                    }
                }
            child = child.getParent();
            }
        }
        return temp;



        // Queue<Scope> fringe = new LinkedList<>();
        // LinkedList<String> visitedMethod = new LinkedList<>();
        // fringe.add(root);
        // while(! fringe.isEmpty()){
        //     Scope scope = fringe.poll();
        //     for(Scope child : scope.getChildrenArrayList()){
        //         fringe.add(child);
        //     }
        //     visitedMethod.clear();
        //     for (Object key : scope.getSymbolTableKeys()){
        //         itemAttribute attrs = scope.getsymbolTableValue((String)key);
        //         if(attrs.getStructureType().equals("Method")){
        //             if(visitedMethod.contains(attrs.getName())){                        
        //                 printError("",String.valueOf(attrs.getLine()),"","");
        //             }else{
        //                 visitedMethod.add(attrs.getName());
        //             }
        //         }
        //     }
        // }
    }

    public static Scope duplicateFieldError(Scope child, String methodName, int line, int column){
        Scope temp = child;
        while(child != null){
            for (Object key : child.getSymbolTableKeys()){
                itemAttribute attrs = child.getsymbolTableValue((String)key);
                if(attrs.getStructureType().equals("Method")){
                    if(attrs.getName().equals(methodName)){
                        printError("type", String.valueOf(line), String.valueOf(column), "text");
                        temp.getsymbolTableValue(methodName).setName(methodName + "_" + String.valueOf(line));
                        return temp;
                    }
                }
            child = child.getParent();
            }
        }
        return temp;




        // Queue<Scope> fringe = new LinkedList<>();
        // LinkedList<String> visitedField = new LinkedList<>();
        // fringe.add(root);
        // while(! fringe.isEmpty()){
        //     Scope scope = fringe.poll();
        //     for(Scope child : scope.getChildrenArrayList()){
        //         fringe.add(child);
        //     }
        //     visitedField.clear();
        //     for (Object key : scope.getSymbolTableKeys()){
        //         itemAttribute attrs = scope.getsymbolTableValue((String)key);
        //         if(attrs.getStructureType().contains("Field")){
        //             if(visitedField.contains(attrs.getName())){
        //                 printError("",String.valueOf(attrs.getLine()),"","");
        //             }else{
        //                 visitedField.add(attrs.getName());
        //             }
        //         }
        //     }
        // }

    }
    
    public static void notDefinedClassError(Scope child, String varName, int line, int column){
        while(child != null){
            for (Object key : child.getSymbolTableKeys()){
                itemAttribute attrs = child.getsymbolTableValue((String)key);
                if(attrs.getStructureType().equals("Class")){
                    if(attrs.getName().equals(varName)){
                        return;
                    }
                }
            child = child.getParent();
            }
        }
        printError("",String.valueOf(line),"",String.valueOf(column));
    }
    
    public static void notDefinedVariableError(Scope child, String varName, int line, int column){
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
        printError("",String.valueOf(line),"",String.valueOf(column));
    }

    private static void printError(String type, String line, String column, String text){
        System.out.println("Error" + type + " : in line " + line + ":" + column + " , " + text);
    }
}
