package nl.itouchinq.scalescore.user.menu;

import lombok.RequiredArgsConstructor;
import nl.fenixnetwerk.modules.menu.Menu;
import nl.fenixnetwerk.modules.menu.button.Button;
import nl.itouchinq.scalescore.user.User;
import org.bukkit.entity.Player;

import java.util.Map;

@RequiredArgsConstructor
public class PlayerInfoMenu extends Menu {

    private final User user;

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public String getTitle(Player player) {
        return "&cPlayer Information &f> " + user.getPlayer().getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        return null;
    }
}
