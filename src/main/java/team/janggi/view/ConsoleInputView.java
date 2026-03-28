package team.janggi.view;

import java.util.Scanner;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.strategy.layout.normal.NormalSetup;

public class ConsoleInputView {
    private static final int ROW_COUNT = 10;
    private static final int COL_COUNT = 9;
    private static final int SETUP_CHOICE_MIN = 1;
    private static final String TITLE_CHO_NORMAL_SETUP = "초나라 상차림을 선택하세요.";
    private static final String TITLE_HAN_NORMAL_SETUP = "한나라 상차림을 선택하세요.";
    private static final String PROMPT_MOVE_SOURCE_SUFFIX = "움직일 기물 좌표 (행 열): ";
    private static final String PROMPT_MOVE_DESTINATION_SUFFIX = "도착 좌표 (행 열): ";
    private static final String INVALID_COORDINATE_MESSAGE =
            "0~" + (ROW_COUNT - 1) + " 행, 0~" + (COL_COUNT - 1) + " 열 형식으로 다시 입력하세요.";
    private static final String INVALID_SETUP_CHOICE_MESSAGE =
            SETUP_CHOICE_MIN + "부터 " + NormalSetup.values().length + "까지의 숫자를 입력하세요.";

    private final Scanner scanner = new Scanner(System.in);

    public NormalSetup readChoNormalSetup() {
        return readNormalSetup(TITLE_CHO_NORMAL_SETUP);
    }

    public NormalSetup readHanNormalSetup() {
        return readNormalSetup(TITLE_HAN_NORMAL_SETUP);
    }

    public Position readMoveSource(Team currentTurn) {
        return readCoordinate(turnPrefix(currentTurn) + PROMPT_MOVE_SOURCE_SUFFIX);
    }

    public Position readMoveDestination(Team currentTurn) {
        return readCoordinate(turnPrefix(currentTurn) + PROMPT_MOVE_DESTINATION_SUFFIX);
    }

    private String turnPrefix(Team team) {
        return switch (team) {
            case CHO -> "초 차례 — ";
            case HAN -> "한 차례 — ";
            case NONE -> "";
        };
    }

    private Position readCoordinate(String prompt) {
        while (true) {
            printText(prompt);
            String line = scanner.nextLine().trim();
            Position parsed = tryParsePosition(line);
            if (parsed != null) {
                return parsed;
            }
            printLine(INVALID_COORDINATE_MESSAGE);
        }
    }

    private NormalSetup readNormalSetup(String titleLine) {
        final NormalSetup[] setups = NormalSetup.values();

        NormalSetup readNormalSetup = null;
        do {
            printLine(titleLine);
            printSetup(setups);

            readNormalSetup = readNormalSetup(titleLine, setups);
        } while (readNormalSetup == null);

        return readNormalSetup;
    }

    private NormalSetup readNormalSetup(String titleLine, NormalSetup[] setups) {
        printText("선택 (" + SETUP_CHOICE_MIN + "-" + setups.length + "): ");

        int setupChoice;
        try {
            setupChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            printLine(INVALID_SETUP_CHOICE_MESSAGE);
            return null;
        }

        final boolean isValidateRange = setupChoice >= SETUP_CHOICE_MIN && setupChoice <= setups.length;
        if (!isValidateRange) {
            printLine(INVALID_SETUP_CHOICE_MESSAGE);
            return null;
        }

        return setups[setupChoice - SETUP_CHOICE_MIN];
    }

    private void printSetup(NormalSetup[] setups) {
        for (int i = 0; i < setups.length; i++) {
            printLine((i + SETUP_CHOICE_MIN) + ". " + setups[i].name());
        }
    }

    private Position tryParsePosition(String line) {
        final String[] parts = line.split("\\s+");

        if (parts.length < 2) {
            return null;
        }

        try {
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            boolean rowOk = row >= 0 && row < ROW_COUNT;
            boolean colOk = col >= 0 && col < COL_COUNT;
            return rowOk && colOk ? new Position(row, col) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void printText(String text) {
        System.out.print(text);
    }

    private void printLine(String text) {
        System.out.println(text);
    }
}
