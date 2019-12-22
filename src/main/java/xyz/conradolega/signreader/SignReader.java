package xyz.conradolega.signreader;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SignReader extends JavaPlugin {

    public static List<Material> SIGN_TYPES = Arrays.asList(
        Material.ACACIA_SIGN,
        Material.ACACIA_WALL_SIGN,
        Material.BIRCH_SIGN,
        Material.BIRCH_WALL_SIGN,
        Material.DARK_OAK_SIGN,
        Material.DARK_OAK_WALL_SIGN,
        Material.JUNGLE_SIGN,
        Material.JUNGLE_WALL_SIGN,
        Material.OAK_SIGN,
        Material.OAK_WALL_SIGN,
        Material.SPRUCE_SIGN,
        Material.SPRUCE_WALL_SIGN
    );
  
    @Override
    public void onEnable() {
        getLogger().info("SignReader plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("SignReader plugin disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return false;
        }

        Player player = (Player) sender;
        Location location = player.getLocation();
        World world = location.getWorld();
        int playerX = location.getBlockX();
        int playerZ = location.getBlockZ();
        for (int x = playerX - 100; x < playerX + 100; x++) {
            for (int y = 0; y < 256; y++) {
                for (int z = playerZ - 100; z < playerZ + 100; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    if (isSign(block)) {
                        Sign sign = (Sign) block.getState();
                        String text = String.join(" ", sign.getLines());
                        sender.sendMessage(text);
                    }
                }
            }
        }
        return true;
    }

    public boolean isSign(Block block) {
        return SIGN_TYPES.contains(block.getType());      
    }
}