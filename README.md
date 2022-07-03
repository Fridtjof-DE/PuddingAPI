[![API Badge](https://img.shields.io/badge/MC%20version-Bukkit%20v1.19-blue?style=flat-square)](https://www.spigotmc.org/)
[![Latest Release Badge](https://img.shields.io/badge/latest%20release-snapshot%20of%20v2.0.0-yellow?style=flat-square)]()
[![Dev State Badge](https://img.shields.io/badge/stage%20of%20development-beta-yellow?style=flat-square)]()
[![Maintenance Badge](https://img.shields.io/maintenance/yes/2022?style=flat-square)]()
[![Issue Badge](https://img.shields.io/github/issues/Fridtjof-DE/PuddingAPI?style=flat-square)](https://github.com/Fridtjof-DE/PuddingAPI/issues)
[![Discord Online Badge](https://img.shields.io/discord/698210072899223642?style=flat-square)](https://discord.gg/HmuuMvUyCU)
[![bStats Players](https://img.shields.io/bstats/players/7954?style=flat-square)](https://bstats.org/plugin/bukkit/Yggdrasil/7954)
[![bStats Server](https://img.shields.io/bstats/servers/7954?style=flat-square)](https://bstats.org/plugin/bukkit/Yggdrasil/7954)

# <img src="https://github.com/Fridtjof-DE/PuddingAPI/blob/master/puddingapi.png" data-canonical-src="https://github.com/Fridtjof-DE/PuddingAPI/blob/master/puddingapi.png" width="32" height="32" /> PuddingAPI

 My personal API for my Java programs
 
---

## Bukkit Plugin Example

```java
class MyPlugin extends JavaPlugin {

	//The boolean toggles if debug msgs should be printed
	Logger logger = new Logger(this, true);
	
	void onEnable() {
		new UpdateChecker(this, 81151, "myplugin.updatepermission");
        	new Metrics(this, 7954);
		
		logger.info("&Hello there");
	}
}
```
---

## Bukkit and Minecraft related

```java
class SomeClass {

	//Get uuid from username
	String uuid = MojangAPI.getUuidFromUserName("Fridtjof_DE");
	
	//Replaces '&' colorcodes with '§'
	String test1 = ChatAPI.format('&', "§bTest");
	//Default char is '&'
	String test2 = ChatAPI.format("§bTest");
	
	//Get a player head from name
	ItemStack itemStack = PlayerHead.getSkullFromOwner("Fridtjof_DE");
	//Get a player head from web
	ItemStack itemStack = PlayeeHead.getSkullFromWeb("https://textures.minecraft.net/texture/de360e2a1c6b0f48f44212575c7ad83b893803a6fd4c1cc3e0983abaa56af4b9");
	//You can also change the display name on these itemstacks
	ItemStack itemStack = PlayerHead.getSkullFromWeb("Fridtjof_DE", "Nice display name");
	
}
```
---

## Java Program Example

```java
class SomeClass {

	//The boolean toggles if debug msgs should be printed
	Logger logger = new Logger("ThreadName", true);
	
	
	void doStuff() {
		new UpdateChecker("current version", "updateCheckTxtUrl", "update website");
		
		logger.info("Doing stuff");
	}
}
```

---

## Config Example

```java
class SomeClass {
	
	void doStuff() {
		Config cfg = new Config("C:/Users/Human/Desktop/", "config_file_name");
		cfg.setValue("some_int", 122);
		cfg.setDefault("some_string", "hi");
		if(cfg.getBoolean("some_boolean")) {
				System.out.println(cfg.getString("some_string"));
		}
	}
}
```
---
