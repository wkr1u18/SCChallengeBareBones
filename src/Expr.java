import java.util.*;
import java.io.*;

public class Expr {
    public BB localBB;
    public int type = 0;
    public void execute() {
        localBB.panic("executing null block");
    }
    public void addNewBlock(Expr expressionToAdd) {

    }
}
//0 - empty block
//1 - executable block
//2 - operation on variable
//3 - loops operation

class ExecutableBlock extends Expr {
    public List<Expr> blocks;
    public ExecutableBlock(BB usedBB)
    {
        type = 1;
        localBB = usedBB;
        blocks = new Vector<Expr>();
    }
    public void execute() {
        System.out.println("Running executable block");
        Iterator<Expr> iterator = blocks.iterator();
        while(iterator.hasNext()) {
            Expr nextBlock = iterator.next();
            nextBlock.execute();
        }
    }
    public void addNewBlock(Expr expressionToAdd) {
        blocks.add(expressionToAdd);
    }

}

class VariableOperation extends Expr {
    public int operationType; //0 - clear 1 - increase 2- decrease
    public String operationVariableIdentifier;

    public VariableOperation(int initialType, String initialVariableIdentifier, BB usedBB) {
        localBB = usedBB;
        type = 2;
        operationType = initialType;
        operationVariableIdentifier = initialVariableIdentifier;
    }
    public void execute(){
        if (operationType == 0) {
            System.out.println("Clearing variable: " + operationVariableIdentifier);
            localBB.variablesList.put(operationVariableIdentifier, new Integer(0));
            System.out.println(localBB.variablesList.get(operationVariableIdentifier));
        }
        else {
            System.out.println("Checking whether variable exists... ");
            if (!localBB.variablesList.containsKey(operationVariableIdentifier)) {
                localBB.panic("Variable " + operationVariableIdentifier + " used but not declared");
            }
            else if (operationType == 1) {
                System.out.println("Increasing variable: " + operationVariableIdentifier);
                int currentValue = localBB.variablesList.get(operationVariableIdentifier);
                currentValue++;
                localBB.variablesList.put(operationVariableIdentifier, new Integer(currentValue));
                System.out.println("Value after incrementation: " + currentValue);
            }
            else {
                System.out.println("Decreasing variable: " + operationVariableIdentifier);
                int currentValue = localBB.variablesList.get(operationVariableIdentifier);
                currentValue--;
                localBB.variablesList.put(operationVariableIdentifier, new Integer(currentValue));
                System.out.println("Value after decrementation: " + currentValue);
            }

        }
    }
}
class LoopExpression extends Expr {
    public String conditionVariableIdentifier;
    public int valueNotEqual;
    public ExecutableBlock operationList;

    public LoopExpression(String initialConditionVariableIdentifier, int initialValueNotEqual, ExecutableBlock initialOperationList, BB usedBB) {
        localBB = usedBB;
        type = 3;
        valueNotEqual= initialValueNotEqual;
        conditionVariableIdentifier = initialConditionVariableIdentifier;
        operationList = initialOperationList;
    }

    public void execute() {
        System.out.println("Checking whether variable exists... ");
        if (!localBB.variablesList.containsKey(conditionVariableIdentifier)) {
            localBB.panic("Variable " + conditionVariableIdentifier + " used but not declared");
        }
        int conditionVariableValue = localBB.variablesList.get(conditionVariableIdentifier);
        while(conditionVariableValue!=valueNotEqual) {
            System.out.println("Executing loop. Control variable value is " + conditionVariableValue);
            operationList.execute();
            conditionVariableValue = localBB.variablesList.get(conditionVariableIdentifier);
        }
    }
}
