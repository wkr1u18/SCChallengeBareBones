import java.util.*;
import java.io.*;

public class BB {

    public HashMap<String, Integer> variablesList = new HashMap<String,Integer>();

    public static void main(String[] args) {
        BB myBB = new BB();



        Expr root = new ExecutableBlock(myBB);
        root.addNewBlock(new VariableOperation(0, "A", myBB));
        root.addNewBlock(new VariableOperation(1, "A", myBB));
        root.addNewBlock(new VariableOperation(1, "A", myBB));
        root.addNewBlock(new VariableOperation(0, "B", myBB));

        ExecutableBlock loopList = new ExecutableBlock(myBB);
        loopList.addNewBlock(new VariableOperation(2, "A", myBB));
        loopList.addNewBlock(new VariableOperation(1, "B", myBB));
        root.addNewBlock(new LoopExpression("A", 0, loopList, myBB));

        root.execute();
        





    }

    public void panic(String errorMessage) {
        System.out.println("PANIC: " + errorMessage);
        System.exit(0);
    }
}
