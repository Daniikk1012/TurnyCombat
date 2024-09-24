# Turny Combat

An old game I with my friends when we were in seventh grade. Recently we
accidentally remembered that it existed, so in an old chat I found the APK,
which I decompiled and turned into a libGDX project. It is as authentic as it
can be, with some minor tweaks. Here's what I've done, with comments on how it
differs from the original:

1. Turned the APK into a JAR file using `dex2jar`;
2. Used `procyon` to decompile the JAR (Obviously, the decompiled files are not
exactly what I have written originally, but the logic is there);
3. Generated a new libGDX 1.12.1 project (Original was in 1.6.1, but I could not
get the `gdx-setup` to work. Plus, it's nice to have LWJGL 3 and newer android
version support);
4. Put the non-libGDX files into the project along with the assets from the
extracted APK (Originally the assets were in the `android/assets` directory, but
since it's a libGDX 1.12.1 project, I decided to use the new assets path);
5. Fixed decompiled code for anonymous classes. They had additional arguments
passed, which I had to remove, additional fields which I had to initialize, and
the names of the fields contained dollars, which I had to replace with
underscores so it compiled;
6. In `GameOverScreen.java`, had to change the `scrolled` method signature, and
add a new `touchCancelled` method. Both are ignored, so the logic was left
untouched.

All bugs are left untouched, like the "add black mana" button for second player
consuming mana of the first player, or the input viewport not updating on resize
on desktop (Don't remember that one being there originally, but even if it
wasn't, it is there now).
