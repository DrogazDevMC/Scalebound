package nl.itouchinq.scalecore.user.command;

import nl.itouchinq.scalecore.ScaleCore;
import nl.itouchinq.scalecore.user.User;
import nl.itouchinq.scalecore.user.menu.PlayerInfoMenu;
import nl.itouchinq.scalecore.utilities.command.annotation.Command;
import nl.itouchinq.scalecore.utilities.command.annotation.Default;
import nl.itouchinq.scalecore.utilities.command.base.CommandBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@Command("userinfo")
public class UserInfoCommand extends CommandBase {

    @Default
    public void defaultCommand(final CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Geef een speler mee om user info op te vragen!");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("Er is geen speler informatie gevonden van " + args[0]);
            return;
        }

        User user = ScaleCore.get().getUserManager().getUsers().get(target.getUniqueId());

        if (sender instanceof ConsoleCommandSender) {
            // TODO geef alle informatie in tekst
        } else {
            Player player = (Player) sender;
            new PlayerInfoMenu(user).openMenu(player);

            player.sendMessage("Je bekijkt nu alle informatie van " + target.getName());
        }
    }
}
