package compiler;

import compiler.itemAttribute;
import compiler.Scope;
import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class HashtablePrinter implements jythonListener{
    private Scope currentScope;
    private int tempLine = 0;

    @Override
    public void enterProgram(jythonParser.ProgramContext ctx) {
        currentScope=new Scope(tempLine);
    }

    @Override
    public void exitProgram(jythonParser.ProgramContext ctx) {

    }

    @Override
    public void enterImportclass(jythonParser.ImportclassContext ctx) {
        Scope new_child=new Scope(tempLine);
        new_child.setParent(currentScope);
        currentScope.appendChild(new_child);
        currentScope=new_child;
        currentScope.insert("import_"+ctx.imprtedClass.getText(),null);
    }

    @Override
    public void exitImportclass(jythonParser.ImportclassContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterClassDef(jythonParser.ClassDefContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Class_"+ctx.className.getText(),null);
    }

    @Override
    public void exitClassDef(jythonParser.ClassDefContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void exitClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(jythonParser.VarDecContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Field_"+ctx.name.getText(),null);
    }

    @Override
    public void exitVarDec(jythonParser.VarDecContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterArrayDec(jythonParser.ArrayDecContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Field_"+ctx.name.getText(),null);
    }

    @Override
    public void exitArrayDec(jythonParser.ArrayDecContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterMethodDec(jythonParser.MethodDecContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Method_"+ctx.name.getText(),null);
    }

    @Override
    public void exitMethodDec(jythonParser.MethodDecContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterConstructor(jythonParser.ConstructorContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        String className = "";
        for(Object key : currentScope.getParent().getSymbolTableKeys()){
            String str = (String)key;
            if (str.contains("lass")){
                className = str.substring(str.indexOf("_")+1, str.length());
                break;
            }
        }
        currentScope.insert("Constructor_"+className,null);
    }

    @Override
    public void exitConstructor(jythonParser.ConstructorContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterParameter(jythonParser.ParameterContext ctx) {

    }

    @Override
    public void exitParameter(jythonParser.ParameterContext ctx) {

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
    public void enterIf_statment(jythonParser.If_statmentContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Method_"+ctx.name.getText(),null);
    }

    @Override
    public void exitIf_statment(jythonParser.If_statmentContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterWhile_statment(jythonParser.While_statmentContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Method_"+ctx.name.getText(),null);
    }

    @Override
    public void exitWhile_statment(jythonParser.While_statmentContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Method_"+ctx.name.getText(),null);
    }

    @Override
    public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        currentScope = currentScope.getParent();
    }

    @Override
    public void enterPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(jythonParser.For_statmentContext ctx) {
        Scope child=new Scope(tempLine);
        child.setParent(currentScope);
        currentScope.appendChild(child);
        currentScope=child;
        currentScope.insert("Method_"+ctx.name.getText(),null);
    }

    @Override
    public void exitFor_statment(jythonParser.For_statmentContext ctx) {
        currentScope = currentScope.getParent();
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
