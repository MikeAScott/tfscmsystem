TfsCmSystem plugin for FitNesse
-----------------------------------------------------------------------

This plugin enables Team Foundation Server revision control in FitNesse. The plugin is
intended to perform basic operations like opening pages for edit, delete, add,
reopen integrated files and revert files if neccessary. Remember that each page
is defined by a directory that bears it's name, and two files that contain it's
content. The first file is content.txt which holds the wiki text. The second is
properties.xml which holds all the metatdata for the page. This plugin will open
those files in the default changelist. The user has to take care on his own to
submit these files.

This plugin is based on the PerforceCmSsystem plugin created by Markus Gärtner
see http://http://code.google.com/p/perforcecmsystem/ for more original project

In order to use this plugin, you have to include TF.EXE in the Path when starting fitnesse
Usually adding path=%path%;C:\Program Files\Microsoft Visual Studio 10.0\Common7\IDE
to the StartFitNesse.cmd should do the trick. 
You must also include TfsCmSystem on the classpath when starting FitNesse.
An example how to do this is included in the distribution.
When you have started up FitNesse with the TfsCmSystem in your classpath,
you have to set CM_SYSTEM to fitnesse.wiki.cmSystems.TfsCmSystem.
 This can be done in one of three ways:

1) use 
       !define CM_SYSTEM {fitnesse.wiki.cmSystems.TfsCmSystem}
   on the top-most page within FitNesse,
2) start FitNesse with -DCM_SYSTEM=fitnesse.wiki.cmSystems.TfsCmSystem or
   some other way to set the CM_SYSTEM property within the JVM,
3) export the environment variable CM_SYSTEM with the content
   fitnesse.wiki.cmSystems.TfsCmSystem. How to do this is shell dependent.

After having started FitNesse with the proper classpath addition and the
CM_SYSTEM variable defined to the TfsCmSystem class, FitNesse should use
Perforce when you add a page, edit one, refactor one, having the according
content.txt and properties.xml files being opened in the default changelist.

If you don't want to add entries in a certain directory like ErrorLogs and
RecentChanges contents to your changelist, you should use the !define approach
on the top-most fitnesse suite right below the root folder. Initially I included
a mechanism to ignore these files all the time, but realized that there might be
people who would like to include these files in the version control, too. So I
dropped this feature.
