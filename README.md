End with Skype quickly, in Windows or Mac OS (for now!).

ABOUT DIESKYPEDIE

This is the most usefull useless piece of software you'll ever stumble upon (or not!). DieSkypeDie does one thing and one thing alone: it kills Skype on demand and it does it in Windows and MacOS.

Initially the idea was to kill the Skype process quickly (with one simple click) in a laptop running Windows 7 just because it took to much time to end it the "normal" way and let's face it, at the end of the day the only thing you really want to is to wrap it up, shut down or logoff your terminal or laptop and head home, right?

Afterwards I decided to extend the same behaviour to MacOS. This is also a simple way to see Java working equally, while running on different platforms.

Feel free to use it and/or change it if you want to. It can be easily adapted to perform the same task for other programs.

ABOUT THE PROJECT SETUP

You'll be able to import the code as a Maven project using Eclipse or IntelliJ. Once imported, access the project root directory (it should be something like <PATH_LEADING_TO>/diskypedie) and execute the floowing commands:

	mvn dependency:resolve
	mvn eclipse:eclipse (only if you're using Eclipse)
	mv clean install

It will work either in Mac OS or Windows, as long as you have Maven fully configured.

ABOUT THE CODE STRUCTURE

	- src
	  |- main
	     |- java
		|- com
		   |- dieskypedie
		      |- CallDieSkypeDie.java
		      |- exception
			 |- DieSkypeDieException.java
		      |- exec
			 |- DieSkypeDie.java
			 |- IDieSkypeDieConstants.java
		      |- generic
			 |- KillProcess.java
	- bin
	  |- classes
	     |- com
	- build
	  |- jar
	- dist
	  |- ico
	     |- mac
		|- DieSkypeDie.icns
	     |- win
		|- DieSkypeDie.ico
	  |- mac
	     |- DieSkypeDie.app
	  |- win
	     |- DieSkypeDie.exe
	- lib
	  |- appbundler-1.0.jar
	- build.xml
	- pom.xml

The main method can be found inside the CallDieSkypeDie class.

You'll find a ready to use, DieSkypeDie.app, package inside the directory 'dist/mac' for mac and a DieSkypeDie.exe, file inside 'dist/win'.

The appbundler-1.0.jar file enables the creation of the .app package. To achieve it just use the target 'Get DieSkypeDie.App' in the ant build file.

