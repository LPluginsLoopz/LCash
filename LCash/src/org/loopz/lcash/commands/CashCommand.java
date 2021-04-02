package org.loopz.lcash.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.loopz.lcash.Main;

public class CashCommand implements CommandExecutor {

	public String formatarCash(String jogador) {

		double CashDoJogador = Main.getPlugin(Main.class).DataBase.getCash(jogador);
		return Main.nf(CashDoJogador);
	}

	public String formatarCashDouble(Double quantia) {
		return Main.nf(quantia);
	}


	public ItemStack createCheque(String string, Player p) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cCheque de §7" + string);
        List<String> lore = new ArrayList<>();
        lore.add("§7Valor: §a" + string);

        lore.add("§7Tipo: §6Cash");
        lore.add("");
        lore.add("§7Clique com o botão direito para utilizar este cheque.");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
	 public Inventory openInv(Player p) {
		 Inventory inv = Bukkit.createInventory(null, 5*9, "§8Menu - Top cash(s)");
		    List<String> cashtop = Main.getPlugin(Main.class).DataBase.getCashTop();
		    p.openInventory(inv);
		    int i2 = 1;
		    int i = 20;
		    if(i == 24) {
		    	i = 29;
		    }
		    
		    ItemStack item3 = new ItemStack(Material.SKULL_ITEM, 1, 
		              (short)SkullType.PLAYER.ordinal());
		          SkullMeta mt4 = (SkullMeta)item3.getItemMeta();
		          mt4.setOwner(p.getName());
		         
	          mt4.setDisplayName("§aSuas informações: ");
	          ArrayList<String> lore4d = new ArrayList<>();
	          lore4d.add("");
	          lore4d.add("§fPosição:§7 Em desenvolvimento.");
	          lore4d.add("§fCash: §6"+ Main.nf(Main.getPlugin(Main.class).DataBase.getCash(p.getName())));
	          mt4.setLore(lore4d);
	          item3.setItemMeta(mt4);
	          inv.setItem(4, item3);
		    ItemStack item2 = new ItemStack(Material.BARRIER);
		          ItemMeta mt4d = item2.getItemMeta();
		          mt4d.setDisplayName("§cNinguém ocupando está vaga.");
		          item2.setItemMeta(mt4d);
		          inv.setItem(20, item2);
		          inv.setItem(21, item2);
		          inv.setItem(22, item2);
		          inv.setItem(23, item2);
		          inv.setItem(24, item2);
		          inv.setItem(29, item2);
		          inv.setItem(30, item2);
		          inv.setItem(31, item2);
		          inv.setItem(32, item2);
		          inv.setItem(33, item2);
		    if (cashtop != null)
		      
		        
		        for (String tops : cashtop) {
		          ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, 
		              (short)SkullType.PLAYER.ordinal());
		          SkullMeta mtd4d = (SkullMeta)item.getItemMeta();
		          mtd4d.setOwner(tops);
		          mtd4d.setDisplayName("§7" + tops);
		          ArrayList<String> lore4 = new ArrayList<>();
		          lore4.add("");
		          lore4.add("§fPosição:§7 " + i2 + "º Lugar");
		          lore4.add("§fCash: §6"+ Main.nf(Main.getPlugin(Main.class).DataBase.getCash(tops)));
		          mtd4d.setLore(lore4);
		          item.setItemMeta((ItemMeta)mtd4d);
		          inv.setItem(i, item);
		          if(i == 29) {
					    break;	
					    }
		          i2++;
		          i++;
		         
		        } 
		      
