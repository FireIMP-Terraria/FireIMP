package net.fireimp.server.network.packets;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import net.fireimp.server.TerrariaServer;

import java.io.IOException;

public enum PacketType {
    CONNECT_REQUEST,
    DISCONNECT,
    CONTINUE_CONNECTING,
    PLAYER_INFO,
    PLAYER_INVENTORY_SLOT,
    CONTINUE_CONNECTING_RESPONSE,
    WORLD_INFO,
    REQUEST_SECTION,
    STATUS,
    SEND_SECTION,
    SECTION_TILE_FRAME,
    SPAWN_PLAYER,
    UPDATE_PLAYER,
    PLAYER_ACTIVE,
    NULL,
    PLAYER_HEALTH,
    MODIFY_TILE,
    TIME,
    DOOR_TOGGLE,
    SEND_TILE_SQUARE,
    UPDATE_ITEM_DROP,
    UPDATE_ITEM_OWNER,
    NPC_UPDATE,
    STRIKE_NPC_WITH_HELD_ITEM,
    CHAT_MESSAGE,
    PLAYER_DAMAGE,
    PROJECTILE_UPDATE,
    NPC_STRIKE,
    DESTROY_PROJECTILE,
    TOGGLE_PVP,
    GET_CHEST_CONTENTS,
    CHEST_ITEM,
    OPEN_CHEST,
    PLACE_CHEST,
    HEAL_EFFECT,
    PLAYER_ZONE,
    PASSWORD_REQUEST,
    PASSWORD_RESPONSE,
    REMOVE_ITEM_OWNER,
    SET_ACTIVE_NPC,
    PLAYER_ITEM_ANIMATION,
    PLAYER_MANA,
    MANA_EFFECT,
    KILL_ME,
    PLAYER_TEAM,
    REQUEST_SIGN,
    UPDATE_SIGN,
    SET_LIQUID,
    COMPLETE_CONNECTION,
    UPDATE_PLAYER_BUFF,
    SPECIAL_NPC_EFFECT,
    UNLOCK,
    ADD_NPC_BUFF,
    UPDATE_NPC_BUFF,
    ADD_PLAYER_BUFF,
    UPDATE_NPC_NAME,
    UPDATE_WORLD_MODE,
    PLAY_MUSIC_ITEM,
    HIT_SWITCH,
    NPC_HOME_UPDATE,
    SPAWN_BOSS,
    PLAYER_DODGE,
    PAINT_TILE,
    PAINT_WALL,
    TELEPORT,
    HEAL_OTHER_PLAYER,
    PLACEHOLDER, // for custom clients and servers?
    CLIENT_UUID,
    GET_CHEST_NAME,
    CATCH_NPC,
    RELEASE_NPC,
    TRAVELLING_MERCHANT_INVENTORY,
    TELEPORT_POTION,
    ANGLER_QUEST,
    COMPLETE_ANGLER_QUEST_TODAY,
    ANGLER_QUESTS_COMPLETED,
    CREATE_TEMPORARY_ANIMATION,
    REPORT_INVASION_PROGRESS,
    PLACE_OBJECT,
    SYNC_PLAYER_CHEST,
    CREATE_COMBAT_TEXT,
    LOAD_NET_MODULE, // unused
    SET_NPC_KILL_COUNT,
    SET_PLAYER_STEALTH,
    FORCE_ITEM_INTO_CHEST,
    UPDATE_TILE_ENTITY,
    PLACE_TILE_ENTITY,
    ALTER_ITEM_DROP,
    PLACE_ITEM_FRAME,
    UPDATE_ITEM_DROP_INSTANCED,
    EMOTE_BUBBLE,
    EXTRA_VALUE,
    SOCIAL_HANDSHAKE, //unused
    DEPRECATED, // unused
    KILL_PORTAL,
    PLAYER_TELEPORT_PORTAL,
    NOTIFY_NPC_KILLED,
    NOTIFY_EVENT,
    UPDATE_MINION_TARGET,
    NPC_TELEPORT_PORTAL,
    SHIELD_STRENGTHS,
    NEBULA_LEVEL_UP,
    MOON_LORD_COUNTDOWN,
    SET_SHOP_ITEM;

    private static final BiMap<Integer, PacketType> idTypeMap = HashBiMap.create();
    private static final BiMap<PacketType, Class<?>> typeClassMap = HashBiMap.create();

    public int getId() {
        return ordinal() + 1;
    }

    public static PacketType getTypeByClass(Class<?> packetClass) {
        return typeClassMap.inverse().get(packetClass);
    }

    public static Class<?> getClassByType(PacketType type) {
        return typeClassMap.get(type);
    }

    public static PacketType getTypeById(int id) {
        return idTypeMap.get(id);
    }

    static {
        // Init idType map
        int id = 1;
        for(PacketType type : values()) {
            idTypeMap.put(id++, type);
        }

        try {
            ClassPath classPath = ClassPath.from(TerrariaServer.class.getClassLoader());
            for(ClassInfo info : classPath.getTopLevelClassesRecursive("net.fireimp.server.network.packets")) {
                Class<?> clazz = Class.forName(info.getName());
                if(NetworkPacket.class.isAssignableFrom(clazz) && !clazz.getSimpleName().equals("NetworkPacket")) {
                    NetworkPacket packet = (NetworkPacket) clazz.newInstance();
                    typeClassMap.put(packet.getType(), clazz);
                }
            }
        } catch(Exception e) {
            // TODO: LOG
        }
    }
}
