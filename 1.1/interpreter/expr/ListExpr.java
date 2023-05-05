package interpreter.expr;

import interpreter.value.ListValue;
import interpreter.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ListExpr extends Expr{

    private List<Expr> items;

    public ListExpr(int line, List<Expr> items){
        super(line);
        this.items = items;
    }

    public Value<?> expr(){
        ArrayList<Value<?>> v = new ArrayList<Value<?>>();
        for(Expr ex : this.items){
            v.add(ex.expr());
        }
        return new ListValue(v);
    }

}
