package interpreter.expr;

import interpreter.InterpreterException;
import interpreter.value.BoolValue;
import interpreter.value.NumberValue;
import interpreter.value.Value;

public class UnaryExpr extends Expr {
    
    public static enum Op {
        NotOp,
        PosOp,
        NegOp,
        PreInc,
        PosInc,
        PreDec,
        PosDec
    }

    private Expr expr;
    private Op op;

    public UnaryExpr(int line, Expr expr, Op op) {
        super(line);
        this.expr = expr;
        this.op = op;
    }

    @Override
    public Value<?> expr() {
        Value<?> v = expr.expr();

        switch (this.op) {
            case NotOp:
                return notOp(v);
            case PosOp:
                return posOp(v);
            case NegOp:
                return negOp(v);
            case PreInc:
                return preIncOp(v);
            case PosInc:
                return posIncOp(v);
            case PreDec:
                return preDecOp(v);
            case PosDec:
            default:
                return posDecOp(v);
        }
    }

    private Value<?> notOp(Value<?> v) {
        boolean b = BoolValue.convert(v);
        return new BoolValue(!b);
    }

    private Value<?> posOp(Value<?> v) {
        double d = NumberValue.convert(v);
        return new NumberValue(d);
    }

    private Value<?> negOp(Value<?> v) {
        double d = NumberValue.convert(v);
        return new NumberValue(-d);
    }

    private Value<?> preIncOp(Value<?> v) {
        double d = NumberValue.convert(v);
        if (this.expr instanceof SetExpr){
            ((SetExpr) this.expr).setValue(new NumberValue(d + 1));
            return new NumberValue(d + 1);
        } else {
            throw new InterpreterException(getLine());
        }
    }

    private Value<?> posIncOp(Value<?> v) {
        double d = NumberValue.convert(v);

        if (this.expr instanceof SetExpr){
            ((SetExpr) this.expr).setValue(new NumberValue(d + 1));
            return new NumberValue(d);
        } else {
            throw new InterpreterException(getLine());
        }
    }

    private Value<?> preDecOp(Value<?> v) {
        double d = NumberValue.convert(v);
        if (this.expr instanceof SetExpr){
            ((SetExpr) this.expr).setValue(new NumberValue(d - 1));
            return new NumberValue(d - 1);
        } else {
            throw new InterpreterException(getLine());
        }
    }

    private Value<?> posDecOp(Value<?> v) {
        double d = NumberValue.convert(v);
        if (this.expr instanceof SetExpr){
            ((SetExpr) this.expr).setValue(new NumberValue(d - 1));
            return new NumberValue(d);
        } else {
            throw new InterpreterException(getLine());
        }
    }

}
