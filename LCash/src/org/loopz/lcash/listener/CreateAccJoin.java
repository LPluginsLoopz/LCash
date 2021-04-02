package org.loopz.lcash.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.loopz.lcash.Main;

public class CreateAccJoin implements Listener {

	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		if(!Main.getPlugin(Main.class).DataBase.exists(e.getPlayer().getName())) {
			Main.getPlugin(Main.class).DataBase.createPlayer(e.getPlayer().getName());
		}
	}
	
}
