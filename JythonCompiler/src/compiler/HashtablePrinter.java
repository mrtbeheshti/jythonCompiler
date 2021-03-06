package compiler;

import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.Queue;

public class HashtablePrinter implements jythonListener{
    private Scope currentScope;
    private int paramIndex = 0;
    private boolean isParam = false;

    @Override
    public void enterProgram(jythonParser.ProgramContext ctx) {
        currentScope=new Scope(1, "Program");
    }

    @Override
    public void exitProgram(jythonParser.ProgramContext ctx) {
        String print_str = "";
        Queue<Scope> scopes = new LinkedList<>();
        scopes.add(this.currentScope);
        while(scopes.size()>0){
            Scope scope_to_print = scopes.poll();
            for(int i=0;i<scope_to_print.childrenCount();i++){
                scopes.add(scope_to_print.getChild(i));
            }
            print_str += scope_to_print.toString();
        }
        
        
        System.out.println(print_str);
    }

    @Override
    public void enterImportclass(jythonParser.ImportclassContext ctx) {

        itemAttribute attrs = new itemAttribute(ctx.imprtedClass.getLine(), ctx.imprtedClass.getText(),
        true,false,false,false,false,false);
        
        attrs.setStructureType("import");

        currentScope.insert("import_"+ctx.imprtedClass.getText(),attrs);
    }

    @Override
    public void exitImportclass(jythonParser.ImportclassContext ctx) {}

    @Override
    public void enterClassDef(jythonParser.ClassDefContext ctx) {
        itemAttribute attrs = new itemAttribute(ctx.start.getLine(), ctx.className.getText(),
        false,true,false,false,false,false);
        
        attrs.setStructureType("Class");

        for(int i=1;i<ctx.CLASSNAME().toArray().length;i++)
            attrs.appendParent(ctx.CLASSNAME(i).getText());

        currentScope.insert("Class_"+ctx.className.getText(),attrs);

        Scope child=new Scope(ctx.start.getLine(), ctx.className.getText());
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        
    }

