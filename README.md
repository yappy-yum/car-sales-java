## Car Sales Management System 

a simple car sales management system built using only Java language

## 🎯 Requirements

- [JDK](https://www.oracle.com/java/technologies/downloads/): Java SE Development Kit
- [Visual Studio Code](https://code.visualstudio.com/download): A recommended code editor for Java, or any other code editor of your choice

**OPTIONAL**

- [Git](https://git-scm.com/downloads): Best terminal for non-linux users
- [Makefile](https://stackoverflow.com/questions/32127524/how-to-install-and-use-make-in-windows): Best for shortcut 

To verify the installation, run the following command:

> **Java**
> ```bash
> java --version
> ```
> An output similar to `java xx.x.x YYYY-MM-DD` indicates a successful installation

> **git**
> ``` bash
> git --version
> ```
> An ouput similar to `git version x.x.x` indicates a successful installation

> **Makefile**
> ```bash
> make --version
> ```
> An output similar to `GNU Make x.x.x` indicates a successful installation

## ⏩ Quickstart

To get all the codes installed in your code editor, run the following command:

```bash
git clone https://github.com/yappy-yum/car-sales-java
```

## ⚠️ Configuration

**Before initialize/run the program, ensures that below changes has made to avoid any unnecessarily bug, not excluding missing dependencies.**

<details>
<summary>MANDATORY</summary>

**1. File Path**
- Head over to `Helper.fileSystem.filePath` and look for `FILE_PATH`, ensures that the file path is relevant to your devices

</details>

<details>
<summary>OPTIONAL</summary>

**1. Argon2 configuration**
- head over to `loginPage.Argon2.Argon`, and change the value of `ITERATION`, `MEMORY`, and `PARALLELISM` that best suit for your own device and preferences
- to test the effects, run the `main` method in `loginPage.Argon2.testArgon` 

**2. Makefile**
- if you're using the header generater, you might want to check the file path on row no.4

</details>

<br>

> once you've finished all the configuration process, run the following command to run the program
>
> ```
> make deploy
> ```

## 🔎 Scope

```javascript
src/
├── Components/
│   ├── Components.java
|   ├── initializer.java
|   ├── SwitchThemeComp.java
│   └── Window.java
|
├── frontPage/
│   ├── FaQConfig
│   ├── FrontPage.java
│   └── isDarkTheme.java
|
├── Helper/
│   ├── Animation/
│   │   ├── ComponentAnim.java
│   │   └── VideoAnim.java
│   ├── Comp/
│   │   ├── createComp.java
│   │   ├── createJFX.java
│   │   └── ScrollHelper.java
│   ├── fileSystem/
│   │   ├── filePath.java
│   │   ├── fontSystem.java
│   │   ├── imageSystem.java
│   │   └── videoSystem.java
│   ├── RoundedBorder/
│   |   ├── roundedBorder.java
│   |   └── roundedBorderFactory.java
|   └── blur.java
|
├── loginPage/
│   ├── Argon2/
│   │   ├── Argon.java
│   │   └── testArgon.java
│   ├── PageInit/
│   │   ├── addLoginFill.java
│   │   ├── loginFill.java
│   │   ├── loginMessage.java
│   │   └── loginPage.java
│   ├── isLogin.java
│   ├── Profile.java
│   └── storage.java
|
└── App.java
```

## 🙏 Attribution

1. External Libraries
- [JavaFX](https://openjfx.io)
- [Argon2](https://central.sonatype.com/artifact/de.mkammerer/argon2-jvm/2.8/overview) (since its a normal java project, the latest version of Argon2 that support such project without POM file would be 2.8)
    - [jna used by Argon2](https://central.sonatype.com/artifact/net.java.dev.jna/jna/5.17.0/overview)
    - [additional platform support jna](https://central.sonatype.com/artifact/net.java.dev.jna/jna-platform/5.17.0)

2. Custom Fonts
- [Montserrat](https://fonts.google.com/specimen/Montserrat) by Julieta Ulanovsky, Sol Matas, Juan Pablo del Peral, Jacques Le Bailly
- [Nordin Slab Rounded Drawn](https://www.fontspace.com/search?q=Nordin%20Slab%20Rounded%20Drawn%20by%20craftsupplyco) by craftsupplyco
- [Rosetta](https://www.fontspace.com/new/fonts) by craftsupplyco