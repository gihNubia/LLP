package interpreter.expr;

import interpreter.InterpreterException;
import interpreter.value.*;
import java.util.List;

public class AccessExpr extends SetExpr{

    private SetExpr base;
    private Expr index;

    public AccessExpr(int line, SetExpr base, Expr index){
        super(line);
        this.base = base;
        this.index = index;
    }


    public Value<?> expr(){
        Value<?> b = base.expr();
        Value<?> i = index.expr();

        if(b instanceof ListValue){
            ListValue blv = (ListValue) b;
            if(i instanceof NumberValue){
                double num = ((NumberValue) i).value();

                if (num < 0){
                    throw new InterpreterException(getLine());
                }
                
                if(num < blv.value().size()){
                    return blv.value().get((int)num);
                }
                else{
                    return null;
                }
            }
            else{
                throw new InterpreterException(getLine());
            }
        }
        else if(b instanceof ObjectValue){
            ObjectValue bov = (ObjectValue) b;
            String s = TextValue.convert(i);

            return bov.value().get(new TextValue(s));
        }
        else{
            throw new InterpreterException(getLine());
        }
    }

    public void setValue(Value<?> value){
        Value<?> b = base.expr();
        Value<?> i = index.expr();

        if(b instanceof ListValue){
            ListValue blv = (ListValue) b;
            if(i instanceof NumberValue){
                double num = NumberValue.convert(i);

                if (num < 0){
                    throw new InterpreterException(getLine());
                }
                
                List<Value<?>> al = blv.value();
                while(al.size() <= num){
                    al.add(null);
                }
                blv.value().set((int)num, value);
            }
            else{
                throw new InterpreterException(getLine());
            }

        }
        else if(b instanceof ObjectValue){
            ObjectValue bov = (ObjectValue) b;
            String s = TextValue.convert(i);

            bov.value().put(new TextValue(s), value);
        }
        else{
            throw new InterpreterException(getLine());
        }
    }
}
