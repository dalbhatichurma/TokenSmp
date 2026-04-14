package com.randomtoken;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TokenManager {
    private final RandomTokenAbilities plugin;
    private final Map<UUID, Token> playerTokens = new HashMap<>();
    private File dataFile;
    private FileConfiguration dataConfig;

    public TokenManager(RandomTokenAbilities plugin) {
        this.plugin = plugin;
        loadData();
    }

    private void loadData() {
        dataFile = new File(plugin.getDataFolder(), "tokens.yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        
        for (String key : dataConfig.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                Token token = Token.valueOf(dataConfig.getString(key));
                playerTokens.put(uuid, token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData() {
        for (Map.Entry<UUID, Token> entry : playerTokens.entrySet()) {
            dataConfig.set(entry.getKey().toString(), entry.getValue().name());
        }
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Token getToken(UUID uuid) {
        return playerTokens.get(uuid);
    }

    public void setToken(UUID uuid, Token token) {
        playerTokens.put(uuid, token);
        saveData();
    }

    public void removeToken(UUID uuid) {
        playerTokens.remove(uuid);
        dataConfig.set(uuid.toString(), null);
        saveData();
    }

    public Token getRandomToken() {
        List<Token> enabledTokens = new ArrayList<>();
        for (Token token : Token.values()) {
            String configKey = "tokens." + token.name().toLowerCase();
            if (plugin.getConfig().getBoolean(configKey, true)) {
                enabledTokens.add(token);
            }
        }
        if (enabledTokens.isEmpty()) {
            return Token.values()[new Random().nextInt(Token.values().length)];
        }
        return enabledTokens.get(new Random().nextInt(enabledTokens.size()));
    }

    public boolean hasToken(UUID uuid) {
        return playerTokens.containsKey(uuid);
    }
}
