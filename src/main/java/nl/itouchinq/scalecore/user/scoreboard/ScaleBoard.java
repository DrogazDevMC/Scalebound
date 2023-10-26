package nl.itouchinq.scalecore.user.scoreboard;

import nl.fenixnetwerk.modules.scoreboard.Assemble;
import nl.fenixnetwerk.modules.scoreboard.AssembleAdapter;
import nl.fenixnetwerk.modules.scoreboard.AssembleBoard;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScaleBoard implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return "&3Scale&bBoard";
    }

    @Override
    public List<String> getLines(Player player) {
        ArrayList<String> lines = new ArrayList<>();

        lines.add("");
        lines.add("");
        lines.add("");
        lines.add("");

        return lines;
    }
}
