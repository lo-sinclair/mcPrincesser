package xyz.losi.mcprincesser.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import xyz.losi.mcprincesser.model.dto.GameServer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StatusRequestDto {
    private List<GameServer> servers = new ArrayList<>();

    private GameServer activeServer;

}
