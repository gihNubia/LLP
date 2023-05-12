package interpreter.command;

import java.util.ArrayList;
import java.util.List;

import interpreter.InterpreterException;
import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.value.ListValue;
import interpreter.value.ObjectValue;
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
        Value<?> v = expr.expr();
        List<Value<?>> values = null;

        if (v instanceof ListValue){
            values = ((ListValue)v).value();
        }
        else if (v instanceof ObjectValue){
            values = new ArrayList<Value<?>>(((ObjectValue)v).value().keySet());
        }
        else {
            throw new InterpreterException(getLine());
        }

        for (Value<?> vl : values){
            var.setValue(vl);
            cmds.execute();
        }
    }
}
