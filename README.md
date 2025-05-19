## Car Sales Management System 

a simple car sales management system built using only Java language

> **⚠️ Disclaimer:** <br>
> This project is solely for demonstration purposes only. The code has not been properly audited or tested for production use. Use it at your own risk.

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

**2. .env file**
- Create a `.env` file and add the following data:

```bash
OWNER_USERNAME=<username>
OWNER_PASSWORD=<password>
```
replace `<username>` and `<password>` with any username and password respectively for owner login 

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

## 🚨 Known Issue

1. The program initialization may take longer time because three hashes need to be executed for the owner details during startup.

## 🔎 Scope

```javascript
src/
├── Components/
│   ├── Components.java
|   ├── initializer.java
|   ├── storeVid.java
|   ├── SwitchThemeComp.java
│   └── Window.java
|
├── Details/
│   ├── AboutUs.java
│   ├── changeInformation.java
│   ├── checkProfile.java
│   └── TaC.java
|
├── frontPage/
│   ├── FaQConfig.java
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
│   │   ├── createScroll.java
│   │   ├── helpStoreComp.java
│   │   ├── PanelHelper.java
│   │   └── wordWrap.java
│   ├── Config/
│   │   ├── dynamicTable.java
│   │   ├── roundedBorder.java
│   │   ├── tableRenderConfig.java
│   │   └── tableSearch.java
│   ├── fileSystem/
│   │   ├── filePath.java
│   │   ├── fontSystem.java
│   │   ├── imageSystem.java
│   │   ├── ImageUploader.java
│   │   └── videoSystem.java
│   ├── login/
│   │   ├── annotateButton.java
│   │   ├── loginComp.java
│   │   ├── loginFill.java
│   │   └── Profile.java
|   └── blur.java
|
├── Inventory/
│   ├── stockDetails.java
|   └── stockInventory.java
|
├── loginSystem/
│   ├── Argon2/
│   │   ├── Argon.java
│   │   └── testArgon.java
│   ├── LoginPage/
│   │   ├── Customer/
│   │   |   ├── Customer.java
│   │   |   └── CustReadyComp.java
│   │   ├── Job/
│   │   |   ├── Job.java
│   │   |   └── JobReadyComp.java
|   |   └── PromptMessage.java
│   ├── isLogin.java
│   ├── schedule.java
│   └── storage.java
|
├── SecondPage/
│   ├── CustomerPage/
│   │   ├── 
│   │   └── 
│   ├── EmployeePage/
│   │   ├── CustomerDB.java
│   │   ├── ManagerDB.java
│   │   └── SalesmanDB.java
│   ├── ADatabase.java
│   └── UI.java
|
├── StoreAnimation/
│   ├── compAnimStorage.java
|   └── videoAnimStorage.java
|
└── App.java
```

## 🙏 Attribution

1. External Libraries
- [JavaFX](https://openjfx.io)
- [Argon2](https://central.sonatype.com/artifact/de.mkammerer/argon2-jvm/2.8/overview), [jna used by Argon2](https://central.sonatype.com/artifact/net.java.dev.jna/jna/5.17.0/overview), [additional platform support jna](https://central.sonatype.com/artifact/net.java.dev.jna/jna-platform/5.17.0)
- [dotenv-java](https://central.sonatype.com/artifact/io.github.cdimascio/dotenv-java/3.2.0/overview)

2. Custom Fonts
- [Montserrat](https://fonts.google.com/specimen/Montserrat) by Julieta Ulanovsky, Sol Matas, Juan Pablo del Peral, Jacques Le Bailly
- [Nordin Slab Rounded Drawn](https://www.fontspace.com/search?q=Nordin%20Slab%20Rounded%20Drawn%20by%20craftsupplyco) by craftsupplyco
- [Rosetta](https://www.fontspace.com/new/fonts) by craftsupplyco
- [Beautiful People](https://www.fontspace.com/search?q=Beautiful%20People%20by%20Billy%20Argel%20Fonts)

> ❝ *Talk is cheap. Show me the code.*  
> — Linus Torvalds
