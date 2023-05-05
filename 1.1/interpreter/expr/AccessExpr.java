package interpreter.expr;

import interpreter.InterpreterException;
import interpreter.value.*;

public class AccessExpr extends SetExpr{

    private SetExpr base;
    private Expr index;

    public AccessExpr(int line, SetExpr base, Expr index){
        super(line);
        this.base = base;
        this.index = index;
    }




    public Value<?> expr(){
        Value<?> v = base.expr();
        Value<?> i = index.expr();
        if(v instanceof ListValue){
            ListValue b = (ListValue) v;
            if(i instanceof NumberValue){
                double num = ((NumberValue) i).value();
                return b.value().get((int)num);
            }
            else{
                throw new InterpreterException(getLine());
            }

        }
        if(v instanceof ObjectValue){
            ObjectValue b = (ObjectValue) v;
            if(i instanceof TextValue){
                return b.value().get((TextValue) i);
            }
            else{
                throw new InterpreterException(getLine());
            }

        }
        else{
            throw new InterpreterException(getLine());
        }

    }

    public void setValue(Value<?> value){
        // x[i] = value;
        //x["b"]
        //x.b
        Value<?> v = base.expr();
        Value<?> i = index.expr();
        if(v instanceof ListValue){
            ListValue b = (ListValue) v;
            if(i instanceof NumberValue){
                double num = NumberValue.convert(i);
                b.value().set((int)num, value);
            }
            else{
                throw new InterpreterException(getLine());
            }

        }
        else if(v instanceof ObjectValue){
            ObjectValue b = (ObjectValue) v;
            if(i instanceof TextValue){
                b.value().put((TextValue) i, value);
                v = b;
            }
            else{
                throw new InterpreterException(getLine());
            }
        }
        else{
            throw new InterpreterException(getLine());
        }

    }
}
