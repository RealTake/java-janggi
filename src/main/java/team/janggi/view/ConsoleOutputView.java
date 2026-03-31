package team.janggi.view;

import team.janggi.domain.BoardSize;
import team.janggi.domain.board.Board;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;
import team.janggi.domain.board.BoardStateReader;

public class ConsoleOutputView {
    private static final String SPACE = " ";
    private static final String EMPTY_TEXT = "";
    private static final String EMPTY_SYMBOL = "．";
    private static final String UNKNOWN_SYMBOL = "？";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";

    public void print(Board board) {
        final BoardStateReader status = board.getStatus();
        final int totalCellCount = BoardSize.X * BoardSize.Y;

        printColumnHeader();
        for (int index = 0; index < totalCellCount; index++) {
            int y = index / BoardSize.Y;
            int x = index % BoardSize.Y;

            Piece piece = status.get(new Position(x, y));
            String cellText = toSymbol(piece);
            printText(applyTeamColor(piece, cellText));
            printText(cellSeparatorAfter(x));
            printText(rowLineSuffix(x, y));
        }
    }

    private void printColumnHeader() {
        for (int x = 0; x < BoardSize.Y; x++) {
            printText(toFullWidthDigit(x) + SPACE);
        }
        printText(headerRowIndexColumnPadding());
        printLine();
    }

    private String toSymbol(Piece piece) {
        if (piece == null || piece.isSamePieceType(PieceType.EMPTY)) {
            return EMPTY_SYMBOL;
        }

        return pieceCode(piece);
    }

    private String pieceCode(Piece piece) {
        final boolean isCho = piece.isSameTeam(Team.CHO);

        if (piece.isSamePieceType(PieceType.KING) && isCho) {
            return "宮";
        }
        if (piece.isSamePieceType(PieceType.KING)) {
            return "將";
        }
        if (piece.isSamePieceType(PieceType.GUARD)) {
            return "士";
        }
        if (piece.isSamePieceType(PieceType.HORSE)) {
            return "馬";
        }
        if (piece.isSamePieceType(PieceType.ELEPHANT)) {
            return "象";
        }
        if (piece.isSamePieceType(PieceType.CHARIOT)) {
            return "車";
        }
        if (piece.isSamePieceType(PieceType.CANNON) && isCho) {
            return "包";
        }
        if (piece.isSamePieceType(PieceType.CANNON)) {
            return "砲";
        }
        if (piece.isSamePieceType(PieceType.SOLDIER) && isCho) {
            return "卒";
        }
        if (piece.isSamePieceType(PieceType.SOLDIER)) {
            return "兵";
        }

        return UNKNOWN_SYMBOL;
    }

    private String applyTeamColor(Piece piece, String value) {
        if (piece == null || piece.isSamePieceType(PieceType.EMPTY)) {
            return value;
        }

        if (piece.isSameTeam(Team.CHO)) {
            return ANSI_BLUE + value + ANSI_RESET;
        }
        if (piece.isSameTeam(Team.HAN)) {
            return ANSI_RED + value + ANSI_RESET;
        }
        return value;
    }

    private String toFullWidthDigit(int value) {
        return String.valueOf((char) ('０' + value));
    }

    private String cellSeparatorAfter(int x) {
        return x < BoardSize.Y - 1 ? SPACE : EMPTY_TEXT;
    }

    private String rowLineSuffix(int x, int y) {
        if (x == BoardSize.Y - 1) {
            return SPACE + y + System.lineSeparator();
        }

        return EMPTY_TEXT;
    }

    private String headerRowIndexColumnPadding() {
        return SPACE;
    }

    private void printText(String text) {
        System.out.print(text);
    }

    private void printLine() {
        System.out.println();
    }

}
