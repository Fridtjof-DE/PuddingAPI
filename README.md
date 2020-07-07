# PuddingAPI
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
	String test1 = ChatAPI.format('&', §bTest);
	//Default char is '&'
	String test2 = ChatAPI.format(§bTest);
	
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
	logger.info("Doing stuff");
	
	void doStuff() {
		new UpdateChecker("current version", "updateCheckTxtUrl", "update website");
	
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

## Maven Repository
  
```maven
<repositories>
  <repository>
    <id>fridtjofsRepo</id>
    <url>https://www.fridtjof.tk/repo</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>tk.fridtjof</groupId>
    <artifactId>puddingapi</artifactId>
    <version>2.0.0</version>
  </dependency
</dependencies>
```

---
