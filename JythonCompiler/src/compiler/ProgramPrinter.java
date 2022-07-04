package compiler;

import gen.jythonListener;
import gen.jythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;


public class ProgramPrinter implements jythonListener {
    private int whiteSpaces =0;
    private boolean isParameter=false;
    @Override
    public void enterProgram(jythonParser.ProgramContext ctx) {
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        this.whiteSpaces +=4;
        System.out.println("start program{");
    }

    @Override
    public void exitProgram(jythonParser.ProgramContext ctx) {
        this.whiteSpaces -=4;
        for (int i=0;i<this.whiteSpaces;i++)
            System.out.print(" ");
        System.out.println("}");
    }

    @Override
    public void enterImportclass(jythonParser.ImportclassContext ctx) {
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        System.out.println("import class: " + ctx.imprtedClass.getText());
    }

    @Override
    public void exitImportclass(jythonParser.ImportclassContext ctx) {
    }

    @Override
    public void enterClassDef(jythonParser.ClassDefContext ctx) {
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        this.whiteSpaces +=4;
        System.out.print("class " + ctx.className.getText() + "/ class parents: ");
        for(int i=1;i<ctx.CLASSNAME().toArray().length;i++)
            System.out.print(ctx.CLASSNAME(i).getText() + ", ");
        if (ctx.CLASSNAME().toArray().length==1)
            System.out.print("object, ");
        System.out.println("{");
    }

    @Override
    public void exitClassDef(jythonParser.ClassDefContext ctx) {
        this.whiteSpaces -=4;
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        System.out.println('}');
    }

    @Override
    public void enterClass_body(jythonParser.Class_bodyContext ctx) {
//        System.out.println(ctx.getText());
    }

    @Override
    public void exitClass_body(jythonParser.Class_bodyContext ctx) {

    }

    @Override
    public void enterVarDec(jythonParser.VarDecContext ctx) {
        if(this.isParameter){
            System.out.print(ctx.type.getText() + " "+ctx.name.getText()+", ");
        }
        else{
            for (int i = 0; i < this.whiteSpaces; i++)
                System.out.print(" ");
            System.out.println("field: " + ctx.name.getText() + "/ type= " + ctx.type.getText());
        }
    }

    @Override
    public void exitVarDec(jythonParser.VarDecContext ctx) {

    }

    @Override
    public void enterArrayDec(jythonParser.ArrayDecContext ctx) {
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        System.out.println("field: "+ ctx.name.getText() + "/ type= "+ctx.type.getText());
    }

    @Override
    public void exitArrayDec(jythonParser.ArrayDecContext ctx) {

    }

    @Override
    public void enterMethodDec(jythonParser.MethodDecContext ctx) {
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        this.whiteSpaces +=4;
        if (ctx.type == null && ctx.name.getText().equals("main"))
            System.out.println("main method{");
        else
            System.out.println("class method: "+ctx.name.getText() + "/ return type: "+ (ctx.type==null ? "void" : ctx.type.getText()) +"{" );

    }

    @Override
    public void exitMethodDec(jythonParser.MethodDecContext ctx) {
        this.whiteSpaces -=4;
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        System.out.println('}');
    }

    @Override
    public void enterConstructor(jythonParser.ConstructorContext ctx) {
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        this.whiteSpaces +=4;
        System.out.println("class constructor: " + ctx.type.getText()+"{");
    }

    @Override
    public void exitConstructor(jythonParser.ConstructorContext ctx) {
        this.whiteSpaces -=4;
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        System.out.println('}');
    }

    @Override
    public void enterParameter(jythonParser.ParameterContext ctx) {
        this.isParameter=true;
        for (int i=0; i<this.whiteSpaces;i++)
            System.out.print(" ");
        System.out.print("parameter list: [");
    }
    @Override
    public void exitParameter(jythonParser.ParameterContext ctx) {
        System.out.println("]");
        this.isParameter=false;
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
        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if ( ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null){
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                this.whiteSpaces +=4;
                System.out.println("nested statement{");
                break;
            }
        }
    }

    @Override
    public void exitIf_statment(jythonParser.If_statmentContext ctx) {
        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if (ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null){
                this.whiteSpaces-=4;
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                System.out.println("}");
                break;
            }
        }
    }

    @Override
    public void enterWhile_statment(jythonParser.While_statmentContext ctx) {

        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if ( ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null){
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                this.whiteSpaces +=4;
                System.out.println("nested statement{");
                break;
            }
        }
    }

    @Override
    public void exitWhile_statment(jythonParser.While_statmentContext ctx) {
        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if (ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null){
                this.whiteSpaces-=4;
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                System.out.println("}");
                break;
            }
        }

    }

    @Override
    public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if ( ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null)
            {
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                this.whiteSpaces +=4;
                System.out.println("nested statement{");
                break;
            }
        }
    }

    @Override
    public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) {
        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if (ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null){
                this.whiteSpaces-=4;
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                System.out.println("}");
                break;
            }
        }

    }

    @Override
    public void enterPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void exitPrint_statment(jythonParser.Print_statmentContext ctx) {

    }

    @Override
    public void enterFor_statment(jythonParser.For_statmentContext ctx) {
        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if ( ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null){
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                this.whiteSpaces +=4;
                System.out.println("nested statement{");
                break;
            }
        }
    }

    @Override
    public void exitFor_statment(jythonParser.For_statmentContext ctx) {
        for (int i = 0;i<ctx.statement().toArray().length;i++){
            if ( ctx.statement(i).if_statment() != null ||
                    ctx.statement(i).if_else_statment() != null ||
                    ctx.statement(i).for_statment() != null ||
                    ctx.statement(i).while_statment() != null){
                this.whiteSpaces-=4;
                for (int j=0; j<this.whiteSpaces;j++)
                    System.out.print(" ");
                System.out.println("}");
                break;
            }
        }

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
