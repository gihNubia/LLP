package interpreter.function;

import interpreter.value.ListValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;
import java.util.Random;
import java.util.Scanner;

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
                for(Value<?> x : ((ListValue) getParams().expr()).value()){
                    System.out.print(x);
                }
                System.out.println();
                return null;

            case Random:
                Random r = new Random();
                double num = r.nextDouble();
                return  new NumberValue(num);

            case Read:
                String in;
                Scanner sc = new Scanner(System.in);
                in = sc.nextLine();
                return new TextValue(in);

            default:
                return null;
        }
    }
}
