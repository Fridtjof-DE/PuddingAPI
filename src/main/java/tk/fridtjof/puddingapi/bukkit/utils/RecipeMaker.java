package tk.fridtjof.puddingapi.bukkit.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class RecipeMaker {

    //TODO This class is WIP - Do not try to use it!

    public static void shapedRecipe(Material output, String row0, String row1, String row2, Material ingredient0, JavaPlugin javaPlugin) {
        ItemStack itemStack = new ItemStack(output);

        ShapedRecipe shapedRecipe = new ShapedRecipe(itemStack);

        shapedRecipe.shape(row0, row1, row2);

        shapedRecipe.setIngredient('*', Material.SUGAR, 2);
        shapedRecipe.setIngredient('%', Material.SUGAR);
        shapedRecipe.setIngredient('B', Material.GLASS_BOTTLE);

        javaPlugin.getServer().addRecipe(shapedRecipe);
    }

    //Shapeless

    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, Material ingredient2, Material ingredient3, Material ingredient4, Material ingredient5, Material ingredient6, Material ingredient7, Material ingredient8, JavaPlugin javaPlugin) {
        ItemStack itemStack = new ItemStack(output);

        ShapelessRecipe shapelessRecipe = new ShapelessRecipe(itemStack);

        shapelessRecipe.addIngredient(ingredient0);
        shapelessRecipe.addIngredient(ingredient1);
        shapelessRecipe.addIngredient(ingredient2);
        shapelessRecipe.addIngredient(ingredient3);
        shapelessRecipe.addIngredient(ingredient4);
        shapelessRecipe.addIngredient(ingredient5);
        shapelessRecipe.addIngredient(ingredient6);
        shapelessRecipe.addIngredient(ingredient7);
        shapelessRecipe.addIngredient(ingredient8);

        javaPlugin.getServer().addRecipe(shapelessRecipe);
    }

    public static void shapelessRecipe(Material output, Material ingredient0, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, Material.AIR, javaPlugin);
    }
    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, ingredient1, Material.AIR, javaPlugin);
    }
    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, Material ingredient2, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, ingredient1, ingredient2, Material.AIR, javaPlugin);
    }
    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, Material ingredient2, Material ingredient3, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, ingredient1, ingredient2, ingredient3, Material.AIR, javaPlugin);
    }
    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, Material ingredient2, Material ingredient3, Material ingredient4, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, Material.AIR, javaPlugin);
    }
    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, Material ingredient2, Material ingredient3, Material ingredient4, Material ingredient5, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, Material.AIR, javaPlugin);
    }
    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, Material ingredient2, Material ingredient3, Material ingredient4, Material ingredient5, Material ingredient6, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, Material.AIR, javaPlugin);
    }
    public static void shapelessRecipe(Material output, Material ingredient0, Material ingredient1, Material ingredient2, Material ingredient3, Material ingredient4, Material ingredient5, Material ingredient6, Material ingredient7, JavaPlugin javaPlugin) {
        shapelessRecipe(output, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, Material.AIR, javaPlugin);
    }
}
