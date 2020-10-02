package de.staticred.discordbot.files;

import de.staticred.discordbot.DBVerifier;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class AliasesFileManager {

    private File file = new File(DBVerifier.getInstance().getDataFolder().getAbsolutePath(), "aliases.yml");
    private Configuration conf;
    public static AliasesFileManager INSTANCE = new AliasesFileManager();

    public void loadFile() {
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            try(InputStream in = getClass().getClassLoader().getResourceAsStream("aliases.yml")) {
                Files.copy(in,file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            conf = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }



        saveFile();
    }

    public List<String> getVerifyAliases() {
        return conf.getStringList("verify");
    }

    public List<String> getUnlinkAliases() {
        return conf.getStringList("unlink");
    }

    public List<String> getUpdateAliases() {
        return conf.getStringList("update");
    }

    public List<String> getHelpAliases() {
        return conf.getStringList("help");
    }

    public List<String> getInfoAliases() {
        return conf.getStringList("info");
    }


    public void saveFile() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(conf,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
