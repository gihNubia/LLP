package interpreter.expr;

import interpreter.value.ObjectValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectExpr extends Expr{
    private List<ObjectItem> items;
    public ObjectExpr(int line, List<ObjectItem> items){
        super(line);
        this.items = items;
    }

    public Value<?> expr(){
        //{"ba": 1, "bd": "123", "ac" = 2i + 1}
        Map<TextValue, Value<?>> mapa = new HashMap<TextValue, Value<?>>();

        for (ObjectItem obj : items){
            Value<?> v = obj.value.expr();
            TextValue t = new TextValue(obj.key);
            mapa.put(t,v);
        }

        ObjectValue ov = new ObjectValue(mapa);
        return ov;
    }
}
