package interpreter;

import interpreter.command.Command;
import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.value.ObjectValue;
import interpreter.value.TextValue;
import interpreter.value.Value;
import lexical.Token;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    public final static Environment globals;

    static {
        globals = new Environment();
        //Variable console = globals.declare(new Token("console", Token.Type.NAME,new TextValue("console")), false);
        //Map<TextValue, Value<?>> mp = new HashMap<>();
        //mp.put()
        //ObjectValue obj = new ObjectValue();
    }

    private Interpreter() {

    }

    public static void interpret(Command cmd) {
        cmd.execute();
    }

    public static void interpret(Expr expr) {
        Value<?> v = expr.expr();
        if (v == null)
            System.out.println("undefined");
        else
            System.out.println(v);
    }

}
