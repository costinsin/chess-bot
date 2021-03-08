public class Main {
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        MoveManager moveManager = new MoveManager();

        generateCommands(commandManager, moveManager);

        commandManager.waitForInput();
    }

    public static void generateCommands(CommandManager commandManager, MoveManager moveManager) {
        commandManager.commands.addCommand("xboard", new RunnableFunction() {
            @Override
            public void run(String response) {
                System.out.println("xboard recieved!");
            }
        });
        commandManager.commands.addCommand("protover [0-9]+", new RunnableFunction() {
            @Override
            public void run(String response) {
                commandManager.send("feature sigint=0 san=0 name=\"Chessmate\"");
            }
        });
        commandManager.commands.addCommand("[a-h][1-8][a-h][1-8]", new RunnableFunction() {
            @Override
            public void run(String response) {
                commandManager.send("move " + moveManager.mirrorMove(response));
            }
        });
        commandManager.commands.addCommand("quit", new RunnableFunction() {
            @Override
            public void run(String response) {
                System.exit(0);
            }
        });
    }
}
