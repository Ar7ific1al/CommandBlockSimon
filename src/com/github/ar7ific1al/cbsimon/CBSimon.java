package com.github.ar7ific1al.cbsimon;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CBSimon extends JavaPlugin	{
	
	public static Logger console = Logger.getLogger("Minecraft");
	
	public String version;
	
	public CBSimonListener cbslistener = new CBSimonListener(this);
	
	@Override
	public void onEnable()	{
		PluginManager pm = Bukkit.getServer().getPluginManager();
		PluginDescriptionFile pdFile = this.getDescription();
		String ver = pdFile.getVersion();
		version = ver;
		String enabled = "[CBS] CraftBlock Simon v." + ver + " enabled.";
		console.info(enabled);
		pm.registerEvents(cbslistener, this);
	}
	
	@Override
	public void onDisable()	{
		String disabled = "[CBS] CraftBlock Simon v." + version + " disabled.";
		console.info(disabled);	
	}
	
	public static String colorMessage(String message, String arg)	{
		String coloredMessage = message;
		coloredMessage = coloredMessage.replaceAll("&1", "\u00A71");
		coloredMessage = coloredMessage.replaceAll("&2", "\u00A72");
		coloredMessage = coloredMessage.replaceAll("&3", "\u00A73");
		coloredMessage = coloredMessage.replaceAll("&4", "\u00A74");
		coloredMessage = coloredMessage.replaceAll("&5", "\u00A75");
		coloredMessage = coloredMessage.replaceAll("&6", "\u00A76");
		coloredMessage = coloredMessage.replaceAll("&7", "\u00A77");
		coloredMessage = coloredMessage.replaceAll("&8", "\u00A78");
		coloredMessage = coloredMessage.replaceAll("&9", "\u00A79");
		coloredMessage = coloredMessage.replaceAll("&0", "\u00A70");
		coloredMessage = coloredMessage.replaceAll("&a", "\u00A7a");
		coloredMessage = coloredMessage.replaceAll("&b", "\u00A7b");
		coloredMessage = coloredMessage.replaceAll("&c", "\u00A7c");
		coloredMessage = coloredMessage.replaceAll("&d", "\u00A7d");
		coloredMessage = coloredMessage.replaceAll("&e", "\u00A7e");
		coloredMessage = coloredMessage.replaceAll("&f", "\u00A7f");
		coloredMessage = coloredMessage.replaceAll("&k", "\u00A7k");
		coloredMessage = coloredMessage.replaceAll("&l", "\u00A7l");
		coloredMessage = coloredMessage.replaceAll("&m", "\u00A7m");
		coloredMessage = coloredMessage.replaceAll("&n", "\u00A7n");
		coloredMessage = coloredMessage.replaceAll("&o", "\u00A7o");
		coloredMessage = coloredMessage.replaceAll("&r", "\u00A7r");
		if (arg != null)	{
			coloredMessage = coloredMessage.replaceAll("%p", arg);
		}
		return coloredMessage;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args)	{
		if (cmdLabel.equalsIgnoreCase("cbs"))	{
			try	{
				if (sender instanceof Player)	{
					Player player = (Player) sender;
							if (args.length < 1)	{
								player.sendMessage(colorMessage("&6=====================================================\n&cCommandBlock Simon&e Version &c" + version + " &eby &cAr7ific1al&e.\nCBSimon is intended for sending formatted messages to players via Command Blocks.\n&6=====================================================", null));
							}
							else if (args.length > 1)	{
								if (player.hasPermission("cbsimon.use"))	{
									if (args[0].equalsIgnoreCase("@a")){
										String unformattedMessage = "";
										for (int i = 1; i < args.length; ++i)	{
											unformattedMessage += args[i];
											if (i < args.length)	{
												unformattedMessage += " ";
											}
										}
										
										String finalMessage[] = unformattedMessage.split("%n");
										for (String s : finalMessage)	{
											Bukkit.getServer().broadcastMessage(colorMessage(s, null));
										}
									}
									else if (Bukkit.getServer().getPlayer(args[0]) == null)	{
										sender.sendMessage(ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Player \"" + args[1] + "\" not found!");
									}
									else	{
										Player recipient = Bukkit.getServer().getPlayer(args[0]);
										String unformattedMessage = "";
										for (int i = 1; i < args.length; ++i)	{
											unformattedMessage += args[i];
											if (i < args.length)	{
												unformattedMessage += " ";
											}
										}
										
										String finalMessage[] = unformattedMessage.split("%n");
										for (String s : finalMessage)	{
											recipient.sendMessage(colorMessage(s, recipient.getName()));
										}
									}
								}
							}
					}
				else{
					if (args.length > 0)	{
						if (args[0].equalsIgnoreCase("@a")){
							String unformattedMessage = "";
							for (int i = 1; i < args.length; ++i)	{
								unformattedMessage += args[i];
								if (i < args.length)	{
									unformattedMessage += " ";
								}
							}
							
							String finalMessage[] = unformattedMessage.split("%n");
							for (String s : finalMessage)	{
								Bukkit.getServer().broadcastMessage(colorMessage(s, null));
							}
						}
						else if (Bukkit.getServer().getPlayer(args[0]) == null)	{
							sender.sendMessage("[CBSimon] " + ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Player \"" + args[1] + "\" not found!");
						}
						else	{
							Player recipient = Bukkit.getServer().getPlayer(args[0]);
							String unformattedMessage = "";
							for (int i = 1; i < args.length; ++i)	{
								unformattedMessage += args[i];
								if (i < args.length)	{
									unformattedMessage += " ";
								}
							}
							
							String finalMessage[] = unformattedMessage.split("%n");
							for (String s : finalMessage)	{
								recipient.sendMessage(colorMessage(s, recipient.getName()));
							}
						}
		
					}
				}
			} catch (IndexOutOfBoundsException ex)	{
				return false;
			}
		}
		return false;
	}
}
