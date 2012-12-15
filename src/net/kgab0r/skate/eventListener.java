package net.kgab0r.skate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class eventListener implements Listener{
	ItemStack boots;
	private skate plug;
	Location from, to;
	public static Material down,skate;
	public static String ws;
	public static Boolean allblock = false;
	public eventListener(skate plugin){
		this.plug = plugin;
		plug.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent evt) {
		Player player = evt.getPlayer();
		if(player.hasPermission("iceskate.skate")) {
		Location loc = player.getLocation();
		loc.setY(loc.getY()-1);
		Block b = loc.getBlock();
		
		loc.setY(loc.getY()-1.8d);
		Block b2 = loc.getBlock();
		Material mat2 = b2.getType(); 
		
		boots = player.getInventory().getBoots();
		Material btype = Material.AIR;
		if(boots!=null){
			btype = boots.getType();
		}else{
			player.setWalkSpeed((float) 0.2);
		}
		Material mat = b.getType();

		//if all block than we dont care with the block
		if(allblock){
			if(btype==skate){
				player.setWalkSpeed(Float.parseFloat(ws)); //BUG
			}
		}else{
		if(btype==skate){
			if(mat==Material.AIR && mat2 == down){
				player.setWalkSpeed(Float.parseFloat(ws)); //BUG
			}else if(mat==down){
				player.setWalkSpeed(Float.parseFloat(ws));
			}
			else{
				player.setWalkSpeed((float) 0.2);
			}
		}
		}
		}
		}
	
}
