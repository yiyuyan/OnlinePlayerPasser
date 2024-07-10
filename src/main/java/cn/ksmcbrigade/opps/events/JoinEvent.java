package cn.ksmcbrigade.opps.events;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.xephi.authme.api.v3.AuthMeApi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JoinEvent implements Listener {

    @EventHandler
    public static void joinOn(PlayerJoinEvent event){
       if(isOnline(event.getPlayer().getName())){
           AuthMeApi.getInstance().forceLogin(event.getPlayer());
           event.getPlayer().sendMessage("[正版免登] 已跳过登录验证。");
       }
    }

    public static boolean isOnline(String name) {
        try {
            JsonObject list = new JsonParser().parse(get("https://api.mojang.com/users/profiles/minecraft",name)).getAsJsonObject();
            System.out.println("get a account: "+list.toString());
            return list.has("name") || list.get("name")!=null;
        }
        catch (Exception e){
            return false;
        }
    }

    public static String get(String urlStr, String name) throws IOException {
        URL url = new URL(urlStr+"/"+name);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            return "Error: " + responseCode;
        }
    }
}
