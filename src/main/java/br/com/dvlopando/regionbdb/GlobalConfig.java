package br.com.dvlopando.regionbdb;

import br.com.dvlopando.regionbdb.core.EpicenterManager;
import br.com.dvlopando.regionbdb.event.RegionEvent;
import org.bukkit.Server;

public class GlobalConfig {
    private static Server server;
    private static RegionEvent regionEvent;
    private static EpicenterManager manager;
    public static void setServer(Server tmpServer)   {
        server = tmpServer;
    }
    public static Server getServer()   {
        return server;
    }
    public static void setRegionEvent(RegionEvent tmpRegionEvent)  {
        regionEvent = tmpRegionEvent;
    }
    public static RegionEvent getRegionEvent()   {
        return regionEvent;
    }
    public static void setEpicenterManager(EpicenterManager tmpManager)   {
        manager = tmpManager;
    }
    public static EpicenterManager getEpicenterManager()   {
        return manager;
    }
}
