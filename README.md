<h1 align="center">
  Lumina
</h1>

<div align="center">

[![Download](https://img.shields.io/github/downloads/NolwenDEV/Lumina/total?style=for-the-badge)](https://github.com/NolwenDEV/Lumina/releases/)
[![License](https://img.shields.io/badge/License-GNU%20General%20Public%20License%20v3.0-blue?style=for-the-badge)](https://github.com/NolwenDEV/Lumina/blob/Main/LICENSE)
[![CodeFactor](https://www.codefactor.io/repository/github/nolwendev/lumina/badge?style=for-the-badge)](https://www.codefactor.io/repository/github/NolwenDEV/Lumina)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/NolwenDEV/Lumina?color=green&label=Version&style=for-the-badge)](https://github.com/NolwenDEV/Lumina/releases/latest)  
**Lumina** is a multi-purpose Minecraft plugin containing a lot of different features, everything is toggleable / customizable in configuration files.
</div>

# 📦 | Feature

- 🏚️ | A home system allowing players to set, teleport to, and manage their personal locations within the game. It makes traveling faster and helps players organize their bases or favorite spots
- 🎒 | A backpack system gives players a personal portable inventory, allowing them to store and carry extra items beyond their main inventory
- ✉️ | A private messaging system allows players to send direct messages to each other, enabling private conversations without using public chat [WIP]
- ✈️ | A player teleportation system allowing players to request to teleport to each other (/tpto) or invite others to teleport to them (/tphere), making meeting up in the game quick and easy [WIP]
- 🛠️ | A set of utility commands to craft anywhere (/craft), return to your last death location (/back), safely trash items (/trash) and filter unwanted drops (/filter)
- 💬 | Customizable event messages (When player join, quit or die) and customizable chat messages
- 💥 | Ability to disable Creeper Griefing
- 🌾 | Automatically replants seeds when harvested, supporting both player actions and automated farms, including modded crops
- 🚧 | More incoming, stay in touch ! (Have an idea ? Contact me on Discord : @NolwenDEV)

# ⚠️ | Requirements

- A server under the **Java edition** of Minecraft
- A server under **Spigot** or one of its forks **(PaperSpigot, PurPur, ...)**
- A **MySQL** or **MariaDB** database (Required for `BACKPACK` and `HOME`)
- Supported versions : **1.16 to 1.21.10** (Untested below 1.16)

# ⚙️ | Installation

To install the latest version of **Lumina**, simply download the `Lumina.jar` file from the **[Releases](https://github.com/NolwenDEV/Lumina/releases)** section of GitHub.

Then, put the file you just downloaded in the `plugins/` folder of your Minecraft server, then, restart it.
Finally, go to the `Lumina/` folder in the `plugins/` folder of your server and configure **Lumina** to work properly.

If necessary, you can follow our **[installation guide](https://github.com/NolwenDEV/Lumina/wiki)** on our Wiki.

# 🌐 | Translation

**Lumina** is delivered in **English** by default, if you want to change the language of the plugin, just go to the `Lumina/Language.yml` file in the `plugins/` folder on your server and change the message values.

Then run the `/Lumina reload` command to reload the configuration files and apply the changes you made!

# ⚖️ | Licensing

Lumina is a plugin designed for the Java edition of Minecraft under the "[GNU General Public License v3.0](https://github.com/NolwenDEV/Lumina/blob/Main/LICENSE)".