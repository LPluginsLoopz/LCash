package org.loopz.lcash;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.loopz.lcash.commands.CashCommand;
import org.loopz.lcash.listener.ChequeClick;
import org.loopz.lcash.listener.CreateAccJoin;

public class Main extends JavaPlugin {
	
	public Conexao Conexao;
	public DataBase DataBase;
	public void onEnable() {
		loadConfig();
		this.Conexao = new Conexao();
		this.DataBase = new DataBase(this);
		Conexao.openConnectionSQLITE();
		getCommand("cash").setExecutor(new CashCommand());
		Bukkit.getServer().getPluginManager().registerEvents(new CreateAccJoin(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ChequeClick(), this);
	}
	

	@Override
	public void onDisable() {
		Conexao.disconnect();
	}
	public void loadConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	@SuppressWarnings("serial")
	public static Map<String, Double> formatters = new HashMap<String, Double>() {{
        String[] prefixes = {"k", "m", "b", "t", "q", "qq", "s", "ss", "o", "n", "d", "un", "dd", "td", "qt", "qn", "sd", "spd", "od", "nd", "vg", "uvg", "dvg", "tvg", "qtv"};
        double total = 1;

        for (String prefix : prefixes)
            put(prefix, total *= 1000);
    }};

    public static double getFormatted(String base) {
        String baseFormatterLetter = base.replaceAll("[0-9]*", "")
                .toLowerCase();

        double baseFormatterNumber = Double.parseDouble(base.replaceAll("[a-zA-Z]*", ""));

        double multiplier = formatters.getOrDefault(baseFormatterLetter, .0);
        if (multiplier <= 0) {
            System.out.println("Not found! :(");
            return 0;
        }

        return (baseFormatterNumber * multiplier);
    }
	public static final double LOG = 6.907755278982137D;
    public static final Object[][] VALUES = {
      {"", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "O", "N", "D", "UN", "DD", "TD", "QT", "QN", "SD", "SPD", "OD", "ND", "VG", "UVG", "DVG", "TVG", "NULL"},
      {1D, 1000.0D, 1000000.0D, 1.0E9D, 1.0E12D, 1.0E15D, 1.0E18D, 1.0E21D, 1.0E24D, 1.0E27D, 1.0E30D, 1.0E33D, 1.0E36D, 1.0E39D, 1.0E42D, 1.0E45D, 1.0E48D, 1.0E51D, 1.0E54D, 1.0E57D, 1.0E60D, 1.0E63D, 1.0E66D, 1.0E69D, 1.0E72D}
    };
    public static final DecimalFormat FORMAT = new DecimalFormat("#,###.##", new DecimalFormatSymbols(new Locale("pt", "BR")));

    public static String nf(double number) {
        if (number == 0) return FORMAT.format(number);
        int index = (int) (Math.log(number) / LOG);
        return FORMAT.format(number / (double) VALUES[1][index]) + VALUES[0][index];
    }

    public static String dnf(double number) {
        if (number == 0) return FORMAT.format(number);
        int index = (int) (Math.log(number) / LOG);
        return FORMAT.format(number / (double) VALUES[0][index]) + VALUES[1][index];
    }
}
