import org.fusesource.jansi.Ansi
import org.fusesource.jansi.AnsiConsole

class GameController {

    private final static LEFT = "s"
    private final static RIGHT = "f"
    private final static UP = "e"
    private final static DOWN = "x"

    void run() {
        AnsiConsole.systemInstall()
        InputStreamReader inputStreamReader = new InputStreamReader(System.in)
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        System.out.println("2048")
        GameView view = GameView.init()
        GameEngine engine = GameEngine.init(view)
        while(engine.running) {
            def input = bufferedReader.readLine()
            switch(input) {
                case LEFT : engine.moveLeft(); break;
                case RIGHT: engine.moveRight(); break;
                case UP: engine.moveUp(); break;
                case DOWN: engine.moveDown(); break;
            }
        }
    }

    static void main(String[] args) {
        new GameController().run()
    }
}
