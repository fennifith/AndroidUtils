AndroidUtils is a collection of util classes that I use in basically every project - I might as well give them their own module. This is more for personal use (I don't expect most people to use this as a dependency in their own projects, other than stealing a snippet or two), but feel free to add your own contributions as you wish.

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
implementation 'me.jfenn:AndroidUtils:0.0.2'
```

### Methods

Most of these methods are fairly self-explanatory in terms of what they actually do. As I'm lazy, you can find auto-generated docs for all of them [here](https://jfenn.me/projects/androidutils/docs), or just browse the source code to see what they do.
