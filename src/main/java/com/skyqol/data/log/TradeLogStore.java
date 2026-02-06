package com.skyqol.data.log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.skyqol.data.log.TradeLogEntry;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TradeLogStore {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type LIST_TYPE = new TypeToken<List<TradeLogEntry>>() {
    }.getType();

    private static final Path FILE = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("skyqol")
            .resolve("bazaar_trades.json");

    private static List<TradeLogEntry> cache = new ArrayList<>();

    private TradeLogStore() {
    }

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                String json = Files.readString(FILE);
                List<TradeLogEntry> loaded = GSON.fromJson(json, LIST_TYPE);
                cache = (loaded != null) ? loaded : new ArrayList<>();
            } else {
                cache = new ArrayList<>();
            }
        } catch (IOException e) {
            cache = new ArrayList<>();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(cache, LIST_TYPE));
        } catch (IOException ignored) {
        }
    }

    public static void append(TradeLogEntry entry) {
        cache.add(entry);
        save();
    }

    public static List<TradeLogEntry> getAll() {
        return List.copyOf(cache);
    }
}
