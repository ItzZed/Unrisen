package com.theunrisen.UnrisenCore.Commands;

import com.theunrisen.UnrisenCore.Main;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.theunrisen.UnrisenCore.Main.plugin;
import static org.bukkit.Bukkit.getServer;

public class CommandRewards implements CommandExecutor, Listener {
    Random rn = new Random();
    public static Economy economy = null;


    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);

        if (economyProvider != null) {

            economy = (Economy)economyProvider.getProvider();

        }

        return (economy != null);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player clicker = (Player)event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (inventory.equals(rewardGui)) {
            if (event.getRawSlot() > 26) {
                event.setCancelled(true);
            } else {
                if (event.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                    clicker.closeInventory();
                    Integer randomInt = rn.nextInt(10 - 1 + 1) + 1;
                    if(randomInt == 1 || randomInt == 2 || randomInt == 3 || randomInt == 4 || randomInt == 5 || randomInt == 6) {
                        Double firstReward = 25000.00;
                        clicker.sendMessage("§aYou have claimed your daily rewards");
                        Bukkit.broadcastMessage("§7[§d§lUnrisen§7] §a§l" + clicker.getName() + " §6has redeemed their Daily Rewards and got §a§l15k");
                        getServer().dispatchCommand(getServer().getConsoleSender(), "eco give " + clicker.getName() + " 15000");
                        plugin.getConfig().set(clicker.getName(), System.currentTimeMillis());
                        plugin.saveConfig();

                    }
                    if (randomInt == 7 || randomInt == 8 || randomInt == 9) {
                        Double secondReward = 50000.00;
                        clicker.sendMessage("§aYou have claimed your daily rewards");
                        Bukkit.broadcastMessage("§7[§d§lUnrisen§7] §a§l" + clicker.getName() + " §6has redeemed their Daily Rewards and got §a§l50k");
                        getServer().dispatchCommand(getServer().getConsoleSender(), "eco give " + clicker.getName() + " 50000");
                        plugin.getConfig().set(clicker.getName(), System.currentTimeMillis());
                        plugin.saveConfig();
                    }
                    if (randomInt==10){
                        Double thirdReward = 100000.00;
                        clicker.sendMessage("§aYou have claimed your daily rewards");
                        Bukkit.broadcastMessage("§7[§d§lUnrisen§7] §a§l" + clicker.getName() + " §6has redeemed their Daily Rewards and got §a§l100k");
                        getServer().dispatchCommand(getServer().getConsoleSender(), "eco give " + clicker.getName() + " 100000");
                        plugin.getConfig().set(clicker.getName(), System.currentTimeMillis());
                        plugin.saveConfig();
                    }


                }
            }
        } else if (inventory.equals(rewardGuiFalse)) {
            if (event.getRawSlot() > 26) {
                event.setCancelled(true);
            } else {
                if (event.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                    event.setCancelled(true);
                } else {
                    Long time = plugin.getConfig().getLong(plugin.getName());
                    Long TimeLeft = ((time + 86400000) - System.currentTimeMillis()) / 1000;
                    if (TimeLeft < 0) {
                        clicker.closeInventory();
                    } else {
                        ItemStack ClaimReward = new ItemStack(Material.BARRIER);
                        ItemMeta ClaimRewardM = ClaimReward.getItemMeta();
                        ClaimRewardM.setDisplayName("Daily Rewards §7(§cUn-Available§7)");
                        ArrayList<String> ClaimRewardLore = new ArrayList<String>();
                        ClaimRewardLore.add("§cYou can claim your daily reward in " + TimeLeft + " seconds");
                        ClaimRewardM.setLore(ClaimRewardLore);
                        ClaimRewardM.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
                        ClaimReward.setItemMeta(ClaimRewardM);
                        ClaimReward.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                        rewardGuiFalse.setItem(13, ClaimReward);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rewards")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if (plugin.getConfig().contains(p.getName())) {
                        Long time = plugin.getConfig().getLong(p.getName());
                        if (System.currentTimeMillis() > time + 86400000) {
                            p.sendMessage("§7[§dUnrisen§7] Opening Daily Rewards Menu...");
                            ItemStack ClaimReward = new ItemStack(Material.DIAMOND);
                            ItemMeta ClaimRewardM = ClaimReward.getItemMeta();
                            ClaimRewardM.setDisplayName("Daily Rewards §7(§aAvailable§7)");
                            ArrayList<String> ClaimRewardlore = new ArrayList<String>();
                            ClaimRewardlore.add("§7(§eRight-Click§7) §ato claim your daily rewards");
                            ClaimRewardlore.add("§a15k §7>§6 60%");
                            ClaimRewardlore.add("§a50k §7>§6 30%");
                            ClaimRewardlore.add("§a100k §7>§6 10%");
                            ClaimRewardM.setLore(ClaimRewardlore);
                            ClaimRewardM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                            ClaimReward.setItemMeta(ClaimRewardM);
                            ClaimReward.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                            rewardGui.setItem(13, ClaimReward);
                            p.openInventory(rewardGui);

                        } else {
                            p.sendMessage("§7[§dUnrisen§7] Opening Daily Rewards Menu...");
                            Long TimeLeft = ((time + 86400000) - System.currentTimeMillis());
                            String TimeLefthms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(TimeLeft),
                                    TimeUnit.MILLISECONDS.toMinutes(TimeLeft) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(TimeLeft)),
                                    TimeUnit.MILLISECONDS.toSeconds(TimeLeft) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(TimeLeft)));
                            ItemStack ClaimReward = new ItemStack(Material.BARRIER);
                            ItemMeta ClaimRewardM = ClaimReward.getItemMeta();
                            ClaimRewardM.setDisplayName("Daily Rewards §7(§cUn-Available§7)");
                            ArrayList<String> ClaimRewardLore = new ArrayList<String>();
                            ClaimRewardLore.add("§cYou can claim your daily reward in " + TimeLefthms + " (Hours:Minutes:Seconds)");
                            ClaimRewardM.setLore(ClaimRewardLore);
                            ClaimRewardM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                            ClaimReward.setItemMeta(ClaimRewardM);
                            ClaimReward.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                            rewardGuiFalse.setItem(13, ClaimReward);
                            p.openInventory(rewardGuiFalse);
                        }
                    } else {
                        p.sendMessage("§7[§dUnrisen§7] Opening Daily Rewards Menu...");
                        ItemStack ClaimReward = new ItemStack(Material.DIAMOND);
                        ItemMeta ClaimRewardM = ClaimReward.getItemMeta();
                        ClaimRewardM.setDisplayName("Daily Rewards §7(§aAvailable§7)");
                        ArrayList<String> ClaimRewardlore = new ArrayList<String>();
                        ClaimRewardlore.add("§7(§eRight-Click§7) §6to claim your daily rewards");
                        ClaimRewardlore.add("§a15k §7>§6 60%");
                        ClaimRewardlore.add("§a50k §7>§6 30%");
                        ClaimRewardlore.add("§a100k §7>§6 10%");
                        ClaimRewardM.setLore(ClaimRewardlore);
                        ClaimRewardM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                        ClaimReward.setItemMeta(ClaimRewardM);
                        ClaimReward.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                        rewardGui.setItem(13, ClaimReward);
                        p.openInventory(rewardGui);
                    }
                } else {
                    sender.sendMessage("§cConsole cannot run this command");
                }
            } else {
                if (sender.isOp()) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        sender.sendMessage("§4§lERROR §7You either entered the players name incorrectly or the player is not online");
                    } else {
                        if (plugin.getConfig().contains(target.getName())) {
                            Long time = plugin.getConfig().getLong(target.getName());
                            if (System.currentTimeMillis() > time + 86400000) {
                                target.sendMessage("§7[§dUnrisen§7] Opening Daily Rewards Menu...");
                                ItemStack ClaimReward = new ItemStack(Material.DIAMOND);
                                ItemMeta ClaimRewardM = ClaimReward.getItemMeta();
                                ClaimRewardM.setDisplayName("Daily Rewards §7(§aAvailable§7)");
                                ArrayList<String> ClaimRewardlore = new ArrayList<String>();
                                ClaimRewardlore.add("§7(§eRight-Click§7) §6to claim your daily rewards");
                                ClaimRewardlore.add("§a15k §7>§6 60%");
                                ClaimRewardlore.add("§a50k §7>§6 30%");
                                ClaimRewardlore.add("§a100k §7>§6 10%");
                                ClaimRewardM.setLore(ClaimRewardlore);
                                ClaimRewardM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                                ClaimReward.setItemMeta(ClaimRewardM);
                                ClaimReward.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                                rewardGui.setItem(13, ClaimReward);
                                target.openInventory(rewardGui);
                            } else {
                                target.sendMessage("§7[§dUnrisen§7] Opening Daily Rewards Menu...");
                                Long TimeLeft = ((time + 86400000) - System.currentTimeMillis());
                                String TimeLefthms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(TimeLeft),
                                        TimeUnit.MILLISECONDS.toMinutes(TimeLeft) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(TimeLeft)),
                                        TimeUnit.MILLISECONDS.toSeconds(TimeLeft) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(TimeLeft)));
                                ItemStack ClaimReward = new ItemStack(Material.BARRIER);
                                ItemMeta ClaimRewardM = ClaimReward.getItemMeta();
                                ClaimRewardM.setDisplayName("Daily Rewards §7(§cUn-Available§7)");
                                ArrayList<String> ClaimRewardLore = new ArrayList<String>();
                                ClaimRewardLore.add("§cYou can claim your daily reward in " + TimeLefthms + " (Hours:Minutes:Seconds)");
                                ClaimRewardM.setLore(ClaimRewardLore);
                                ClaimRewardM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                                ClaimReward.setItemMeta(ClaimRewardM);
                                ClaimReward.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                                rewardGuiFalse.setItem(13, ClaimReward);
                                target.openInventory(rewardGuiFalse);
                            }
                        } else {
                            target.sendMessage("§7[§dUnrisen§7] Opening Daily Rewards Menu...");
                            ItemStack ClaimReward = new ItemStack(Material.DIAMOND);
                            ItemMeta ClaimRewardM = ClaimReward.getItemMeta();
                            ClaimRewardM.setDisplayName("Daily Rewards §7(§aAvailable§7)");
                            ArrayList<String> ClaimRewardlore = new ArrayList<String>();
                            ClaimRewardlore.add("§7(§eRight-Click§7) §6to claim your daily rewards");
                            ClaimRewardlore.add("§a15k §7>§6 60%");
                            ClaimRewardlore.add("§a50k §7>§6 30%");
                            ClaimRewardlore.add("§a100k §7>§6 10%");
                            ClaimRewardM.setLore(ClaimRewardlore);
                            ClaimRewardM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
                            ClaimReward.setItemMeta(ClaimRewardM);
                            ClaimReward.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
                            rewardGui.setItem(13, ClaimReward);
                            target.openInventory(rewardGui);
                            sender.sendMessage("Inventory has been opened for" + target.getName());
                        }
                    }
                } else {
                    sender.sendMessage("§cYou do not have permission to open the gui for someone else");
                }
            }

        }
        return false;
    }
    public static Inventory rewardGui = Bukkit.createInventory(null, 27, ChatColor.RED + "Daily Rewards");
    static {
        rewardGui.setItem(0, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(1, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(2, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(3, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(4, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(5, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(6, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(7, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(8, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(18, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(19, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(20, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(21, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(22, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(23, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(24, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(25, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));
        rewardGui.setItem(26, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5));


    }
    public static Inventory rewardGuiFalse = Bukkit.createInventory(null, 27, ChatColor.RED + "Daily Rewards");
    static {
        rewardGuiFalse.setItem(0, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(1, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(2, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(3, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(4, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(5, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(6, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(7, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(8, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(14 , new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(18, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(19, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(20, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(21, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(22, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(23, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(24, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(25, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));
        rewardGuiFalse.setItem(26, new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14));


    }
}
