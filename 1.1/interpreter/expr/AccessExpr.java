package interpreter.expr;

import interpreter.InterpreterException;
import interpreter.value.*;

import javax.swing.*;
import java.util.ArrayList;
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
        Value<?> v = base.expr();
        Value<?> i = index.expr();

        if(v instanceof ListValue){
            ListValue b = (ListValue) v;
            if(i instanceof NumberValue){
                double num = ((NumberValue) i).value();
                if(num < b.value().size()){
                    return b.value().get((int)num);
                }
                else{
                    return null;
                }
            }
            else{
                throw new InterpreterException(getLine());
            }
        }
        else if(v instanceof ObjectValue){
            ObjectValue b = (ObjectValue) v;
            String s = TextValue.convert(i);

            return b.value().get(new TextValue(s));
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
                List<Value<?>> al = b.value();
                    while(al.size() <= num){
                        al.add(null);
                    }
                    b.value().set((int)num, value);


            }
            else{
                throw new InterpreterException(getLine());
            }

        }
        else if(v instanceof ObjectValue){
            ObjectValue b = (ObjectValue) v;
            String s = TextValue.convert(i);

            b.value().put(new TextValue(s), value);
        }
        else{
            throw new InterpreterException(getLine());
        }
    }
}
