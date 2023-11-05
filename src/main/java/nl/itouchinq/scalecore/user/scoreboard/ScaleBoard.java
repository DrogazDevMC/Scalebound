package nl.itouchinq.scalecore.user.scoreboard;

import nl.fenixnetwerk.modules.scoreboard.AssembleAdapter;
import nl.itouchinq.scalecore.ScaleCore;
import nl.itouchinq.scalecore.user.User;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScaleBoard implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return "&3Scale&bBoard";
    }

    @Override
    public List<String> getLines(Player player) {
        ArrayList<String> lines = new ArrayList<>();
        User user = ScaleCore.get().getUserManager().getUsers().get(player.getUniqueId());

//        lines.add("");
//        lines.add("");
        lines.add("Coins");
        lines.add("  " + user.getCoins());
//        lines.add("");
//        lines.add("");

        return lines;
    }
}
