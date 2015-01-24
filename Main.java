package me.Wirex

public class Main extends JavaPlugin implements listener {

  public static final Logger LOGGER = Logger.getLogger("Minecraft");

  @Override
	public void onEnable() {
	  loadConfig();
		getServer().getPluginManager().registerEvents(this, this);
		LOGGER.info("[UnknowCMD] Plugin je uspesne zapnuty!");
	}
	
	@Override
	public void onDisable() {
	  LOGGER.info("[UnknowCMD] Plugin je uspesne vypnuty!");
	}
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void onUnkow(PlayerCommandPreprocessEvent e) {
		if (!e.isCancelled()) {
			Player p = e.getPlayer();
			
			String msg = e.getMessage().split(" ")[0];
			HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
			
			String suchmsg = getConfig().getString("config.UnknowCMD");
			suchmsg = suchmsg.replace("$cmd", msg);
			
			if (topic == null) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', suchmsg));
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10.0F, 10.0F);
				e.setCancelled(true);
			}
		}
	}
	
	public boolean onCommand1(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("ukcreload")) {
			if (p.hasPermission("ukc.reload")) {
				reloadConfig();
				p.sendMessage(ChatColor.GREEN + "Config uspesne znova nacitany!");
			} else {
				p.sendMessage(ChatColor.RED + "Nemas prava!");
			}
		}
		
		return true;
	}
