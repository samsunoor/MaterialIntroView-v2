# MaterialIntroView [![](https://jitpack.io/v/shripal17/MaterialIntroView-v2.svg)](https://jitpack.io/#shripal17/MaterialIntroView-v2) [ ![Download](https://api.bintray.com/packages/shripal17/codertainment/materialintroview-v2/images/download.svg?version=2.1.0) ](https://bintray.com/shripal17/codertainment/materialintroview-v2/2.1.0/link)![](https://img.shields.io/badge/SDK-21+-blueviolet)

Beautiful and highly customisable material-design based android library to help your users get started with your awesome app!
Based originally on [iammert/MaterialIntroView](https://github.com/iammert/MaterialIntroView).

Modifications/additions from the base lib:
- [x] Migrate to AndroidX
- [x] Migrated to Kotlin
- [x] Add customisation options like help icon color, card background color, dot icon color
- [x] Update Sample
- [x] Custom align text in info card
- [x] Custom help icon in info card
- [x] Custom typeface for info text
- [x] Custom Info View inside info card (using view or layout resource)
- [x] Kotlin extension function for activity
- [x] Full integration with MaterialIntroConfiguration
- [x] Enhanced MaterialIntroListener, know when user has clicked or MIV was dismissed because it was set as saved
- [x] Add option (userClickAsDisplayed) to set view intro as displayed only if user clicks on target view or outside too (if dismissOnTouch is enabled)
- [x] More kotlin friendly
- [x] Add Sequence
- [x] Singleton-based approach for unified experience across your app
- [x] Bug fixes
- [ ] Add skip button


# Screenshot
<img src="https://raw.githubusercontent.com/shripal17/MaterialIntroView-v2/master/art/home.png" width="360"/>

# Import
### Through bintray
1. Add to project-level build.gradle
```groovy
buildscript {
  //...
}
allProjects {
  repositories {
    //...
    maven { url "https://dl.bintray.com/shripal17/codertainment" }
  }
}
```
2. Add to module-level build.gradle
```groovy
dependencies {
  //...
  implementation 'com.codertainment.materialintro:materialintroview-v2:2.1.0'
}
```
### Through JitPack
```groovy
buildscript {
  //...
}
allProjects {
  repositories {
    //...
    maven { url "https://jitpack.io" }
  }
}
```
2. Add to module-level build.gradle
```groovy
dependencies {
  //...
  implementation 'com.github.shripal17:MaterialIntroView-v2:2.1.0'
}
```

# Single Usage in Activity/Fragment
```kotlin
val miv = materialIntro(show = true /* if you want to show miv instantly */) {
      maskColor = Color.BLUE
      delayMillis = 300

      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      fadeAnimationDurationMillis = 300

      focusType = Focus.ALL
      focusGravity = FocusGravity.CENTER

      padding = 24 // in px

      dismissOnTouch = false

      isInfoEnabled = true
      infoText = "Hello this is help message"
      infoTextColor = Color.BLACK
      infoTextSize = 18f
      infoTextAlignment = View.TEXT_ALIGNMENT_CENTER
      infoTextTypeface = Typeface.DEFAULT_BOLD
      infoCardBackgroundColor = Color.WHITE

      isHelpIconEnabled = true
      helpIconResource = R.drawable.your_icon
      helpIconDrawable = yourDrawable
      helpIconColor = Color.RED

      infoCustomView = yourViewHere
      infoCustomViewRes = R.layout.your_custom_view_here

      isDotViewEnabled = true
      isDotAnimationEnabled = true
      dotIconColor = Color.WHITE

      viewId = "unique_id" // or automatically picked from view's tag
      targetView = viewToBeFocusedHere

      isPerformClick = false

      showOnlyOnce = true
      userClickAsDisplayed = true

      shapeType = ShapeType.CIRCLE
    }
// if you want to show it later
miv.show(activity)
```

# Properties
| Name        | Description                    | Default Value |
|-------------|--------------------------------|---------------|
|maskColor    | The background color           | 46% Transparent |
|delayMillis  | Delay in ms for MIV (MaterialIntroView) to be shown  | 500 |
| isFadeInAnimationEnabled | Enable fade-in animation for MIV | true |
| isFadeOutAnimationEnabled | Enable fade-out animation for MIV | true |
|focusGravity | `FocusGravity.CENTER`, `FocusGravity.LEFT` or `FocusGravity.RIGHT` | `FocusGravity.CENTER` |
| focusType | `Focus.ALL`, `Focus.MINIMUM` or `Focus.NORMAL` | `Focus.NORMAL` |
| padding | Padding (in px) for focusing the target view | 10 |
| dismissOnTouch | Dismiss intro when user touches anywhere | false |
| isInfoEnabled | Whether to show info CardView | true |
| infoText | Text (CharSequence) to be displayed in info CardView | "" |
| infoTextColor | Text Color for info text | `textColorPrimary` |
| infoTextSize | Text size in sp for info text | 16sp |
| infoTextAlignment | Text alignment for info text | `View.TEXT_ALIGNMENT_CENTER` |
| infoTextTypeface | Custom typeface for info text | `Typeface.DEFAULT` |
| infoCardBackgroundColor | Info CardView background color | Inherit from active theme |
| isHelpIconEnabled | Whether to show the help icon in Info CardView | `true` |
| helpIconColor | Tint help Icon | Black |
| helpIconResource | Custom drawable Resource for help icon | NA |
| helpIconDrawable | Custom drawable for help icon | NA |
| infoCustomView | Custom view to be displayed inside info CardView | NA |
| infoCustomViewRes | Custom layout resource id to be inflated inside CardView | NA |
| isDotViewEnabled | Whether to show a dot at the centre of focus view | true |
| isDotAnimationEnabled | Whether to zoom-in and zoom-out dot icon periodically | true |
| dotIconColor | Tint Dot Icon | `textColorPrimaryInverse` |
| viewId | Unique ID of View so that MIV doesn't show again 2nd time onwards (if `showOnlyOnce` is enabled) | Automatically picked from view's `tag` |
| targetView | View to be focused on | NA |
| isPerformClick | Click on the focused view when dismissing | false |
| showOnlyOnce | MIV should be shown only once | true |
| userClickAsDisplayed | MIV should be set as displayed only when user dismisses MIV manually, else MIV will be set as displayed as soon as it is rendered | true |
| shapeType | `ShapeType.CIRCLE` or `ShapeType.RECTANGLE` | `ShapeType.CIRCLE` |
| customShape | Use custom shape (Usage to be updated) | NA |
| materialIntroListener | Callback when user dismisses a view or it is not shown because it was set as displayed | Current activity/fragment if it implements `MaterialIntroListener` |

# Listener
In your activity/fragment:
```kotlin
class GravityFragment : Fragment(), MaterialIntroListener {
  // onUserClick is true when MIV has been dismissed through user click, false when MIV was previously displayed and was set as saved
  override fun onIntroDone(onUserClick: Boolean, viewId: String) {
    // your action here
  }
  //...
}
```

# Configuration Method
```kotlin
//Create global config instance to not write same config again and again.
val config = MaterialIntroConfiguration().apply {
      maskColor = Color.BLUE
      delayMillis = 300

      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      fadeAnimationDurationMillis = 300

      focusType = Focus.ALL
      focusGravity = FocusGravity.CENTER

      padding = 24 // in px

      dismissOnTouch = false

      isInfoEnabled = true
      infoText = "Hello this is help message"
      infoTextColor = Color.BLACK
      infoTextSize = 18f
      infoTextAlignment = View.TEXT_ALIGNMENT_CENTER
      infoTextTypeface = Typeface.DEFAULT_BOLD
      infoCardBackgroundColor = Color.WHITE

      isHelpIconEnabled = true
      helpIconResource = R.drawable.your_icon
      helpIconDrawable = yourDrawable
      helpIconColor = Color.RED

      infoCustomView = yourViewHere
      infoCustomViewRes = R.layout.your_custom_view_here

      isDotViewEnabled = true
      isDotAnimationEnabled = true
      dotIconColor = Color.WHITE

      viewId = "unique_id" // or automatically picked from view's tag
      targetView = viewToBeFocusedHere

      isPerformClick = false

      showOnlyOnce = true
      userClickAsDisplayed = true

      shapeType = ShapeType.CIRCLE
}
materialIntro(config = config)
```
# Sequence (Added in v2.1.0)
Using MaterialIntroSequence, you can create a flow for intro view easily in your activity/fragments. Suppose your activity has multiple fragments and each fragment has some or the other view on which you want to show MIV but you want a specific sequence to be followed. In such cases, MaterialIntroSequence is your savior!

The usage is quite simple, call the extension function from your activity/fragment and add MaterialIntroConfiguration objects to it:

```kotlin
class YourFragment: Fragment(), MaterialIntroSequenceListener {

  //...
  
  override fun onResume() {
    super.onResume()
    materialIntroSequence(initialDelay = 1000, materialIntroSequenceListener = this) {
      add(
        MaterialIntroConfiguration(
          viewId = "viewId1",
          infoText = "Help for viewId1",
          infoCardBackgroundColor = Color.GREEN,
          helpIconColor = Color.BLUE,
          infoTextColor = Color.BLACK,
          dotIconColor = Color.RED,
          targetView = view1
        )
      )
      add(
        MaterialIntroConfiguration(
          viewId = "viewId2",
          infoText = "Help for viewId2",
          infoCardBackgroundColor = Color.GREEN,
          helpIconColor = Color.BLUE,
          infoTextColor = Color.BLACK,
          dotIconColor = Color.RED,
          targetView = view2
        )
      )
    }
  }
  
  override fun onProgress(onUserClick: Boolean, viewId: String, current: Int, total: Int) {
    toast("click: $onUserClick\nviewId: $viewId\ncurrent: $current\ntotal: $total")
  }

  override fun onCompleted() {
    toast("Tutorial Complete")
  }
}
```
# Use Custom Shapes
You can use your own highlight shapes if Circle and Rectangle do not work for you. See source for `Circle` and `Rect` for implementation example.
> TODO update doc

# More Screenshots
| Default config | Right align gravity | RecyclerView item |
|----------------|---------------------|-------------------|
| ![Default config](/art/home.png?raw=true) | ![Right align gravity](/art/gravity.png?raw=true) | ![RecyclerView item](/art/recycler.png?raw=true) |

| Focus All | Focus Minimum | Focus Normal |
|----------------|---------------------|-------------------|
| ![Focus All](/art/focus_all.png?raw=true) | ![Focus Minimum](/art/focus_min.png?raw=true) | ![Focus Normal](/art/focus_normal.png?raw=true) |

| Toolbar Item with sequence and custom colors | Custom Info View using resource layout | Custom Info View at runtime |
|----------------|---------------------|-------------------|
| ![Toolbar Item with sequence and custom colors](/art/toolbar_item_custom_colors.png?raw=true) | ![Custom Info View using resource layout](/art/custom_view_res.png?raw=true) | ![Custom Info View at runtime](/art/custom_view.png?raw=true) |

| Sequence with multiple fragments |
|----------------|
|<img src="https://raw.githubusercontent.com/shripal17/MaterialIntroView-v2/master/art/sequence_multiple_fragments.png" width="360"/> |

# Full Demo GIF
![Whole Video](/art/materialintroviewgif.gif?raw=true)

# Authors
[Mert SIMSEK](https://github.com/iammert)

[Murat Can BUR](https://github.com/muratcanbur)

[Shripal Jain (Me)](https://github.com/shripal17)

# Showcase
Apps using this library

Create a new issue to add your app here
- [Portal Controller](https://play.google.com/store/apps/details?id=com.portalcomputainment.android.controller.client)

# License
--------


    Copyright 2020 Shripal Jain

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.







