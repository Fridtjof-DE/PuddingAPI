# PuddingAPI
 My personal API for my Java programs
 
---

## Config Example

```java
import tk.fridtjof.puddingapi.config.Config;

class SomeClass {
	void doStuff() {
		Config cfg = new Config("C:/Users/Human/Desktop/", "config_file_name");
		cfg.setValue("some_int", 122);
		cfg.setDefault("some_string", "hi");
		if(cfg.getBoolean("some_boolean")) {
				System.out.println(cfg.getString("some_string"));
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
    <version>1.0.1</version>
  </dependency
</dependencies>
```

---
