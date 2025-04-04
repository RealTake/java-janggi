import view.InputView;
import view.OutputView;

public class JanggiApplication {

    public static void main(String[] args) {
        JanggiConsoleManager janggiConsoleManager = new JanggiConsoleManager(new InputView(), new OutputView());
        janggiConsoleManager.playJanggi();
    }
}
