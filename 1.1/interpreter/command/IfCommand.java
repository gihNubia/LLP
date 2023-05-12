package interpreter.command;

import interpreter.expr.Expr;
import interpreter.value.BoolValue;
import interpreter.value.Value;

public class IfCommand extends Command{
    private Expr expr;

    private Command thenCmds;

    private Command elseCmds;

    public IfCommand(int line, Expr expr, Command thenCmds, Command elseCmds){
        super(line);

        this.expr = expr;
        this.thenCmds = thenCmds;
        this.elseCmds = elseCmds;
    }

    public IfCommand(int line, Expr expr, Command thenCmds){
        super(line);

        this.expr = expr;
        this.thenCmds = thenCmds;
        this.elseCmds = null;
    }

    @Override
    public void execute(){
        Value<?> v = expr.expr();

        if(BoolValue.convert(v)){
            thenCmds.execute();
        }
        else if(elseCmds != null){
            elseCmds.execute();
        }

    }

}
