package net.example;

import net.example.managers.PluginMgr;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginInstance extends JavaPlugin {

    private static PluginInstance instance;
    private PluginMgr manager;

    @Override
    public void onEnable() {
        instance = this;
        this.manager = new PluginMgr();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * @return The Minecraft Plugin Instance
     */
    public static PluginInstance getInstance() {
        return instance;
    }

    /**
     * @return The Main Manager (PluginMgr by default)
     */
    public PluginMgr getMgr() {
        return manager;
    }

    /**
     * Returns true or false depending on the value of the debug config option in config.yml if it exists.
     * By default, it returns false.
     * @return true or false
     */
    public boolean isDebugMode() {
        boolean isMgrReady = manager != null && manager.isInitialized();
        boolean isConfigMgrReady = isMgrReady && manager.getConfigMgr() != null && manager.getConfigMgr().isInitialized();

        if (isConfigMgrReady && manager.getConfigMgr().get("debug") != null &&  manager.getConfigMgr().get("debug") instanceof Boolean)
            return (boolean) manager.getConfigMgr().get("debug");
        return false;
    }
}