    @Override
    public void exitClassDef(jythonParser.ClassDefContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterVarDec(jythonParser.VarDecContext ctx) {
        itemAttribute attrs = new itemAttribute(ctx.start.getLine(), ctx.name.getText(),
        false,false,true,false,false,false);
        attrs.setVariableType(ctx.type.getText());
        currentScope.insert("Field_"+ctx.name.getText(),attrs);
        if(isParam){
            attrs.setStructureType("Parameter");
            itemAttribute parentAttribute = null;
            String parentName = this.currentScope.getName();
            String method = "Method_"+parentName;

            String constructor = "Constructor_"+parentName;
            if (this.currentScope.getParent().getsymbolTableValue(method) !=null)
                parentAttribute = this.currentScope.getParent().getsymbolTableValue(method);
            else
                parentAttribute = this.currentScope.getParent().getsymbolTableValue(constructor);
            parentAttribute.addParameter(ctx.type.getText());
        }else{

            if(ctx.type.getText().contains("class")){
                attrs.setStructureType("ClassField");
            }else{
                String scopeName = this.currentScope.getName();
                Scope item = this.currentScope.getParent();
                if(item.getsymbolTableValue("Class_"+scopeName) != null){
                    attrs.setStructureType("Field");
                }
                else
                    attrs.setStructureType("MethodField");
            }

        }
        ErrorDetector.notDefinedVariableError(currentScope, ctx.name.getText(), ctx.start.getLine(), 8);
        ErrorDetector.duplicateFieldError(currentScope, ctx.name.getText(), ctx.start.getLine(),12);
    }

    @Override
    public void exitVarDec(jythonParser.VarDecContext ctx) {}

    @Override
    public void enterArrayDec(jythonParser.ArrayDecContext ctx) {
        itemAttribute attrs = new itemAttribute(ctx.start.getLine(), ctx.name.getText(),
        false,false,true,false,false,false);
        attrs.setVariableType(ctx.type.getText());
        if(ctx.CLASSNAME() != null){
            attrs.setStructureType("ClassArrayField");
        }else if(ctx.TYPE() != null){
            attrs.setStructureType("ArrayField");
        }

        currentScope.insert("Field_"+ctx.name.getText(),attrs);
    }

    @Override
    public void exitArrayDec(jythonParser.ArrayDecContext ctx) {}

    @Override
    public void enterMethodDec(jythonParser.MethodDecContext ctx) {
        itemAttribute attrs = new itemAttribute(ctx.start.getLine(), ctx.name.getText(),
        false,false,false,true,false,false);

        attrs.setStructureType("Method");
        String type = (ctx.type!=null)? ctx.type.getText() : "void";
        attrs.setReturnType(type);

        currentScope.insert("Method_"+ctx.name.getText(),attrs);
        paramIndex = 0;

        Scope child=new Scope(ctx.start.getLine(), ctx.name.getText());
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        ErrorDetector.duplicateMethodError(currentScope, ctx.name.getText(), ctx.start.getLine(), 13);
    }

    @Override
    public void exitMethodDec(jythonParser.MethodDecContext ctx) {
        currentScope = currentScope.getParent();
        paramIndex = 0;
    }

    @Override
    public void enterConstructor(jythonParser.ConstructorContext ctx) {
        itemAttribute attrs = new itemAttribute(ctx.start.getLine(), ctx.type.getText(),
        false,false,false,false,false,true);
        
        attrs.setStructureType("Constructor");
        
        currentScope.insert("Constructor_"+ctx.type.getText(),attrs);
        paramIndex = 0;

        Scope child=new Scope(ctx.start.getLine(), ctx.type.getText());
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
    }

    @Override
    public void exitConstructor(jythonParser.ConstructorContext ctx) {
        currentScope = currentScope.getParent();
        paramIndex = 0;
    }

    @Override
    public void enterParameter(jythonParser.ParameterContext ctx) {
        isParam = true;
    }

    @Override
    public void exitParameter(jythonParser.ParameterContext ctx) {
        paramIndex++;
        isParam = false;
    }

    @Override
    public void enterIf_statment(jythonParser.If_statmentContext ctx) {
        String name = "";
        switch(currentScope.getName()){
            case "If":
            case "While":
            case "For": name= "nested"; break;
            default: name = "If";   
        }
        Scope child=new Scope(ctx.start.getLine(), name);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
    }

    @Override
    public void exitIf_statment(jythonParser.If_statmentContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterWhile_statment(jythonParser.While_statmentContext ctx) {
        String name = "";
        switch(currentScope.getName()){
            case "If":
            case "While":
            case "For": name= "nested"; break;
            default: name = "While";   
        }
        Scope child=new Scope(ctx.start.getLine(), name);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
    }

    @Override
    public void exitWhile_statment(jythonParser.While_statmentContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        String name = "";
        switch(currentScope.getName()){
            case "If":
            case "While":
            case "For": name= "nested"; break;
            default: name = "If";   
        }
        Scope child=new Scope(ctx.start.getLine(), name);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
    }

    @Override
    public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterFor_statment(jythonParser.For_statmentContext ctx) {
        String name = "";
        switch(currentScope.getName()){
            case "If":
            case "While":
            case "For": name= "nested"; break;
            default: name = "For";   
        }
        Scope child=new Scope(ctx.start.getLine(), name);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
    }


//////////////////////////////////////////////////////////////////////////////////

    @Override
    public void exitFor_statment(jythonParser.For_statmentContext ctx) {
        currentScope = currentScope.getParent();
    }

    
    @Override
    public void enterClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void exitClass_body(jythonParser.Class_bodyContext ctx) {

    }

    
    @Override
    public void enterStatement(jythonParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(jythonParser.StatementContext ctx) {

    }

    @Override
    public void enterReturn_statment(jythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void exitReturn_statment(jythonParser.Return_statmentContext ctx) {

    }

    @Override
    public void enterCondition_list(jythonParser.Condition_listContext ctx) {

    }

    @Override
    public void exitCondition_list(jythonParser.Condition_listContext ctx) {

    }

    @Override
    public void enterCondition(jythonParser.ConditionContext ctx) {

    }

    @Override
    public void exitCondition(jythonParser.ConditionContext ctx) {

    }


    @Override
    public void enterPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(jythonParser.Print_statmentContext ctx) {

    }


    @Override
    public void enterMethod_call(jythonParser.Method_callContext ctx) {

    }

    @Override
    public void exitMethod_call(jythonParser.Method_callContext ctx) {

    }

    @Override
    public void enterAssignment(jythonParser.AssignmentContext ctx) {

    }

    @Override
    public void exitAssignment(jythonParser.AssignmentContext ctx) {

    }

    @Override
    public void enterExp(jythonParser.ExpContext ctx) {

    }

    @Override
    public void exitExp(jythonParser.ExpContext ctx) {

    }

    @Override
    public void enterPrefixexp(jythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void exitPrefixexp(jythonParser.PrefixexpContext ctx) {

    }

    @Override
    public void enterArgs(jythonParser.ArgsContext ctx) {

    }

    @Override
    public void exitArgs(jythonParser.ArgsContext ctx) {

    }

    @Override
    public void enterExplist(jythonParser.ExplistContext ctx) {

    }

    @Override
    public void exitExplist(jythonParser.ExplistContext ctx) {

    }

    @Override
    public void enterArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void exitArithmetic_operator(jythonParser.Arithmetic_operatorContext ctx) {

    }

    @Override
    public void enterRelational_operators(jythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void exitRelational_operators(jythonParser.Relational_operatorsContext ctx) {

    }

    @Override
    public void enterAssignment_operators(jythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void exitAssignment_operators(jythonParser.Assignment_operatorsContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
