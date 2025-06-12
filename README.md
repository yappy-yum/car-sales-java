## Car Sales Management System 

a simple car sales management system built using only Java language

> **⚠️ Disclaimer:**  
> 1. This project is intended for **demonstration purposes only**. The code provided has not undergone thorough auditing or testing for production environments. **Use it at your own risk.**  
> 2. The `files/` directory contains fictional content including user faces, documents, and CVs. All names, email addresses, phone numbers, institution names, etc are **entirely fictional** and are used solely for illustrative and educational purposes as part of this program.


<br>


## 🎯 Requirements

- [JDK](https://www.oracle.com/java/technologies/downloads/): Java SE Development Kit, required to compile and run Java applications.
- [Visual Studio Code](https://code.visualstudio.com/download): A recommended code editor for Java, or any other code editor of your choice

**OPTIONAL (but recommended)**

- [Git](https://git-scm.com/downloads): Best terminal for non-linux users
- [Makefile](https://stackoverflow.com/questions/32127524/how-to-install-and-use-make-in-windows): Automates repetitive tasks with commands.

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


<br>


## ⏩ Quickstart

To get all the codes installed in your code editor, run the following command:

```bash
git clone https://github.com/yappy-yum/car-sales-java
```


<br>


## ⚠️ Configuration

**Before running the program, ensure the following configurations are made to avoid potential bugs and missing dependencies.**

<details>
<summary>🔧 MANDATORY CONFIGURATION</summary>

**1. 🔗 File Path**
- Navigate to `Helper.fileSystem.filePath`
- Update the `FILE_PATH` variable to match a valid path on your machine

**2. 📄 `.env` file**
- Create a `.env` file in the root directory.
- Add the following environment variables:

```bash
OWNER_USERNAME=<username>
OWNER_PASSWORD=<password>
DECRYPT_PASSWORD=<decryption-password>
```
> Replace `<username>` and `<password>` with the desired owner login credentials.
> Replace `<decryption-password>` with the password used for decrypting hashed images.

**3. 🧰 Makefile**
- If using the header generator, update the file path in **line 4**.
- If using the terminal and want to try make deploy or make tests, verify that the Java file paths in **lines 13–14** are correct.

**4. 🖥️ `run.cmd`**
- If you're using Windows **without a terminal**, this script helps run the Java code.
- Check and update file paths in **lines 5–7** as needed.

</details>

<details>
<summary>⚙️ OPTIONAL CONFIGURATION</summary>

**1. ⚗️ Argon2 parameters**
- Go to `loginPage.Argon2.Argon`.
- Adjust the following values to match your system's performance:
   - `ITERATION`
   - `MEMORY`
   - `PARALLELISM`

> **To test these settings, run the main method inside `loginPage.Argon2.testArgon`**

</details>


<br>


## 🧪 Run Tests and Deploy

After finishing the configuration:

```bash
make tests
make deploy
```

Or, if you're on Windows without a terminal, open `run.cmd`. <br>
Then:
1. Choose option `1` to run test suits
2. Choose option `2` to deloy and run the project


<br>


## 🚨 Known Issue

<details>
<summary>1. ⏳ Slow Startup on Initialization</summary>

- On startup, the program performs **Argon2 hash operations** for the owner profile.
- This may introduce a slight delay during initialization.
- ✅ This is expected behavior and does not indicate a bug.

</details>

<details>
<summary>2. ⚠️ Occasionally Ignorable Exceptions</summary>

- The following exceptions may appear **intermittently** under rare conditions, like during reloads, particularly rendering or background threading 
- They are related to JavaFX rendering and threading behavior.
- ✅ These do not affect program functionality and can be safely ignored.

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

```bash
Exception in thread "Thread-24041" java.lang.NullPointerException: Cannot invoke "Components.storeVid.clearAll()" because "this.i.storeVid" is null
        at Components.Window.lambda$_throw$0(Window.java:249)
        at java.base/java.lang.Thread.run(Thread.java:1570)
```

</details>

<details>
<summary>3. 🧮 Integer Overflow</summary>

- The system uses `int` data type for all the integer input fields.
- An attempt to input a number **more than 2,147,483,647** can cause a runtime exception.

```bash
Exception in thread "AWT-EventQueue-0" java.lang.NumberFormatException: For input string: "100000000000000000"
        at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
        at java.base/java.lang.Integer.parseInt(Integer.java:588)
        at java.base/java.lang.Integer.parseInt(Integer.java:685)
        at LoginSystem.LoginPage.Customer.Customer.CheckFirstRegister(Customer.java:237)
        at Helper.login.loginComp.lambda$createFillNext$0(loginComp.java:292)
        at java.desktop/javax.swing.AbstractButton.fireActionPerformed(AbstractButton.java:1972)
        at java.desktop/javax.swing.AbstractButton$Handler.actionPerformed(AbstractButton.java:2314)
        at java.desktop/javax.swing.DefaultButtonModel.fireActionPerformed(DefaultButtonModel.java:407)
        at java.desktop/javax.swing.DefaultButtonModel.setPressed(DefaultButtonModel.java:262)
        at java.desktop/javax.swing.plaf.basic.BasicButtonListener.mouseReleased(BasicButtonListener.java:279)
        at java.desktop/java.awt.Component.processMouseEvent(Component.java:6621)
        at java.desktop/javax.swing.JComponent.processMouseEvent(JComponent.java:3398)
        at java.desktop/java.awt.Component.processEvent(Component.java:6386)
        at java.desktop/java.awt.Container.processEvent(Container.java:2266)
        at java.desktop/java.awt.Component.dispatchEventImpl(Component.java:4996)
        at java.desktop/java.awt.Container.dispatchEventImpl(Container.java:2324)
        at java.desktop/java.awt.Component.dispatchEvent(Component.java:4828)
        at java.desktop/java.awt.LightweightDispatcher.retargetMouseEvent(Container.java:4948)
        at java.desktop/java.awt.LightweightDispatcher.processMouseEvent(Container.java:4575)
        at java.desktop/java.awt.LightweightDispatcher.dispatchEvent(Container.java:4516)
        at java.desktop/java.awt.Container.dispatchEventImpl(Container.java:2310)
        at java.desktop/java.awt.Window.dispatchEventImpl(Window.java:2780)
        at java.desktop/java.awt.Component.dispatchEvent(Component.java:4828)
        at java.desktop/java.awt.EventQueue.dispatchEventImpl(EventQueue.java:775)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:720)
        at java.desktop/java.awt.EventQueue$4.run(EventQueue.java:714)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:400)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:87)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:98)
        at java.desktop/java.awt.EventQueue$5.run(EventQueue.java:747)
        at java.desktop/java.awt.EventQueue$5.run(EventQueue.java:745)
        at java.base/java.security.AccessController.doPrivileged(AccessController.java:400)
        at java.base/java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(ProtectionDomain.java:87)
        at java.desktop/java.awt.EventQueue.dispatchEvent(EventQueue.java:744)
        at java.desktop/java.awt.EventDispatchThread.pumpOneEventForFilters(EventDispatchThread.java:203)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForFilter(EventDispatchThread.java:124)
        at java.desktop/java.awt.EventDispatchThread.pumpEventsForHierarchy(EventDispatchThread.java:113)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:109)
        at java.desktop/java.awt.EventDispatchThread.pumpEvents(EventDispatchThread.java:101)
        at java.desktop/java.awt.EventDispatchThread.run(EventDispatchThread.java:90)
```

</details>


<br>


## 🔎 Scope

<details>
<summary>java source folder structure</summary>

```javascript
src/
├── Comment/
│   ├── __init__.java
|   ├── ComConfig.java
|   ├── comment.java
|   ├── SaveComp.java
│   └── UpdateCallback.java
|
├── Components/
│   ├── Components.java
|   ├── initializer.java
|   ├── storeVid.java
|   ├── SwitchThemeComp.java
│   └── Window.java
|
├── Details/
│   ├── AboutUs.java
│   ├── AddCar.java
│   ├── changeInformation.java
│   ├── checkCar.java
│   ├── checkProfile.java
│   ├── Deletion.java
│   ├── TaC.java
│   └── Verify.java
|
├── frontPage/
│   ├── FaQConfig.java
│   ├── FrontPage.java
│   └── isDarkTheme.java
|
├── Helper/
│   ├── Animation/
│   │   ├── componentAnim.java
│   │   └── videoAnim.java
│   ├── Comp/
│   │   ├── createComp.java
│   │   ├── createDynamicTavle.java // @deprecated
│   │   ├── createJFX.java
│   │   ├── createScroll.java
│   │   ├── helpStoreComp.java
│   │   └── wordWrap.java
│   ├── Config/
│   │   ├── PanelConfig/
│   │   |   ├── DropdownPanel.java
│   │   |   ├── GradientPanel.java
│   │   |   └── PanelHelper.java
│   │   ├── SearchTable/
│   │   |   ├── SearchDynTable.java // @deprecated
│   │   |   └── SearchSingleTable.java
│   │   ├── TableSurface/
│   │   |   ├── tableAddIcon.java 
│   │   |   └── tableRenderConfig.java
│   │   ├── BookingConfig.java
│   │   └── roundedBorder.java
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
│   ├── blur.java
|   └── ErrorMessages.java
|
├── Inventory/
│   ├── CarIDGenerator.java
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
│   ├── SecureImage/
│   │   └── ImageHandler.java
│   ├── isLogin.java
│   ├── schedule.java
│   └── storage.java
|
├── SecondPage/
│   ├── AbstractDB/
│   │   ├── dynamicTable.java // @deprecated
│   │   └── singleTable.java
│   ├── CustomerPage/
│   │   └── Purchase.java
│   ├── EmployeePage/
│   │   ├── unVerifiedDB/
│   │   |   ├── VerifyCust.java
│   │   |   └── VerifyEmployee.java
│   │   └── VerifiedDB/
│   │       ├── CustomerDB.java
│   │       ├── InventoryDB.java
│   │       ├── ManagerDB.java
│   │       └── SalesmanDB.java
│   ├── password.java
│   └── UI.java
|
├── StoreAnimation/
│   ├── compAnimStorage.java
|   └── videoAnimStorage.java
|
└── App.java
```

</details>

<details>
<summary>java test folder structure</summary>

```javascript
test/
├── framework/
│   ├── ImageUtils.java
│   ├── ProfileSample.java
│   ├── Pseudorandomness.java
|   └── StringToStyledDocument.java
|
├── AgeIncTest.java
└── loginTest.java
```

</details>


<br>


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
- [Tagesschrift](https://fonts.google.com/specimen/Tagesschrift) by Yanone