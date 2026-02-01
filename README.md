[![API Badge](https://img.shields.io/badge/MC%20version-Bukkit%20v1.21.x-blue?style=flat-square)](https://www.spigotmc.org/)
[![Dev State Badge](https://img.shields.io/badge/stage%20of%20development-beta-yellow?style=flat-square)]()
[![Maintenance Badge](https://img.shields.io/maintenance/yes/2026?style=flat-square)]()
[![Issue Badge](https://img.shields.io/github/issues/Fridtjof-DE/PuddingAPI?style=flat-square)](https://github.com/Fridtjof-DE/PuddingAPI/issues)
[![Discord Online Badge](https://img.shields.io/discord/961799414647750717?style=flat-square)](https://discord.gg/fT6VJurHCT)
[![bStats Players](https://img.shields.io/bstats/players/7954?style=flat-square)](https://bstats.org/plugin/bukkit/PuddingAPI/8018)
[![bStats Server](https://img.shields.io/bstats/servers/7954?style=flat-square)](https://bstats.org/plugin/bukkit/PuddingAPI/8018)

[![Download Badge Modrinth](https://img.shields.io/modrinth/dt/PSN4XhQK?color=brightgreen&label=Modrinth%20downloads&style=flat-square)](https://modrinth.com/plugin/PSN4XhQK/versions)

# <img src="https://github.com/Fridtjof-DE/PuddingAPI/blob/master/puddingapi.png" data-canonical-src="https://github.com/Fridtjof-DE/PuddingAPI/blob/master/puddingapi.png" width="32" height="32" /> PuddingAPI

A collection of my shared libraries that I use for various projects, such as my (upcoming) Bukkit plugins. Intended use for myself only.

## How to Build

If you are looking to make changes, then you'll need to get both a JDK 17 and Maven installed on your computer. I recommend just using an IDE like Intellij IDEA or Eclipse.

Once you have them set up you'll need to run Maven:

```
mvn package
```

Results will be in the `builds` folder, with base name
`PuddingAPI`. The version number is made up of the current date.
(see timestamp format in pom.xml), you'll have a file in this format:

  - `PuddingAPI-yyMMddHHmm.jar`

Copy this file over to the plugins folder of your Minecraft server or use it as a dependency when working on other plugins.
