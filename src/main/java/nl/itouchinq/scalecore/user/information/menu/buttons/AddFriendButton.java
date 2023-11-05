package nl.itouchinq.scalecore.user.information.menu.buttons;

import com.cryptomorin.xseries.XMaterial;
import nl.fenixnetwerk.modules.menu.button.Button;
import nl.fenixnetwerk.modules.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AddFriendButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        // TODO item data
        return new ItemBuilder(XMaterial.PAPER.parseMaterial()).build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        player.sendMessage("Add friend button");
        player.closeInventory();
    }
}
