package cn.glycol.cfh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

public class SkullUtils {

	private static final Logger LOG = LogManager.getLogger("ColorfulHeads");
	
	public static String getSkullOwner(ItemStack stack) {
		if(isPlayerSkull(stack)) {
			CompoundNBT nbt = getOrCreateTag(stack);
			
			String owner = nbt.getString("SkullOwner");
			if(owner.equals("")) {
				CompoundNBT skullowner = nbt.getCompound("SkullOwner");
				if(skullowner != null) owner = skullowner.getString("Name");
			}
			return owner;
		} else {
			return "";
		}
	}
	
	public static ItemStack setSkullOwner(ItemStack stack, String skullowner) {
		if(stack == null) stack = new ItemStack(Items.PLAYER_HEAD);
		if(isPlayerSkull(stack)) {
			CompoundNBT nbt = getOrCreateTag(stack);
			nbt.putString("SkullOwner", skullowner);
		}
		return stack;
	}
	
	public static ItemStack setSkullOwner(ItemStack stack, String id, String texture) {
		if(stack == null) stack = new ItemStack(Items.PLAYER_HEAD);
		if(isPlayerSkull(stack)) {
			CompoundNBT root = getOrCreateTag(stack);
			CompoundNBT skullowner = new CompoundNBT();
			CompoundNBT properties = new CompoundNBT();
			ListNBT textures = new ListNBT();
			CompoundNBT value = new CompoundNBT();
			if(root.contains("SkullOwner")) skullowner = root.getCompound("SkullOwner");
			skullowner.putString("Id", id);
			value.putString("Value", texture);
			textures.add(value);
			properties.put("textures", textures);
			skullowner.put("Properties", properties);
			root.put("SkullOwner", skullowner);
		}
		
		return stack;
	}
	
	public static String serialize(ItemStack skull) {
		CompoundNBT root = getOrCreateTag(skull);
		CompoundNBT skullowner = root.getCompound("SkullOwner");
		String skullid = skullowner.getString("Id");
		CompoundNBT properties = skullowner.getCompound("Properties");
		ListNBT textures = properties.getList("textures", Constants.NBT.TAG_COMPOUND);
		CompoundNBT property = textures.getCompound(0);
		String texture = property.getString("Value");
		return skullid+"|"+texture+"|"+skull.getDisplayName();
	}
	
	public static ItemStack deserialize(String skullcode) {
		String[] parts = skullcode.split("\\|");
		if(parts.length > 3) {
			LOG.warn("Failed to deserialize the skull code, too many parts: {}", skullcode);
		}
		else if(parts.length < 2) {
			LOG.warn("Failed to deserialize the skull code, too few parts: {}", skullcode);
		}
		else {
			boolean customInfo = false;
			
			String skullid = parts[0];
			String texture = parts[1];
			String display = null;
			if(parts.length == 3) {
				display = parts[2].replace("&", "\u00A7");
				// CUSTOM的提示
				if(display.contains("##CUSTOM##")) {
					customInfo = true;
				}
			}
			ItemStack skull = SkullUtils.setSkullOwner(null, skullid, texture);
			if(customInfo) {
				skull.setDisplayName(new TranslationTextComponent("cfh.custom_info"));
			} else {
				if(display != null) skull.setDisplayName(new StringTextComponent(display));
			}
			return skull;
		}
		return ItemStack.EMPTY;
	}
	
	public static boolean isPlayerSkull(ItemStack stack) {
		return stack.getItem() == Items.PLAYER_HEAD;
	}
	
	public static CompoundNBT getOrCreateTag(ItemStack stack) {
		
		if(!stack.hasTag()) {
			stack.setTag(new CompoundNBT());
		}
		
		return stack.getTag();
	}
	
}
