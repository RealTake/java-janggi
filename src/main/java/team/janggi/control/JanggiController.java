package team.janggi.control;

import team.janggi.domain.Board;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.strategy.boardstruct.NormalBoardStrategy;
import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;
import team.janggi.domain.strategy.layout.normal.NormalSetup;
import team.janggi.view.ConsoleInputView;
import team.janggi.view.ConsoleOutputView;

public class JanggiController {
    private final ConsoleOutputView consoleOutputView;
    private final ConsoleInputView consoleInputView;

    public JanggiController() {
        this.consoleOutputView = new ConsoleOutputView();
        this.consoleInputView = new ConsoleInputView();
    }

    public void run() {
        final NormalSetup choSetup = consoleInputView.readChoNormalSetup();
        final NormalSetup hanSetup = consoleInputView.readHanNormalSetup();

        final Board board = new Board(new NormalBoardStrategy(new NormalLayoutStrategy(choSetup, hanSetup)));

        board.initBoard();
        Team current = Team.CHO;

        while (true) {
            consoleOutputView.print(board);
            Position from = consoleInputView.readMoveSource(current);
            Position to = consoleInputView.readMoveDestination(current);
            try {
                board.move(current, from, to);
                current = nextTeam(current);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Team nextTeam(Team team) {
        return team == Team.CHO ? Team.HAN : Team.CHO;
    }
}
