package team.janggi.service;

import team.janggi.config.AppConfig;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.board.Board;
import team.janggi.domain.board.NormalBoardPiecesInitializer;
import team.janggi.domain.board.NormalSetup;
import team.janggi.domain.GameRoom;
import team.janggi.exception.GameNotFinishedException;
import team.janggi.exception.GameNotFoundException;
import team.janggi.exception.GameOverException;
import team.janggi.repository.BoardRepository;
import team.janggi.repository.GameRoomRepository;
import team.janggi.repository.dto.BoardViewDTO;
import team.janggi.repository.dto.GameRoomInfoDTO;

public class JanggiService {
    private final GameRoomRepository gameRoomRepository;
    private final BoardRepository boardRepository;

    public JanggiService(GameRoomRepository gameRoomRepository,
                        BoardRepository boardRepository) {
        this.gameRoomRepository = gameRoomRepository;
        this.boardRepository = boardRepository;
    }

    public long createGameRoom(NormalSetup choSetup, NormalSetup hanSetup) {
        return AppConfig.transactionManager().execute(() -> {
            final GameRoom gameRoom = new GameRoom(Team.CHO);
            final long gameRoomId = gameRoomRepository.save(gameRoom);

            final Board board = new Board(new NormalBoardPiecesInitializer(choSetup, hanSetup));
            boardRepository.save(gameRoomId, board);
            return gameRoomId;
        });
    }

    public GameRoomInfoDTO findGameRoom(long gameRoomId) {
        final GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);
        if (gameRoom == null) {
            throw new GameNotFoundException();
        }

        return new GameRoomInfoDTO(gameRoom.getId(), gameRoom.getCurrentTurn(), gameRoom.getCreatedDt());
    }

    public void move(long gameRoomId, Team team, Position from, Position to) {
        final GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);
        if (gameRoom == null) {
            throw new GameNotFoundException();
        }

        final Board board = boardRepository.findById(gameRoomId);
        if (board == null) {
            throw new GameNotFoundException();
        }

        if (board.isGameFinished()) {
            throw new GameOverException();
        }

        AppConfig.transactionManager().execute(() -> {
            gameRoom.changeTurn(getNextTurn(team));
            board.move(team, from, to);

            gameRoomRepository.save(gameRoom);
            boardRepository.save(gameRoomId, board);
            return null;
        });
    }

    public boolean isGameOver(long gameRoomId) {
        final Board board = boardRepository.findById(gameRoomId);

        if (board == null) {
            throw new GameNotFoundException();
        }

        return board.isGameFinished();
    }

    public Team getWinnerTeam(long gameRoomId) {
        final Board board = boardRepository.findById(gameRoomId);
        if (board == null) {
            throw new GameNotFoundException();
        }

        if (!board.isGameFinished()) {
            throw new GameNotFinishedException();
        }

        return board.getWinner();
    }

    public Team getTurn(long gameRoomId) {
        final GameRoom gameRoom = gameRoomRepository.findById(gameRoomId);

        if (gameRoom == null) {
            throw new GameNotFoundException();
        }
        return gameRoom.getCurrentTurn();
    }

    public BoardViewDTO getBoardView(long gameRoomId) {
        final Board board = boardRepository.findById(gameRoomId);
        if (board == null) {
            throw new GameNotFoundException();
        }

        return new BoardViewDTO(getTurn(gameRoomId),
                board.getScore(Team.CHO), board.getScore(Team.HAN),
                board.getStateReader());
    }

    private Team getNextTurn(Team currentTurn) {
        if (currentTurn == Team.CHO) {
            return Team.HAN;
        }
        if (currentTurn == Team.HAN) {
            return Team.CHO;
        }
        throw new IllegalStateException("지원하지 않는 팀입니다: " + currentTurn);
    }
}
