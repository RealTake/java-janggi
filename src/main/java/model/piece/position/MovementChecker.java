package model.piece.position;

public class MovementChecker {

    public static final int NO_MOVE = 0;
    public static final int UP = -1;
    public static final int LEFT = -1;
    public static final int DOWN = 1;
    public static final int RIGHT = 1;

    public static boolean isUp(final int rowStep, final int columnStep) {
        return rowStep == UP
                && columnStep == NO_MOVE;
    }

    public static boolean isLeft(final int rowStep, final int columnStep) {
        return rowStep == NO_MOVE
                && columnStep == LEFT;
    }

    public static boolean isRight(final int rowStep, final int columnStep) {
        return rowStep == NO_MOVE
                && columnStep == RIGHT;
    }

    public static boolean isDown(final int rowStep, final int columnStep) {
        return rowStep == DOWN
                && columnStep == NO_MOVE;
    }

    public static boolean isUpLeft(final int rowStep, final int columnStep) {
        return rowStep == UP
                && columnStep == LEFT;
    }

    public static boolean isUpRight(final int rowStep, final int columnStep) {
        return rowStep == UP
                && columnStep == RIGHT;
    }

    public static boolean isDownLeft(final int rowStep, final int columnStep) {
        return rowStep == DOWN
                && columnStep == LEFT;
    }

    public static boolean isDownRight(final int rowStep, final int columnStep) {
        return rowStep == DOWN
                && columnStep == RIGHT;
    }

    public static boolean isUpLeftStraight(final int rowStep, final int columnStep) {
        return rowStep <= UP
                && columnStep <= LEFT
                && Math.abs(rowStep) == Math.abs(columnStep);
    }

    public static boolean isUpRightStraight(final int rowStep, final int columnStep) {
        return rowStep <= UP
                && columnStep >= RIGHT
                && Math.abs(rowStep) == Math.abs(columnStep);
    }

    public static boolean isDownLeftStraight(final int rowStep, final int columnStep) {
        return rowStep >= DOWN
                && columnStep <= LEFT
                && Math.abs(rowStep) == Math.abs(columnStep);
    }

    public static boolean isDownRightStraight(final int rowStep, final int columnStep) {
        return rowStep >= DOWN
                && columnStep >= RIGHT
                && Math.abs(rowStep) == Math.abs(columnStep);
    }

    public static boolean isUpStraight(final int rowStep, final int columnStep) {
        return rowStep <= UP
                && columnStep == NO_MOVE;
    }

    public static boolean isLeftStraight(final int rowStep, final int columnStep) {
        return rowStep == NO_MOVE
                && columnStep <= LEFT;
    }

    public static boolean isRightStraight(final int rowStep, final int columnStep) {
        return rowStep == NO_MOVE
                && columnStep >= RIGHT;
    }

    public static boolean isDownStraight(final int rowStep, final int columnStep) {
        return rowStep >= DOWN
                && columnStep == NO_MOVE;
    }

    public static boolean isUpUpLeft(final int rowStep, final int columnStep) {
        return rowStep == UP + UP
                && columnStep == LEFT;
    }

    public static boolean isUpUpRight(final int rowStep, final int columnStep) {
        return rowStep == UP + UP
                && columnStep == RIGHT;
    }

    public static boolean isLeftLeftUp(final int rowStep, final int columnStep) {
        return rowStep == UP
                && columnStep == LEFT + LEFT;
    }

    public static boolean isLeftLeftDown(final int rowStep, final int columnStep) {
        return rowStep == DOWN
                && columnStep == LEFT + LEFT;
    }

    public static boolean isRightRightUp(final int rowStep, final int columnStep) {
        return rowStep == UP
                && columnStep == RIGHT + RIGHT;
    }

    public static boolean isRightRightDown(final int rowStep, final int columnStep) {
        return rowStep == DOWN
                && columnStep == RIGHT + RIGHT;
    }

    public static boolean isDownDownLeft(final int rowStep, final int columnStep) {
        return rowStep == DOWN + DOWN
                && columnStep == LEFT;
    }

    public static boolean isDownDownRight(final int rowStep, final int columnStep) {
        return rowStep == DOWN + DOWN
                && columnStep == RIGHT;
    }

    public static boolean isUpUpUpLeftLeft(final int rowStep, final int columnStep) {
        return rowStep == UP + UP + UP
                && columnStep == LEFT + LEFT;
    }

    public static boolean isUpUpUpRightRight(final int rowStep, final int columnStep) {
        return rowStep == UP + UP + UP
                && columnStep == RIGHT + RIGHT;
    }

    public static boolean isLeftLeftLeftUpUp(final int rowStep, final int columnStep) {
        return rowStep == UP + UP
                && columnStep == LEFT + LEFT + LEFT;
    }

    public static boolean isLeftLeftLeftDownDown(final int rowStep, final int columnStep) {
        return rowStep == DOWN + DOWN
                && columnStep == LEFT + LEFT + LEFT;
    }

    public static boolean isRightRightRightUpUp(final int rowStep, final int columnStep) {
        return rowStep == UP + UP
                && columnStep == RIGHT + RIGHT + RIGHT;
    }

    public static boolean isRightRightRightDownDown(final int rowStep, final int columnStep) {
        return rowStep == DOWN + DOWN
                && columnStep == RIGHT + RIGHT + RIGHT;
    }

    public static boolean isDownDownDownLeftLeft(final int rowStep, final int columnStep) {
        return rowStep == DOWN + DOWN + DOWN
                && columnStep == LEFT + LEFT;
    }

    public static boolean isDownDownDownRightRight(final int rowStep, final int columnStep) {
        return rowStep == DOWN + DOWN + DOWN
                && columnStep == RIGHT + RIGHT;
    }
}
