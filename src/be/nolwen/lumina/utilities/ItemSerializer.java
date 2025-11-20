package be.nolwen.lumina.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class ItemSerializer {
	
	public static String serializeUnique(ItemStack itemStack) {
		if(itemStack == null || itemStack.getType().isAir()) return null;
		
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		try(BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream(arrayOutputStream)) {
			objectOutputStream.writeObject(itemStack);
		} catch(IOException exception) { exception.printStackTrace(); }
		
		return Base64.getEncoder().encodeToString(arrayOutputStream.toByteArray());
	}
	
	public static String serializeList(ItemStack[] itemStacks) {
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try (BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream(arrayOutputStream)) {
        	objectOutputStream.writeInt(itemStacks.length);
            for (ItemStack itemStack : itemStacks) {
            	objectOutputStream.writeObject(itemStack);
            }
        } catch(IOException exception) { exception.printStackTrace(); }
        
        return Base64.getEncoder().encodeToString(arrayOutputStream.toByteArray());
	}
	
		// ---------------------------------------- \\
	
	public static ItemStack deserializeUnique(String base) {
		if(base == null || base.isEmpty()) return null;
		
		byte[] bytes = Base64.getDecoder().decode(base);
		try(ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes)) {
			BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream(arrayInputStream);
			if(objectInputStream.readObject() instanceof ItemStack) return (ItemStack) objectInputStream.readObject();
		} catch(IOException | ClassNotFoundException exception) { exception.printStackTrace(); }
		
		return null;
	}

    public static ItemStack[] deserializeList(String base) {
        if (base == null || base.isEmpty()) return new ItemStack[0];
        
        byte[] bytes = Base64.getDecoder().decode(base);
        try (ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
            BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream(arrayInputStream)) {
        	int length = objectInputStream.readInt();
        	
            ItemStack[] items = new ItemStack[length];
            for (int index = 0; index < length; index++) {
                Object object = objectInputStream.readObject();
                items[index] = (object instanceof ItemStack) ? (ItemStack) object : null;
            }
            
            return items;
        } catch(IOException | ClassNotFoundException exception) { exception.printStackTrace(); }
        
        return null;
    }
    
}

