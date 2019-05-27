# gdx-liftoff
A modern setup tool for libGDX Gradle projects, forked from czyzby/gdx-setup

If you've used libGDX for even a short time, you've probably used the official `gdx-setup.jar` made by the libGDX team. You may have used alternative setup tools,
like [czyzby/gdx-setup](https://github.com/czyzby/gdx-setup). The problem these two setup tools share is that they currently use outdated Gradle versions, both old
enough that they won't work with Java 11 or newer, and neither seems to be updated very often. This project provides another alternative setup tool based on
[SquidSetup](https://github.com/tommyettinger/SquidSetup), but removing the close ties to the SquidLib libraries to make it more general-use. Using SquidSetup's
code, which is built on czyzby's code, gives us working projects that use Gradle 5.4, instead of 4.6 for the official setup or 4.0.2 for czyzby's gdx-setup.
Currently, gdx-liftoff depends on libGDX 1.9.9 by default, and allows using snapshots as well.

Projects default to using LWJGL3 instead of LWJGL2 (the old 'desktop' platform), since code tends to be very similar between the two, but LWJGL3 generally offers
more features. This code is tested for compatibility with GWT, including the various changes that Gradle needs with this version. It is sometimes tested on Android,
and if any issues are posted about Android problems in the generated projects, they will be readily addressed (because I have an Android phone to test on). Issues
with iOS, either RoboVM or MOE, will have to be addressed by someone sending a pull request, because I can't reproduce any iOS issues without an iOS device.

## Usage

  - Get the latest `gdx-liftoff.jar` from the [Releases tab](https://github.com/tommyettinger/gdx-liftoff/releases) of this project.
  - Regardless of what platforms you intend to target, make sure the steps
    [described by the LibGDX wiki here](https://github.com/libgdx/libgdx/wiki/Setting-up-your-Development-Environment-%28Eclipse%2C-Intellij-IDEA%2C-NetBeans%29)
    are taken care of.
  - Run the JAR. Plug in whatever options you see fit:
    - For the Platforms tab, you can technically use the "Toggle Client Platforms" button to enable LWJGL3 (which works
      on all desktop/laptop platforms), Android, iOS (which will only build on a MacOS machine), and HTML. This
      isn't always a good idea because downloading iOS and HTML dependencies can take some time, so just check the
      platforms you want to target. You can re-run the setup, make a new project with the same settings (in a different
      folder), and copy in the existing code if you want to quickly add a platform or platforms.
      - Desktop and/or LWJGL3 should usually be checked, so you can test on the same computer
        you develop on.
        - LWJGL3 is almost the same as Desktop, but because it has better support for new hardware
          (such as high-DPI displays), it should probably be preferred. It also allows multiple windows and drag+drop.
      - iOS should probably not be checked if you aren't running MacOS and don't intend to later build an iOS
        app on a Mac. It needs some large dependencies to be downloaded when you first import the project.
      - Android should only be checked if you've set up your computer for Android development. Unlike with some other
        setup tools, since gdx-liftoff uses Gradle 5.4, having an Android project present shouldn't interfere with
        other platforms or IDE integration.
      - HTML is a more-involved target, with some perfectly-normal code on all other platforms acting completely
        different on HTML due to the tool used, Google Web Toolkit (GWT). It's almost always possible to work around
        these differences and make things like random seeds act the same on all platforms, but it takes work. Mostly,
        you need to be careful with the `long` and `int` number types, and relates to `int` not overflowing as it
        would on desktop, and `long` not being visible to reflection. See [this small guide to GWT](GWT.md) for more.
    - For dependencies, you don't need LibGDX checked (the tool is set up to download LibGDX and set it as a
      dependency in all cases).
    - If you click Advanced, you can choose to generate project files for IntelliJ IDEA
      and/or Eclipse, which has some advantages but probably more disadvantages. If you
      know what you're doing, you might want to try it, but be prepared for frustration.
    - Also in Advanced, you can set the libGDX version (it defaults to 1.9.9, but can be set lower or higher) and
      various other versions.
  - Click generate, and very soon a window should pop up with instructions for what to do.
    
Now you'll have a project all set up with a sample.

  - The way to run a game project that's probably the most reliable is to use Gradle tasks
    to do any part of the build/run process. The simplest way to do this is in the IDE itself,
    via `View -> Tool Windows -> Gradle`, and selecting tasks to perform, such as
    `Desktop -> Tasks -> application -> run.` If you try to run a specific class' `main()`
    method, you may encounter strange issues, but this shouldn't happen with Gradle tasks.
  - If you had the LWJGL3 (or Desktop) option checked in the setup and you chose a non-empty
    template in the Templates tab, you can run the LWJGL3 or Desktop module right away.
  - If you had the Android option checked in the setup and have a non-empty template,
    you can try to run the Android module on an emulator or a connected Android device.
  - If you had the GWT option checked in the setup and have a non-empty template,
    you can go through the lengthy, but simple, build for GWT, probably using the `superDev`
    task for the `gwt` module, or also possibly the `dist` task in that module. 
  - If you had the iOS option checked in the setup, you're running Mac OS X,
    and you have followed all the steps for iOS development with libGDX, maybe you can run
    an iOS task? I can't try myself without a Mac or iOS device, so if you can get this to
    work, posting an issue with any info for other iOS targeters would be greately appreciated.
  - All builds currently use Gradle 5.4 with the "api/implementation/compile fiasco" resolved. Adding dependencies
    will use the `api` keyword instead of the `compile` keyword it used in earlier versions. All modules use the
    `java-library` plugin, which enables the `api` keyword for dependencies.
  - You may need to refresh the Gradle project after the initial import if some dependencies timed-out;
    JitPack dependencies in particular may take up to 15 minutes to become available if you're using any of those,
    like SquidLib. In IntelliJ IDEA, the `Refresh all projects` button is a pair of circling arrows in the Gradle
    tool window, which can be opened with `View -> Tool Windows -> Gradle`.
  - Out of an abundance of caution, [the dependency impersonation issue reported here by Márton
    Braun](https://blog.autsoft.hu/a-confusing-dependency/) is handled the way he handled it, by putting
    `jcenter()` last in the repositories lists. I don't know if any other tools have done the same, but it's
    an easy fix and I encourage them to do so.
    
Good luck, and I hope you make something great!

