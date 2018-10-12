import java.util.HashMap;

public class BB {

    public HashMap<String, Integer> variablesList = new HashMap<String,Integer>();

    public static void main(String[] args) {
        BB myBB = new BB();
        Expr block1 = new VariableOperation(0, "A", myBB);
        Expr block2 = new VariableOperation(1, "A", myBB);
        Expr block3 = new VariableOperation(2,"A",myBB);
        block1.execute();
        block2.execute();
        block3.execute();



    }

    public void panic(String errorMessage) {
        System.out.println("PANIC: " + errorMessage);
        System.exit(0);
    }
}