			return inv;  
		  }

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {
		
			Player p = (Player) s;
			switch (cmd.getName().toLowerCase()) {
			case "cash":
				if (args.length == 0) {
					p.sendMessage("§aCash: §6" + formatarCash(p.getName()) + "");
					break;
				}
				
				
				if (!args[0].equalsIgnoreCase("definir") 
						&& !args[0].equalsIgnoreCase("retirar")
						&& !args[0].equalsIgnoreCase("adicionar")&& !args[0].equalsIgnoreCase("set")
						&& !args[0].equalsIgnoreCase("enviar") && !args[0].equalsIgnoreCase("remove")
						 && !args[0].equalsIgnoreCase("pay")
						&& !args[0].equalsIgnoreCase("top") && !args[0].equalsIgnoreCase("add")
						&& !args[0].equalsIgnoreCase("cheque") && !args[0].equalsIgnoreCase("help")
						&& !args[0].equalsIgnoreCase("ajuda")) {
					if (!Main.getPlugin(Main.class).DataBase.exists(args[0])) {
						p.sendMessage("§cEsta conta não foi encontrada no banco de dados.");
						return true;
					}
					p.sendMessage("§aCash de §7" + args[0] + "§a: §6" + formatarCash(args[0]));
					return true;
				}

				

				

					
					switch (args[0].toLowerCase()) {
					case "ajuda":
						if(!p.hasPermission("LCash.admin")) {
							List<String> helpd = Main.getPlugin(Main.class).getConfig().getStringList("Messages.Help");
							for (String helppd : helpd) {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', helppd));
							}
						break;
						}
						List<String> helpp = Main.getPlugin(Main.class).getConfig().getStringList("Messages.HelpAdmin");
						for (String helpadm : helpp) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpadm));
						}
						break;
					case "help":
						if(!p.hasPermission("LCash.admin")) {
							List<String> helpd = Main.getPlugin(Main.class).getConfig().getStringList("Messages.Help");
							for (String helpp1 : helpd) {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpp1));
							}
						break;
						}
						List<String> helpp1 = Main.getPlugin(Main.class).getConfig().getStringList("Messages.HelpAdmin");
						for (String helpadm : helpp1) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', helpadm));
						}
						break;
					}
					switch (args[0].toLowerCase()) {
					case "top":
						p.openInventory(openInv(p));
						 

						break;
					}
					switch (args[0].toLowerCase()) {
					case "enviar":
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash enviar (jogador) (quantia)");
							break;
						}
						Player jogador = Bukkit.getPlayer(args[1]);
						
						if (jogador == null) {
							p.sendMessage("§cEste jogador está offline no momento.");
							break;
						}
						if (jogador.getName().equalsIgnoreCase(p.getName())) {
							p.sendMessage("§cVocê não pode enviar cash a sí mesmo.");
							break;
						}
						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}

						Double quantia = Double.valueOf(args[2]);
						String max2 = "999000000000000000000000000000000000000000000000000000000000000000000000000";
						if(quantia > Double.valueOf(max2)) {
							p.sendMessage("§cVocê execedeu o limite de valor permitido para enviar cash(s).");
							break;
						}
						if (Main.getPlugin(Main.class).DataBase.getCash(p.getName()) < quantia) {
							p.sendMessage("§cVocê não possui esta quantidade de cash para enviar.");
							break;
						}
						if(Main.getPlugin(Main.class).DataBase.getCash(jogador.getName()) >= Double.valueOf(max2)) {
							p.sendMessage("§cEste jogador já atingiu o máximo de cash(s), e não pode receber mais.");
							break;
						}
						p.sendMessage("§aVocê enviou §6" + formatarCashDouble(quantia)
								+ " §acash(s) para o jogador §7" + jogador.getName());
						jogador.sendMessage("§aVocê recebeu §6" + formatarCashDouble(quantia)
								+ " §acash(s) do jogador §7" + p.getName());
						Main.getPlugin(Main.class).DataBase.adicionarCash(jogador.getName(), quantia);
						Main.getPlugin(Main.class).DataBase.removerCash(p.getName(), quantia);

						
						
						break;
					case "pay":
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash enviar (jogador) (quantia)");
							break;
						}
						Player jogador1 = Bukkit.getPlayer(args[1]);
						
						if (jogador1 == null) {
							p.sendMessage("§cEste jogador está offline no momento.");
							break;
						}
						if (jogador1.getName().equalsIgnoreCase(p.getName())) {
							p.sendMessage("§cVocê não pode enviar cash a sí mesmo.");
							break;
						}
						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}

						Double quantia1 = Double.valueOf(args[2]);
						String max = "999000000000000000000000000000000000000000000000000000000000000000000000000";
						if(quantia1 > Double.valueOf(max)) {
							p.sendMessage("§cVocê execedeu o limite de valor permitido para enviar cash(s).");
							break;
						}
						if (Main.getPlugin(Main.class).DataBase.getCash(p.getName()) < quantia1) {
							p.sendMessage("§cVocê não possui esta quantidade de cash para enviar.");
							break;
						}
						if(Main.getPlugin(Main.class).DataBase.getCash(jogador1.getName()) >= Double.valueOf(max)) {
							p.sendMessage("§cEste jogador já atingiu o máximo de cash(s), e não pode receber mais.");
							break;
						}
						p.sendMessage("§aVocê enviou §6" + formatarCashDouble(quantia1)
								+ " §acash(s) para o jogador §7" + jogador1.getName());
						jogador1.sendMessage("§aVocê recebeu §6" + formatarCashDouble(quantia1)
								+ " §acash(s) do jogador §7" + p.getName());
						Main.getPlugin(Main.class).DataBase.adicionarCash(jogador1.getName(), quantia1);
						Main.getPlugin(Main.class).DataBase.removerCash(p.getName(), quantia1);

						
						
						break;
					}
			

	
						
					

					switch (args[0].toLowerCase()) {
					case "definir":
						
						if (!p.hasPermission("LCash.admin")) {
							p.sendMessage("§cVocê não possui permissão para executar este comando.");
							break;
						}
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash definir (jogador) (quantia)");
							break;
						}
						if (!Main.getPlugin(Main.class).DataBase.exists(args[1])) {
							p.sendMessage("§cEsta conta não foi encontrada no banco de dados.");
							break;
						}

						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}
						
						Double quantia = Double.valueOf(args[2]);
						String max = "999000000000000000000000000000000000000000000000000000000000000000000000000";
						if(quantia > Double.valueOf(max)) {
							p.sendMessage("§cVocê execedeu o limite de valor permitido para setar cash(s).");
							break;
						}
						p.sendMessage("§aVocê definiu o saldo de §7" + args[1] + " §apara §6"
								+ formatarCashDouble(quantia) + " §acash(s).");
						Main.getPlugin(Main.class).DataBase.definirCash(args[1], quantia);

						break;
					case "set":
						
						if (!p.hasPermission("LCash.admin")) {
							p.sendMessage("§cVocê não possui permissão para executar este comando.");
							break;
						}
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash definir (jogador) (quantia)");
							break;
						}
						if (!Main.getPlugin(Main.class).DataBase.exists(args[1])) {
							p.sendMessage("§cEsta conta não foi encontrada no banco de dados.");
							break;
						}

						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}

						Double quantia1 = Double.valueOf(args[2]);
						String max2 = "999000000000000000000000000000000000000000000000000000000000000000000000000";
						if(quantia1 > Double.valueOf(max2)) {
							p.sendMessage("§cVocê execedeu o limite de valor permitido para setar cash(s).");
							break;
						}
						p.sendMessage("§aVocê definiu o saldo de §7" + args[1] + " §apara §6"
								+ formatarCashDouble(quantia1) + " §acash(s).");
						Main.getPlugin(Main.class).DataBase.definirCash(args[1], quantia1);

						break;
					}
					
					switch (args[0].toLowerCase()) {
					case "cheque":
						if(args.length < 2) {
							p.sendMessage("§cUtilize: /cash cheque (quantia)");
							break;
						}
						try {

							@SuppressWarnings("unused")
							Double quantia = Double.valueOf(args[1]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}
						Double quantia = Double.valueOf(args[1]);
						
						if(Main.getPlugin(Main.class).DataBase.getCash(p.getName()) < quantia) {
							p.sendMessage("§cVocê não possui esse valor em cash(s).");
							break;
						}
						String max = "999000000000000000000000000000000000000000000000000000000000000000000000000";
						if(quantia > Double.valueOf(max)) {
							p.sendMessage("§cVocê execedeu o limite de valor permitido para criar cheque.");
							break;
						}
						if (p.getInventory().firstEmpty() == -1) {
							p.sendMessage("§cSeu inventario não pode estar cheio para criar um cheque.");
							break;
						}
						
						


						Double quantia2 = Double.valueOf(args[1]);
						Main.getPlugin(Main.class).DataBase.removerCash(p.getName(), quantia2);
						
						p.sendMessage("§aVocê criou um cheque no valor de §6" + Main.nf(quantia2));
						p.getInventory().addItem(createCheque(Main.nf(quantia2), p));
						
						break;
						
					}

					switch (args[0].toLowerCase()) {
					case "adicionar":
						
						if (!p.hasPermission("LCash.admin")) {
							p.sendMessage("§cVocê não possui permissão para executar este comando.");
							break;
						}
						
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash adicionar (jogador) (quantia)");
							break;
						}
						if (!Main.getPlugin(Main.class).DataBase.exists(args[1])) {
							p.sendMessage("§cEsta conta não foi encontrada no banco de dados.");
							break;
						}

						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}

						Double quantia = Double.valueOf(args[2]);
						String max = "999000000000000000000000000000000000000000000000000000000000000000000000000";
						if(quantia > Double.valueOf(max)) {
							p.sendMessage("§cVocê execedeu o limite de valor permitido para adicionar cash(s).");
							break;
						}
						if(Main.getPlugin(Main.class).DataBase.getCash(args[1]) >= Double.valueOf(max)) {
							p.sendMessage("§cEste jogador já atingiu o máximo de cash(s), e não pode receber mais.");
							break;
						}
						p.sendMessage("§aVocê adicionou §6" + formatarCashDouble(quantia)
								+ " §acash(s) no saldo de §7" + args[1]);
						Main.getPlugin(Main.class).DataBase.adicionarCash(args[1], quantia);

						break;
					case "add":
						
						if (!p.hasPermission("LCash.admin")) {
							p.sendMessage("§cVocê não possui permissão para executar este comando.");
							break;
						}
						
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash adicionar (jogador) (quantia)");
							break;
						}
						if (!Main.getPlugin(Main.class).DataBase.exists(args[1])) {
							p.sendMessage("§cEsta conta não foi encontrada no banco de dados.");
							break;
						}

						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}

						Double quantia1 = Double.valueOf(args[2]);
						String max2 = "999000000000000000000000000000000000000000000000000000000000000000000000000";
						if(quantia1 > Double.valueOf(max2)) {
							p.sendMessage("§cVocê execedeu o limite de valor permitido para adicionar cash(s).");
							break;
						}
						if(Main.getPlugin(Main.class).DataBase.getCash(args[1]) >= Double.valueOf(max2)) {
							p.sendMessage("§cEste jogador já atingiu o máximo de cash(s), e não pode receber mais.");
							break;
						}
						p.sendMessage("§aVocê adicionou §6" + formatarCashDouble(quantia1)
								+ " §acash(s) no saldo de §7" + args[1]);
						Main.getPlugin(Main.class).DataBase.adicionarCash(args[1], quantia1);

						break;
					}

					switch (args[0].toLowerCase()) {
					case "retirar":
						
						if (!p.hasPermission("LCash.admin")) {
							p.sendMessage("§cVocê não possui permissão para executar este comando.");
							break;
						}
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash retirar (jogador) (quantia)");
							break;
						}
						if (!Main.getPlugin(Main.class).DataBase.exists(args[1])) {
							p.sendMessage("§cEsta conta não foi encontrada no banco de dados.");
							break;
						}
						
						

						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}

						Double quantia = Double.valueOf(args[2]);
						if(Main.getPlugin(Main.class).DataBase.getCash(args[1]) < quantia) {
							p.sendMessage("§cEste jogador não possui este saldo.");
							break;
						}
						p.sendMessage("§aVocê removeu §6" +  formatarCashDouble(quantia) + "§a cash(s) do jogador");
						Main.getPlugin(Main.class).DataBase.removerCash(args[1], quantia);

						break;
					case "remove":
						
						if (!p.hasPermission("LCash.admin")) {
							p.sendMessage("§cVocê não possui permissão para executar este comando.");
							break;
						}
						if(args.length < 3) {
							p.sendMessage("§cUtilize: /cash retirar (jogador) (quantia)");
							break;
						}
						if (!Main.getPlugin(Main.class).DataBase.exists(args[1])) {
							p.sendMessage("§cEsta conta não foi encontrada no banco de dados.");
							break;
						}
						
					

						try {

							@SuppressWarnings("unused")
							Double a = Double.valueOf(args[2]);

						} catch (Exception e) {
							p.sendMessage("§cVocê deve inserir somente numeros.");
							break;
						}

						Double quantia1 = Double.valueOf(args[2]);
						if(Main.getPlugin(Main.class).DataBase.getCash(args[1]) < quantia1) {
							p.sendMessage("§cEste jogador não possui este saldo.");
							break;
						}
						p.sendMessage("§aVocê removeu §6" +  formatarCashDouble(quantia1) + "§a cash(s) do jogador");
						Main.getPlugin(Main.class).DataBase.removerCash(args[1], quantia1);

						break;
					}

			}
					return false;
				
			
			
	}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

