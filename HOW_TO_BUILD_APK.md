# SaveIt — How to Build the APK

## Option A: Android Studio (Easiest — 2 clicks)

1. Download & install **Android Studio** (free): https://developer.android.com/studio
2. Open Android Studio → **File → Open** → select this `SaveIt` folder
3. Wait for Gradle sync (1–2 min, automatic)
4. Click **Build → Build Bundle(s) / APK(s) → Build APK(s)**
5. Click **"locate"** in the popup → your APK is ready!
6. Transfer the APK to your phone and install it.

> On your phone: Settings → Security → allow "Install unknown apps" for the file manager you use to open the APK.

---

## Option B: Command Line (if you have Android SDK installed)

```bash
cd SaveIt
./gradlew assembleDebug
# APK will be at: app/build/outputs/apk/debug/app-debug.apk
```

---

## What the app does

- Wraps the SaveIt HTML downloader in a full-screen Android WebView
- Supports YouTube, Instagram, Facebook, TikTok, Twitter, SoundCloud
- Download links open in the system browser for easy saving
- Works on Android 5.0+ (API 21+)
- No extra libraries needed — pure WebView
