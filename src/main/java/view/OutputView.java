package view;

import model.piece.Piece;
import model.piece.Pieces;
import model.player.Player;
import model.piece.position.Position;

import java.util.Arrays;

import static model.piece.position.Position.COLUMN_COUNT_OF_JANGGI;
import static model.piece.position.Position.MAXIMUM_GREEN_PALACE_ROW;
import static model.piece.position.Position.MAXIMUM_PALACE_COLUMN;
import static model.piece.position.Position.MAXIMUM_RED_PALACE_ROW;
import static model.piece.position.Position.MINIMUM_GREEN_PALACE_ROW;
import static model.piece.position.Position.MINIMUM_PALACE_COLUMN;
import static model.piece.position.Position.MINIMUM_RED_PALACE_ROW;
import static model.piece.position.Position.ROW_COUNT_OF_JANGGI;

public class OutputView {

    private static final String RED_COLOR_ANSI_CODE = "\u001B[31m";
    private static final String GREEN_COLOR_ANSI_CODE = "\u001B[32m";
    private static final String COLOR_CANCEL_ANSI_CODE = "\u001B[0m";
    private static final String EMPTY_POSITION_TEXT = "－";
    private static final String LINE = System.lineSeparator();

    public void outputExceptionMessage(final String exceptionMessage) {
        System.out.println("[ERROR] " + exceptionMessage + LINE);
    }

    public void outputCurrentJanggiBoard(final Pieces redPieces, final Pieces greenPieces) {
        String[][] piecePositionTexts = initializePiecePositionTexts();
        placePieceTexts(piecePositionTexts, redPieces, greenPieces);
        printCurrentPiecePositions(piecePositionTexts);
    }

    private String[][] initializePiecePositionTexts() {
        String[][] piecePositionTexts = new String[ROW_COUNT_OF_JANGGI][COLUMN_COUNT_OF_JANGGI];
        for (String[] piecePositionText : piecePositionTexts) {
            Arrays.fill(piecePositionText, EMPTY_POSITION_TEXT);
        }
        paintPalace(piecePositionTexts);
        return piecePositionTexts;
    }

    private void paintPalace(final String[][] piecePositionTexts) {
        for (int column = MINIMUM_PALACE_COLUMN; column <= MAXIMUM_PALACE_COLUMN; column++) {
            for (int row = MINIMUM_RED_PALACE_ROW; row <= MAXIMUM_RED_PALACE_ROW; row++) {
                piecePositionTexts[row][column] = "＋";
            }
            for (int row = MINIMUM_GREEN_PALACE_ROW; row <= MAXIMUM_GREEN_PALACE_ROW; row++) {
                piecePositionTexts[row][column] = "＋";
            }
        }
    }

    private void placePieceTexts(final String[][] piecePositionTexts, final Pieces redPieces, final Pieces greenPieces) {
        for (Piece piece : redPieces.getPieces()) {
            Position piecePosition = piece.getPosition();
            piecePositionTexts[piecePosition.getRow()][piecePosition.getColumn()] = RED_COLOR_ANSI_CODE + PieceText.textOf(piece.getPieceType()) + COLOR_CANCEL_ANSI_CODE;
        }
        for (Piece piece : greenPieces.getPieces()) {
            Position piecePosition = piece.getPosition();
            piecePositionTexts[piecePosition.getRow()][piecePosition.getColumn()] = GREEN_COLOR_ANSI_CODE + PieceText.textOf(piece.getPieceType()) + COLOR_CANCEL_ANSI_CODE;
        }
    }

    private void printCurrentPiecePositions(final String[][] piecePositionTexts) {
        System.out.println("\tＡＢＣＤＥＦＧＨＩ");
        for (int i = 0; i < piecePositionTexts.length; i++) {
            System.out.print(10 - i + "\t");
            for (int j = 0; j < piecePositionTexts[i].length; j++) {
                System.out.print(piecePositionTexts[i][j]);
            }
            System.out.print(LINE);
        }
        System.out.print(LINE);
    }

    public void outputWinner(final Player player, final int greenPoints, final int redPoints) {
        System.out.println("상대 팀의 궁을 죽였습니다.");
        System.out.println(String.format("%s 팀이 승리하였습니다.", TeamText.textOf(player.getTeam())));
        System.out.println(String.format("초 나라의 점수: %d" + LINE + "한 나라의 점수: %d", greenPoints, redPoints));
    }
}
