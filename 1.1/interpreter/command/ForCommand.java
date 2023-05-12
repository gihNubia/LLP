package interpreter.command;

import interpreter.InterpreterException;
import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.value.ListValue;
import interpreter.value.Value;


public class ForCommand extends Command{
    private Expr expr;
    private Command cmds;

    private Variable var;

    public ForCommand(int line, Variable var, Expr expr,  Command cmds){
        super(line);
        this.expr = expr;
        this.cmds = cmds;
        this.var = var;

    }

    public void execute(){
        //for (<var> in <expr> ) <cmds>
        try{
            ListValue v = (ListValue) expr.expr();
            for(Value<?> vl : v.value()){
                var.setValue(vl);
                cmds.execute();
            }
        }
        catch (Exception e){
            throw new InterpreterException(getLine());
        }




    }
}
