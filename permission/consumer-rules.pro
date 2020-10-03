-keep class com.fmdc.permission.EasyPermission
-keep @com.fmdc.permission.EasyPermission class * { *; }

-keepclassmembers class com.fmdc.permission.* {
   public *;
}

-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

-dontwarn
-dontnote
-dontoptimize
-dontobfuscate
