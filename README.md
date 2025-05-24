## Car Sales Management System 

a simple car sales management system built using only Java language

> **⚠️ Disclaimer:**  
> 1. This project is intended for **demonstration purposes only**. The code provided has not undergone thorough auditing or testing for production environments. **Use it at your own risk.**  
> 2. The `files/sample` directory contains fictional content including user faces, documents, and CVs. All names, email addresses, phone numbers, institution names, etc are **entirely fictional** and are used solely for illustrative and educational purposes as part of this program.

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

<details>
<summary>1. Slow Startup on Initialization</summary>

- During program startup, three Argon2 data hashes are executed for the owner profile.
- This may cause a slight delay during initialization — this is expected behavior and not a bug.

</details>

<details>
<summary>2. Occasional Ignorable Exceptions</summary>

- The following exceptions may appear **intermittently** under rare conditions, like during reloads, particularly rendering or background threading 
- These can be **safely ignored** as they do not affect core functionality.

```bash
java.util.concurrent.RejectedExecutionException: Task com.sun.javafx.tk.quantum.PaintRenderJob@1af0d9dd[Not completed, task = java.util.concurrent.Executors$RunnableAdapter@12693f2a[Wrapped task = com.sun.javafx.tk.quantum.UploadingPainter@4fef1452]] rejected from com.sun.javafx.tk.quantum.QuantumRenderer@218458a4[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 1525]
        at java.base/java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2081)
        at java.base/java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:841)
        at java.base/java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1376)
        at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:123)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.QuantumRenderer.submitRenderJob(QuantumRenderer.java:210)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.QuantumToolkit.addRenderJob(QuantumToolkit.java:504)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.EmbeddedScene.repaint(EmbeddedScene.java:193)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.PaintCollector.renderAll(PaintCollector.java:442)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.QuantumToolkit.pulse(QuantumToolkit.java:593)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.QuantumToolkit.pulse(QuantumToolkit.java:572)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.QuantumToolkit.pulseFromQueue(QuantumToolkit.java:565)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.QuantumToolkit.lambda$runToolkit$6(QuantumToolkit.java:346)
        at javafx.graphics@24.0.1/com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:95)
        at javafx.graphics@24.0.1/com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
        at javafx.graphics@24.0.1/com.sun.glass.ui.win.WinApplication.lambda$runLoop$0(WinApplication.java:168)
        at java.base/java.lang.Thread.run(Thread.java:1570)
```

```bash
Exception in thread "JavaFX Application Thread" java.lang.NullPointerException: Cannot invoke "com.sun.javafx.tk.quantum.SceneState.update()" because "this.sceneState" is null
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.GlassScene.updateSceneState(GlassScene.java:253)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.EmbeddedScene.lambda$setPixelScaleFactors$1(EmbeddedScene.java:158)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.QuantumToolkit.runWithRenderLock(QuantumToolkit.java:442)
        at javafx.graphics@24.0.1/com.sun.javafx.tk.quantum.EmbeddedScene.lambda$setPixelScaleFactors$2(EmbeddedScene.java:157)
        at javafx.graphics@24.0.1/com.sun.javafx.application.PlatformImpl.lambda$runLater$4(PlatformImpl.java:419)
        at javafx.graphics@24.0.1/com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:95)
        at javafx.graphics@24.0.1/com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
        at javafx.graphics@24.0.1/com.sun.glass.ui.win.WinApplication.lambda$runLoop$0(WinApplication.java:168)
        at java.base/java.lang.Thread.run(Thread.java:1570)
```

```java
Exception in thread "Thread-24041" java.lang.NullPointerException: Cannot invoke "Components.storeVid.clearAll()" because "this.i.storeVid" is null
        at Components.Window.lambda$_throw$0(Window.java:212)
        at java.base/java.lang.Thread.run(Thread.java:1570)
```

</details>

## 🔎 Scope

<details>
<summary>java `src` structure</summary>

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

</details>

## 🙏 Attribution

1. External Libraries
- [JavaFX](https://openjfx.io)
- [Argon2](https://central.sonatype.com/artifact/de.mkammerer/argon2-jvm/2.8/overview), [jna used by Argon2](https://central.sonatype.com/artifact/net.java.dev.jna/jna/5.17.0/overview), [additional platform support jna](https://central.sonatype.com/artifact/net.java.dev.jna/jna-platform/5.17.0)
- [dotenv-java](https://central.sonatype.com/artifact/io.github.cdimascio/dotenv-java/3.2.0/overview)
- [junit](https://central.sonatype.com/artifact/org.junit.platform/junit-platform-console-standalone?smo=true)

2. Custom Fonts
- [Montserrat](https://fonts.google.com/specimen/Montserrat) by Julieta Ulanovsky, Sol Matas, Juan Pablo del Peral, Jacques Le Bailly
- [Nordin Slab Rounded Drawn](https://www.fontspace.com/search?q=Nordin%20Slab%20Rounded%20Drawn%20by%20craftsupplyco) by craftsupplyco
- [Rosetta](https://www.fontspace.com/new/fonts) by craftsupplyco
- [Beautiful People](https://www.fontspace.com/search?q=Beautiful%20People%20by%20Billy%20Argel%20Fonts)
