pluginManagement {
    repositories {
        maven { url = 'https://mirrors.cloud.tencent.com/nexus/repository/maven-public/' }
        maven { url = 'https://maven.fabricmc.net/' }
        maven { url = 'https://maven.architectury.dev/' }
        maven { url = 'https://files.minecraftforge.net/maven/' }
        gradlePluginPortal()
    }
}

rootProject.name = 'iceandfirecommunitydelight'

include 'common'
include 'fabric'
include 'neoforge'
