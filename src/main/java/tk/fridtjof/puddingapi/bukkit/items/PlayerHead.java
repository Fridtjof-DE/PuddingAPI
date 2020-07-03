package tk.fridtjof.puddingapi.bukkit.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class PlayerHead {

    public static ItemStack getSkullFromOwner(String skullOwner) {
        return getSkullFromOwner(skullOwner, "");
    }

    public static ItemStack getSkullFromOwner(String skullOwner, String displayName) {
        ItemStack itemStack = getSkull(displayName);
        if(skullOwner == null)return itemStack;

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(skullOwner);
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    public static ItemStack getSkullFromWeb(String url) {
        return getHeadFromWeb(url, "");
    }

    public static ItemStack getHeadFromWeb(String url, String displayName) {
        ItemStack itemStack = getSkull(displayName);
        if(url == null)return itemStack;

        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url: \"%s\"}}}", url).getBytes());
        gameProfile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            assert skullMeta != null;
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, gameProfile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }

    private static ItemStack getSkull(String displayName) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        if(!displayName.equals("")) {
            skullMeta.setDisplayName("§r" + displayName);
        }
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
