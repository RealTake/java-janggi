package model.piece;

import java.util.List;

import static model.piece.position.DefaultPiecePositions.BYEONG_FIFTH_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_FIRST_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_FOURTH_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_SECOND_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_THIRD_RED;
import static model.piece.position.DefaultPiecePositions.CANNON_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.CANNON_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.CANNON_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.CANNON_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.CHARIOT_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.CHARIOT_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.CHARIOT_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.CHARIOT_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.GENERAL_GREEN;
import static model.piece.position.DefaultPiecePositions.GENERAL_RED;
import static model.piece.position.DefaultPiecePositions.GUARD_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.GUARD_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.GUARD_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.GUARD_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.HORSE_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.HORSE_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.HORSE_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.HORSE_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.JOL_FIFTH_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_FIRST_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_FOURTH_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_SECOND_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_THIRD_GREEN;

public class TeamPiecesGenerator {

    public static List<Piece> generateRedTeamPieces() {
        return List.of(
                new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), GENERAL_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FIRST_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_SECOND_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_THIRD_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FOURTH_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FIFTH_RED.getPosition())
        );
    }

    public static List<Piece> generateGreenTeamPieces() {
        return List.of(
                new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), GENERAL_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_FIRST_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_SECOND_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_THIRD_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_FOURTH_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_FIFTH_GREEN.getPosition())
        );
    }
}
