package cn.glycol.cfh.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

import cn.glycol.cfh.CFH;
import cn.glycol.cfh.SkullUtils;
import net.minecraft.item.ItemStack;

import static cn.glycol.cfh.data.Constant.*;

public class DataManager {

	static {
		defaults();
	}
	
	/**
	 * 获取指定的文件
	 * @param name 头颅文件名称
	 * @return 头颅文件
	 */
	private static File getFile(String name) {
		return new File("config/ColorfulHeads/"+name+".cfg");
	}
	
	/**
	 * 预加载原版设定
	 */
	public static void defaults() {
		
		File custom = getFile("custom");
		File colors = getFile("colors");
		File blocks = getFile("blocks");
		File interior = getFile("interior");
		File misc = getFile("misc");
		File animals = getFile("animals");
		File foods = getFile("foods");
		File seasonal = getFile("seasonal");
		File letters = getFile("letters");
		File mobs = getFile("mobs");
		
		withDefVal(custom, DEF_CUSTOM);
		withDefVal(colors, DEF_COLORS);
		withDefVal(blocks, DEF_BLOCKS);
		withDefVal(interior, DEF_INTERIOR);
		withDefVal(misc, DEF_MISC);
		withDefVal(animals, DEF_ANIMALS);
		withDefVal(foods, DEF_FOODS);
		withDefVal(seasonal, DEF_SEASONAL);
		withDefVal(letters, DEF_LETTERS);
		withDefVal(mobs, DEF_MOBS);
	}
	
	/**
	 * 将初始数据写入文件
	 * @param file 文件
	 * @param defVal 初始数据
	 */
	private static void withDefVal(File file, String...defVal) {
		
		Objects.requireNonNull(file);
		Objects.requireNonNull(defVal);
		
		String context = new String();
		for(String str : defVal) {
			context += str + "\n";
		}
		context = context.trim();
		
		try {
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(context);
			fw.close();
		} catch(IOException e) {
			CFH.LOGGER.error("Unable to write default data into file: {}", file.getName());
		}
		
	}
	
	/**
	 * 从文件获取头颅
	 * @param name 头颅文件名称
	 * @return 头颅列表
	 */
	public static List<ItemStack> getSkulls(String name) {
		
		Objects.requireNonNull(name);
		
		List<ItemStack> list = Lists.newArrayList();
		File data = getFile(name);
		
		try {
			List<String> context = Files.readAllLines(data.toPath());
			for(String str : context) {
				ItemStack skull = SkullUtils.deserialize(str);
				if(!skull.isEmpty()) {
					list.add(skull);
				}
			}
		} catch (Exception e) {
			CFH.LOGGER.error("Unable to read data from file: {}", data.getName());
		}
		
		return list;
		
	}
	
}
