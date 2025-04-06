package Commands;

public class Where extends Command{

    public Where(){
        super(null);
        System.err.println("Oopsie! Something is wrong with the arguments!");
    }

    @Override
    public String execute(){return("");};
}
