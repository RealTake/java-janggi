package domain.piece.rule;

import domain.piece.Piece;
import domain.piece.Position;
import java.util.List;
import java.util.Optional;

public class PalaceRule implements Rule {

    @Override
    public void validate(List<Position> path, List<Piece> piecesInPath, Piece selectedPiece,
                         Optional<Piece> targetPiece) {
        boolean isOutOfPalace = path.stream().anyMatch(position -> !position.isInPalace());
        if (isOutOfPalace) {
            throw new IllegalArgumentException("궁 내부에서만 이동할 수 있습니다.");
        }
    }
}
