package org.loopz.lcash.listener;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.loopz.lcash.Main;

public class ChequeClick implements Listener {
	


	  @EventHandler
	  public void onInteract(PlayerInteractEvent e) {
	    Player p = e.getPlayer();
	    Action a = e.getAction();
	    if(!p.getItemInHand().hasItemMeta()) {
	    	return;
	    }
	    if(!p.getItemInHand().getType().equals(Material.PAPER)) {
	    	return;
	    }
	    if(p.getItemInHand().getItemMeta().getLore() == null) {
	    	return;
	    }
	    
	    if (a.equals(Action.RIGHT_CLICK_AIR) || a.equals(Action.RIGHT_CLICK_BLOCK)) {
	      String price = null;
	      for (String s : e.getPlayer().getItemInHand().getItemMeta().getLore()) {
	        if (s.startsWith("§7Valor: ")) {
	          price = ChatColor.stripColor(s).replace("Valor: ", "");

	          String max = "999000000000000000000000000000000000000000000000000000000000000000000000000";
	          double dprice = Double.valueOf((Main.getFormatted(price)));
	          if(Main.getPlugin(Main.class).DataBase.getCash(p.getName()) >= Double.valueOf(max)) {
					p.sendMessage("§cVocê já atingiu o máximo de cash(s), e não pode receber mais.");
					break;
				}
	            p.sendMessage("§aFoi adicionado §6" + Main.nf(dprice) + " §acash(s) em sua conta.");
	          Main.getPlugin(Main.class).DataBase.adicionarCash(p.getName(), dprice);
	          if (p.getItemInHand().getAmount() > 1) p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
	          else p.getInventory().removeItem(p.getItemInHand());
	          
	        } 
	      }
	    } 
	  }
	  @EventHandler
	  public void Inventory(InventoryClickEvent e) {
			if (e.getInventory().getTitle().equalsIgnoreCase("§8Menu - Top Cash(s)")) {
				e.setCancelled(true);
				if (e.getCurrentItem() == null)
					return;
				if (!e.getCurrentItem().hasItemMeta())
					return;
				e.setCancelled(true);
			}
	  }
}