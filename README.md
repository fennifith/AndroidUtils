AndroidUtils is a collection of util classes that I use in almost every project - I might as well give them their own module. This is more for personal use (I don't expect most people to use this as a dependency in their own projects, other than stealing a snippet or two), but feel free to add your own contributions as you wish.

[![](https://jitpack.io/v/me.jfenn/AndroidUtils.svg)](https://jitpack.io/#me.jfenn/AndroidUtils)

## Usage

### Setup

This project is published on [JitPack](https://jitpack.io), which you can add to your project by copying the following to your root build.gradle at the end of "repositories".

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

To add the dependency, copy this line into your app module's build.gradle file.

```gradle
implementation 'me.jfenn:AndroidUtils:0.0.5'
```

### Methods

#### ViewUtils

Various extension functions/delegates to safely reference view ids in activities, fragments, and other Android components.

```kotlin
val button: MaterialButton? = findView(R.id.button)
val recyclerView: RecyclerView? by bind(R.id.recycler)
```

Also: `View.setBackgroundTint(color)` will wrap & tint a view's background drawable. 

#### Prefs

A bunch of generic-typed preference utility functions.

```kotlin
// editing a SharedPreferences object (automatically runs .apply())
sharedPrefs.edit {
    putInt("themeKey", THEME_DARK)
}

// setter/getter operator functions
val theme: Int = sharedPrefs["themeKey"]
sharedPrefs["themeKey"] = THEME_LIGHT
```

Property delegates can be used to greatly simplify SharedPreferences operations.

```kotlin
// binding a single property
val theme by sharedPrefs.get<Int>("themeKey", defaultValue = THEME_LIGHT)

// uses the delegated property name ("themeKey") if a key is not provided
val themeKey by sharedPrefs.get<Int>(defaultValue = THEME_LIGHT)
```

##### TypedPreference

TypedPreference objects can be used to create a set of typed "pointers" to reference throughout your project, instead of relying on a hardcoded preference key/string.

```kotlin
// constructs a TypedPreference<Int> with the key "PREF_THEME"
val PREF_THEME by pref<Int>(defaultValue = THEME_LIGHT)

// in another file...
var theme by sharedPrefs.get(PREF_THEME)

// alternatively, in an activity/fragment/etc (if using PreferenceManager.getDefaultSharedPreferences)
var theme by prefs(PREF_THEME)
```

#### DimenUtils

Contains two `Context` extension functions: `getStatusBarHeight()` and `getNavigationBarHeight()`, that both work accordingly.

Also includes some unit conversion functions that use `Resources.getSystem()` to find the display density:
- `dpToPx(Float) : Int`
- `spToPx(Float) : Int`
- `pxToDp(Int) : Float`
- `pxToSp(Int) : Float` 
