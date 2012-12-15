package net.kgab0r.skate;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
public final class skate extends JavaPlugin {
	
	public void onEnable(){
		getLogger().info("IceSkate enabled!");
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		new eventListener(this);
		
		reloadcfg();
		
	}
 
	private void reloadcfg() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		if(getConfig().getBoolean("enabled")){
			 eventListener.ws = getConfig().getString("speed");
			 getLogger().info("Speed set to: "+eventListener.ws);
			// goon
			Material downblock = Material.getMaterial(getConfig().getInt("downblock"));
			Material skate = Material.getMaterial(getConfig().getInt("skate"));
			Boolean allblock = getConfig().getBoolean("allblock");
			
			//passtoel
			eventListener.down = downblock;
			eventListener.skate = skate;
			eventListener.allblock = allblock;
			
			//echo
			if (allblock){
				getLogger().info("DownBlock set to: ALLBLOCK");
			}else{
				getLogger().info("DownBlock set to: "+downblock);
			}
			getLogger().info("Skate set to: "+skate);			 
}else{
	this.setEnabled(false);
}
	}

	public void onDisable(){
		getLogger().info("IceSkate disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!sender.isOp())
			return true;
		final String commandStr = cmd.getName().toLowerCase();
		if(commandStr.equalsIgnoreCase("is")){
			if (args.length != 1){
				return false;}
			else if(args[0].equalsIgnoreCase("reload")){
				this.reloadConfig();
				reloadcfg();
				sender.getServer().broadcastMessage("[Skate] reloaded!");
				return true;
			}
			sender.getServer().broadcastMessage("[Skate] Command not found!");
			return false;
		}
		return true; 
	}
}
