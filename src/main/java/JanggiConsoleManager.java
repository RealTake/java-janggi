import dao.DatabaseConnectionManager;
import dao.GameDao;
import dao.PieceDao;
import model.Answer;
import model.JanggiProcess;
import model.piece.Piece;
import model.piece.Pieces;
import model.player.Player;
import model.player.Team;
import model.piece.position.Position;
import view.InputView;
import view.OutputView;

public class JanggiConsoleManager {

    private final InputView inputView;
    private final OutputView outputView;
    private final PieceDao pieceDao;
    private final GameDao gameDao;

    public JanggiConsoleManager(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;

        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager();
        this.pieceDao = new PieceDao(databaseConnectionManager.getConnection());
        this.gameDao = new GameDao(databaseConnectionManager.getConnection());
    }

    public void playJanggi() {
        JanggiProcess janggiProcess = initializeJanggiProcess();
        progressTurnUntilEnd(janggiProcess);
        outputView.outputWinner(janggiProcess.getWinner(), janggiProcess.calculateTeamPoints(Team.GREEN), janggiProcess.calculateTeamPoints(Team.RED));
        resetDatabase();
    }

    private JanggiProcess initializeJanggiProcess() {
        if (gameDao.isExist()) {
            return initializeWhenPreviousGameExist();
        }
        return initializeNewJanggiGame();
    }

    private JanggiProcess initializeWhenPreviousGameExist() {
        Answer answer = inputView.inputContinuePreviousGame();
        if (answer.isYes()) {
            return initializeFromPreviousJanggiGame();
        }
        resetDatabase();
        return initializeNewJanggiGame();
    }

    private JanggiProcess initializeFromPreviousJanggiGame() {
        Team currentTurnTeam = gameDao.findPreviousGameTurn();
        Pieces greenPieces = Pieces.continuePiecesFrom(pieceDao.findPieceByTeam(Team.GREEN));
        Pieces redPieces = Pieces.continuePiecesFrom(pieceDao.findPieceByTeam(Team.RED));
        Player greenPlayer = new Player(greenPieces, Team.GREEN);
        Player redPlayer = new Player(redPieces, Team.RED);
        return JanggiProcess.initializeJanggi(greenPlayer, redPlayer, currentTurnTeam);
    }

    private void resetDatabase() {
        gameDao.deleteGame();
        pieceDao.deleteAllPieces();
    }

    private JanggiProcess initializeNewJanggiGame() {
        Pieces greenPieces = Pieces.initializeGreenTeamPieces();
        Pieces redPieces = Pieces.initializeRedTeamPieces();
        Player greenPlayer = new Player(greenPieces, Team.GREEN);
        Player redPlayer = new Player(redPieces, Team.RED);
        initializePiecesToDB(greenPieces, redPieces);
        return JanggiProcess.initializeJanggi(greenPlayer, redPlayer, Team.GREEN);
    }

    public void initializePiecesToDB(final Pieces greenPieces, final Pieces redPieces) {
        pieceDao.addPieces(greenPieces, Team.GREEN);
        pieceDao.addPieces(redPieces, Team.RED);
        gameDao.addGame(Team.GREEN);
    }

    private void progressTurnUntilEnd(final JanggiProcess janggiProcess) {
        while (janggiProcess.isAllPlayersAlive()) {
            outputView.outputCurrentJanggiBoard(janggiProcess.getPlayerByTeam(Team.RED).getPieces(), janggiProcess.getPlayerByTeam(Team.GREEN).getPieces());
            progressTurn(janggiProcess);
        }
        outputView.outputCurrentJanggiBoard(janggiProcess.getPlayerByTeam(Team.RED).getPieces(), janggiProcess.getPlayerByTeam(Team.GREEN).getPieces());
    }

    private void progressTurn(final JanggiProcess janggiProcess) {
        try {
            Position startPosition = inputView.inputCurrentTeamMovePiecePosition(janggiProcess.getCurrentTurnTeam());
            Piece movePiece = janggiProcess.findCurrentTurnPlayerPieceAt(startPosition);
            Position destination = inputView.inputDestinationToMove(movePiece.getPieceType());
            janggiProcess.movePiece(startPosition, destination);
            janggiProcess.changeTurn();
            updateTurnResultToDatabase(janggiProcess.getCurrentTurnTeam(), startPosition, destination);
        } catch (RuntimeException exception) {
            outputView.outputExceptionMessage(exception.getMessage());
        }
    }

    private void updateTurnResultToDatabase(final Team nextTurn, final Position startPosition, final Position destination) {
        pieceDao.deletePieceByPosition(destination);
        pieceDao.updatePiecePosition(startPosition, destination);
        gameDao.updateGameTurn(nextTurn);
    }
}
