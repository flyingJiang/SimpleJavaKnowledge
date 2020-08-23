#

## 1. You're working in a team that uses IntelliJ IDEA as IDE. You have committed your changes to your local Mercurial repository but when you perform a pull operation, the IDE notifies you there are conflicts with the upstream version of the code. There are changes like calls to different methods and different default values for variables. You don't want to lose your changes, how can you solve this problem using the tools IntelliJ provides?
   Incorrect -Click on Merge, a tool with three panes will open to show you a read-only version of your local copy, a read-only version of the code in the repository, and an editor with the base revision. On the toolbar, click on the button for the Apply All Non-Conflicting Changes option.
   Incorrect -Click on Merge, the conflicting file will get a Merged with conflicts status. Next, go to the Local Changes tab of the Version Control tool window and open the file to manually fix the conflicting parts.
   **Your choice: correct -Click on Merge, a tool with three panes will open to show you a read-only version of your local copy, a read-only version of the code in the repository, and an editor with the base revision where you can manually merge the conflicting parts.**
   Incorrect -Click on Create Patch to create a .patch file. Select VCS -> Apply patch from the main menu and choose the .patch file. A tool with two panes will open to integrating the changes in the patch file with your local copy of your code.

## 2. In Maven, which of the following is a valid scope for a dependency?
   Incorrect -public
   **Correct -runtime**
   Your choice: incorrect -module
   Incorrect -package

## 3. In IntelliJ IDEA, how would you open the Search Everywhere dialogue?
   Incorrect -Edit > Find > Find in Path
   Incorrect -Edit > Find > Find
   **Your choice: correct -Press Shift Key twice in quick succession**
   Incorrect -Edit > Find > Search Structurally
   Incorrect -I don't know yet.

## 4. Which of the following is mandatory in a module for creating a launcher script?
   Incorrect -A class implementing the interface Launcher
   **Your choice: correct -A class with main method**
   Incorrect -A class extending the class Application
   Incorrect -A class with annotation - @Launcher
   Incorrect -I don't know yet.

## 5. What does the following command do?
   docker run ubuntu ls
   Incorrect -1. It starts the ubuntu container (if it is stopped/paused).
   2. Executes the command and captures the output.
   Your choice: correct -1. It downloads the ubuntu image if it doesn’t exist locally.
   2. It creates a new container from that image.
   3. It configures and starts the container.
   4. Executes the command and captures the output.
   Incorrect -1. It creates a new container from the ubuntu image (only if it exists locally).
   2. It configures and starts the container.
   3. Executes the command and captures the output.
   4. If the image doesn't exist locally, an error is thrown.
   Incorrect -1. Executes the command in the ubuntu container and captures the output.
   2. If the container is not running, an error is thrown.
   Incorrect -I don't know yet.
