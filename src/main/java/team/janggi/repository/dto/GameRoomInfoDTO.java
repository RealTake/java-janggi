package team.janggi.repository.dto;

import java.time.LocalDateTime;
import team.janggi.domain.Team;

public record GameRoomInfoDTO(
        Long id,
        Team currentTurn,
        LocalDateTime createdDt
) {
}
