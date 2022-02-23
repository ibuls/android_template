# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes *Annotation*
#-dontnote kotlinx.serialization.SerializationKt
#-keep,includedescriptorclasses class com.yourcompany.yourpackage.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class com.dev.core.models.** {
*;
}
#-keepclasseswithmembers class com.yourcompany.yourpackage.** { # <-- change package name to your app's
#    kotlinx.serialization.KSerializer serializer(...);
#}


# FOR Models

-keep public class com.dev.core.models.** {
  public void set*(***);
  public *** get();
}

#MP chart
-keep class com.github.mikephil.charting.*{ *; }

#-dontwarn com.airbnb.lottie.**
-keep class com.airbnb.lottie.* {*;}



# Retrofit
-keep class com.google.gson.* { *; }
-keep public class com.google.gson.* {public private protected *;}
-keep class com.google.inject.* { *; }
-keep class org.apache.http.* { *; }
-keep class org.apache.james.mime4j.* { *; }
-keep class javax.inject.* { *; }
-keep class javax.xml.stream.* { *; }
-keep class retrofit.* { *; }
-keep class com.google.appengine.* { *; }
-keepattributes *Annotation*
-keepattributes Signature
#-dontwarn com.squareup.okhttp.*
#-dontwarn rx.**
#-dontwarn javax.xml.stream.**
#-dontwarn com.google.appengine.**
#-dontwarn java.nio.file.**
#-dontwarn org.codehaus.**

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
#-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions


# Proguard for Glide

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Hawk
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *; }

-keepattributes MethodParameters



#UCrop

-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }


# Calendar View
-keep class com.haibin.calendarview.MonthView {
    public <init>(android.content.Context);
}
-keep class com.haibin.calendarview.WeekBar {
    public <init>(android.content.Context);
}
-keep class com.haibin.calendarview.WeekView {
    public <init>(android.content.Context);
}
-keep class com.haibin.calendarview.YearView {
    public <init>(android.content.Context);
}

# Starting from Android Gradle plugin 4.1.0 fields from R classes are no longer kept by default
-keepclassmembers class your.library.package.R$* {
   public static <fields>;
}