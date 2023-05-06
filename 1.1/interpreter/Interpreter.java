package interpreter;

import java.util.HashMap;
import java.util.Map;

import interpreter.command.Command;
import interpreter.expr.ConstExpr;
import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.function.NativeFunction;
import interpreter.function.NativeOp;
import interpreter.value.FunctionValue;

import interpreter.value.*;
import lexical.*;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {

    public final static Environment globals;

    static {
        globals = new Environment();

        Map<TextValue, Value<?>> mp = new HashMap<TextValue, Value<?>>();

        // Criando funcao Log
        Environment envLog = new Environment(globals);
        Variable paramsLog = envLog.declare(
                    new Token("params", Token.Type.NAME, null),
                    false);
        NativeFunction nfLog = new NativeFunction(paramsLog, NativeOp.Log);
        FunctionValue fvLog = new FunctionValue(nfLog);
        TextValue tvLog = new TextValue("log");
        mp.put(tvLog, fvLog);

        // Criando funcao Read
        Environment envRead = new Environment(globals);
        Variable paramsRead = envRead.declare(
                    new Token("params", Token.Type.NAME, null),
                    false);
        NativeFunction nfRead = new NativeFunction(paramsRead, NativeOp.Read);
        FunctionValue fvRead = new FunctionValue(nfRead);
        TextValue tvRead = new TextValue("read");
        mp.put(tvRead, fvRead);

        // Criando funcao Random
        Environment envRandom = new Environment(globals);
        Variable paramsRandom = envRandom.declare(
                    new Token("params", Token.Type.NAME, null),
                    false);
        NativeFunction nfRandom = new NativeFunction(paramsRandom, NativeOp.Random);
        FunctionValue fvRandom = new FunctionValue(nfRandom);
        TextValue tvRandom = new TextValue("random");
        mp.put(tvRandom, fvRandom);


        ObjectValue ov = new ObjectValue(mp);
        Variable console = globals.declare(
            new Token("console", Token.Type.NAME, null),
            false);
        console.setValue(ov);
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
