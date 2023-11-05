package nl.itouchinq.scalecore.user.command;

import com.cryptomorin.xseries.XMaterial;
import nl.itouchinq.scalecore.utilities.command.annotation.Command;
import nl.itouchinq.scalecore.utilities.command.annotation.Default;
import nl.itouchinq.scalecore.utilities.command.base.CommandBase;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command("test")
public class TestCommand extends CommandBase {

    @Default
    public void defaultCommand(final Player player) {
        ItemDisplay item = player.getWorld().spawn(player.getLocation(), ItemDisplay.class);

        item.setItemStack(new ItemStack(XMaterial.PAPER.parseMaterial()));

    }
}
