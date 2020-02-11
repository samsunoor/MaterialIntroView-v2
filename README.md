# MaterialIntroView
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
- [ ] Add Sequence
- [ ] Singleton-based approach for unified experience across your app
- [x] Bug fixes


# Screenshot
<img src="https://raw.githubusercontent.com/shripal17/MaterialIntroView/master/art/materialintroviewgif.gif"/>

# Import
1. Add to project-level build.grdle
```groovy
buildscript {
  ...
}
allProjects {
  repositories {
    ...
    maven { url "https://dl.bintray.com/shripal17/codertainment" }
  }
}
```
2. Add to module-level build.gradle
```groovy
dependencies {
  ...
  implementation 'com.codertainment.materialintro:materialintroview-v2:2.0.0'
}
```

# Usage in Activity/Fragment
Use `activity?.materialIntro` in fragments
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
      infoText = ""
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

      viewId = "unique_id"
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
|focusGravity | FocusGravity.CENTER, FocusGravity.LEFT or FocusGravity.RIGHT | FocusGravity.CENTER |
| focusType | Focus.ALL, Focus.MINIMUM or Focus.NORMAL | Focus.NORMAL|
| padding | Padding (in px) for focusing the target view | 10 |
| dismissOnTouch | Dissmiss intro when user touches anywhere | false |
| isInfoEnabled | Whether to show info CardView | true |
| infoText | Text (CharSequence) to be displayed in info CardView | "" |
| infoTextColor | Text Color for info text | textColorPrimary |
| infoTextSize | Text size in sp for info text | 16sp |
| infoTextAlignment | Text alignment for info text | View.TEXT_ALIGNMENT_CENTER |
| infoTextTypeface | Custom typeface for info text | Typeface.DEFAULT |
| infoCardBackgroundColor | Info CardView background color | Inherit from active theme |
| isHelpIconEnabled | Whether to show the help icon in Info CardView | true |
| helpIconColor | Tint help Icon | Black |
| helpIconResource | Custom drawable Resource for help icon | NA |
| helpIconDrawable | Custom drawable for help icon | NA |
| infoCustomView | Custom view to be displayed inside info CardView | NA |
| infoCustomViewRes | Custom layout resource id to be inflated inside CardView | NA |
| isDotViewEnabled | Whether to show a dot at the centre of focus view | true |
| isDotAnimationEnabled | Whether to zoom-in and zoom-out dot icon periodically | true |
| dotIconColor | Tint Dot Icon | textColorPrimaryInverse |
| viewId | Unique ID of View so that MIV doesn't show again 2nd time onwards (if showOnlyOnce is enabled) | NA |
| targetView | View to be focused on | NA |
| isPerformClick | Click on the focused view when dismissing | false |
| showOnlyOnce | MIV should be shown only once | true |
| userClickAsDisplayed | MIV should be set as displayed only when user dismisses MIV manually, else MIV will be set as displayed as soon as it is rendered | true |
| shapeType | ShapeType.CIRCLE or ShapeType.RECTANGLE | ShapeType.CIRCLE |
| customShape | Use custom shape (Usage to be updated) | NA |
| materialIntroListener | Callback when user dismisses a view or it is not shown because it was set as displayed | NA |

# Listener
In your activity/fragment:
```kotlin
class GravityFragment : Fragment(), MaterialIntroListener {
// onUserClick is true when MIV has been dismissed through user click, false when MIV was previously displayed and was set as saved
override fun onIntroDone(onUserClick: Boolean, viewId: String) {
   // your action here
  }
...
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
      infoText = ""
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

      viewId = "unique_id
      targetView = viewToBeFocusedHere

      isPerformClick = false

      showOnlyOnce = true
      userClickAsDisplayed = true

      shapeType = ShapeType.CIRCLE
...
}
materialIntro(config = config) {
```

# Use Custom Shapes
You can use your own highlight shapes if Circle and Rectangle do not work for you. See source for `Circle` and `Rect` for implementation example.
> TODO update doc
```kotlin
class MyShape: Shape {
    // ... your implementation
}

//... in your app code

.setCustomShape(MyShape shape)

```

# Demos
![Alt text](/art/art_drawer.png?raw=true)
![Alt text](/art/art_focus_all.png?raw=true)
![Alt text](/art/art_focus_normal.png?raw=true)
![Alt text](/art/art_gravity_left.png?raw=true)
![Alt text](/art/art_rectangle.png?raw=true)

# Authors

[Mert SIMSEK](https://github.com/iammert)

[Murat Can BUR](https://github.com/muratcanbur)

[shripal17](https://github.com/shripal17)

License
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







