package interpreter.expr;

import javax.management.RuntimeErrorException;

import interpreter.value.BoolValue;
import interpreter.value.Value;

public class ConditionalExpr extends Expr {
    

    private Expr cond;
    private Expr trueExpr;
    private Expr falseExpr;

    public ConditionalExpr(int line, Expr cond, Expr trueExpr, Expr falseExpr){
        super(line);

        this.cond = cond;
        this.trueExpr = trueExpr;
        this.falseExpr = falseExpr;
    }

    public Value<?> expr(){
        return BoolValue.convert(cond.expr()) ? trueExpr.expr() : falseExpr.expr();
    }
}
