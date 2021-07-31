package ro.fasttrackit.plugindemo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;

public class Main extends JavaPlugin implements Listener {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

    }

    @Override
    public void onEnable() {
        System.out.println("TEST PLUGIN ENABLED");

        getCommand("heal").setExecutor(new HealCommand());

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println("TEST PLUGIN DISABLED");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().toLowerCase(Locale.ROOT).equals("hello")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                player.sendMessage(ChatColor.DARK_RED + "Hello from the othersiiiiiiiide" +
                        ChatColor.DARK_AQUA + ", " + player.getName() + ". Your health has been restored");

                player.setHealth(20D);
            } else {
                System.out.println("You cannot use this command through the console");
            }
        } else if (cmd.getName().toLowerCase(Locale.ROOT).equals("config")) {
            String word = this.getConfig().getString("Word");
            int number = this.getConfig().getInt("Number");

            Player player = (Player) sender;

            player.sendMessage(ChatColor.GRAY + "The word is " + ChatColor.DARK_GREEN + word
             + ChatColor.GRAY + " and the number is " + ChatColor.DARK_RED + number);
        }

        return false;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("testplugin.move")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onThrow(PlayerEggThrowEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(ChatColor.RED + "Bithc why you throwin me? RIP " + event.getEgg().getName());
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        player.setWalkSpeed(8);
    }
}
