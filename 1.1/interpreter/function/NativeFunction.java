package interpreter.function;

import interpreter.value.Value;

import java.lang.annotation.Native;

import interpreter.expr.Variable;

public class NativeFunction extends Function{

    private NativeOp op;

    public NativeFunction(Variable params, NativeOp op){
        super(params);
        this.op = op;
    }
    public Value<?> call(){
        switch (op){
            case Log:
                break;
            case Random:
                break;
            case Read:
                break;
        }

        return null;
    }
}
