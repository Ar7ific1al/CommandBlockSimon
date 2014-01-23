package com.github.ar7ific1al.cbsimon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Button;

public class CBSimonListener implements Listener	{
	
	public CBSimon plugin;

	public CBSimonListener(CBSimon instance) {
		plugin = instance;
		CBSimon.console.info("[CBSimon] CommandBlock Simon listener registered.");
	}
	
	public void cbsimon(Block attached, Player user)	{
		CommandBlock commandBlock = (CommandBlock) attached.getState();
		String[] command = commandBlock.getCommand().split(" ");
		if (command[0].equalsIgnoreCase("cbs"))	{
			if (command[1].equalsIgnoreCase("%p"))	{				
				String finalCommand = "cbs " + user.getName() + " ";
				for (int i = 2; i < command.length; ++i)	{
					finalCommand += command[i];
					if (i < command.length)	{
						finalCommand += " ";
					}
				}
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), finalCommand);
			}
			else	{
				return;
			}
		}
	}
	
	@EventHandler
	public void onButtonPress(PlayerInteractEvent event) throws ArrayIndexOutOfBoundsException	{
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))	{
			Button button;
			if (event.getClickedBlock().getType() == Material.STONE_BUTTON || event.getClickedBlock().getType() == Material.WOOD_BUTTON)	{
				Block block = event.getClickedBlock();
				button = (Button) block.getState().getData();
				Block attached = block.getRelative(button.getAttachedFace());
				if (attached.getType().equals(Material.COMMAND))	{
					String[] command = ((CommandBlock) attached.getState()).getCommand().split(" ");
					if (command[0].equalsIgnoreCase("cbs") && command.length > 1)	{
						cbsimon(attached, event.getPlayer());
					}
				}
				else	{
					for (BlockFace f : BlockFace.values())	{
						if (f.equals(BlockFace.UP) || f.equals(BlockFace.DOWN) || f.equals(BlockFace.NORTH) || f.equals(BlockFace.SOUTH)
								|| f.equals(BlockFace.EAST) || f.equals(BlockFace.WEST))	{
							if (attached.getRelative(f).getType().equals(Material.COMMAND))	{
								CommandBlock commandBlock = (CommandBlock) attached.getRelative(f).getState();
								String[] command = commandBlock.getCommand().split(" ");
								if (command[0].equalsIgnoreCase("cbs") && command.length > 1)	{
									if (command[1].equalsIgnoreCase("%p"))	{
										cbsimon(attached.getRelative(f), event.getPlayer());
										event.setCancelled(true);
									}
								}
								else if (command[0].equalsIgnoreCase("sudo") && command.length > 1)	{
									if (command[1].equalsIgnoreCase("%p"))	{
										String args = "";
										for (int i = 2; i < command.length; ++i)	{
											if (command[i].toLowerCase() != "sudo" && command[i].toLowerCase() != "%p")	{
												args += command[i];
											}
											if (i < command.length)	{
												args += " ";
											}
										}
										event.setCancelled(true);
										Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + event.getPlayer().getName() + " " + args);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
}
