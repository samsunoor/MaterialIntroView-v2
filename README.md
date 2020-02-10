# MaterialIntroView
Material Intro View is a showcase android library based originally on [iammert/MaterialIntroView](https://github.com/iammert/MaterialIntroView).

Modifications/additions from the base lib:
- [x] Migrate to AndroidX
- [x] Migrated to Kotlin
- [x] Add customisation options like help icon color, card background color, dot icon color
- [x] Update Sample
- [x] Center-align text in info card
- [ ] Add Sequence
- [ ] Singleton-based approach for unified experience across your app
- [ ] Custom helper icon in ballon
- [ ] Bug fixes


# Screen
<img src="https://raw.githubusercontent.com/shripal17/MaterialIntroView/master/art/materialintroviewgif.gif"/>

# Usage
```kotlin
MaterialIntroView(activity).apply {
      isDotViewEnabled = true
      isDotAnimationEnabled = true
      maskColor = Color.BLACK
      padding = 24
      dismissOnTouch = false
      isHelpIconEnabled = true
      isInfoEnabled = true
      focusType = Focus.MINIMUM // or Focus.ALL or Focus.NORMAL
      focusGravity = FocusGravity.CENTER // or FocusGravity.LEFT or FocusGravity.RIGHT
      delayMillis = 100
      fadeAnimationDurationMillis = 200
      isFadeInAnimationEnabled = true
      isFadeOutAnimationEnabled = true
      cardBackgroundColor = Color.RED
      dotIconColor = Color.GREEN
      helpIconColor = Color.BLUE
      isPerformClick = true
      setInfoText("Your info text comes here")
      setTarget(viewToBeFocused)
      materialIntroListener = this@Activity
      viewId = id // unique id
      show(this@Activity)
    }
```

# Import
Not yet available through maven

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
| isHelpIconEnabled| Whether to show the help icon in Info CardView | true |
| isInfoEnabled | Whether to show info CardView | true |
| isDotViewEnabled | Whether to show a dot at the centre of focus view | true |
| isDotAnimationEnabled | Whether to zoom-in and zoom-out dot icon periodically | true |
| viewId | Unique ID of View so that MIV Doesn't show again 2nd time onwards | NA |
| shapeType | ShapeType.CIRCLE or ShapeType.RECTANGLE | ShapeType.CIRCLE |
| dotIconColor | Tint Dot Icon | White |
| helpIconColor | Tint help Icon | Black |
| cardBackgroundColor | Info CardView Background Color | White |
| isPerformClick | Click on the focused view when dismissing | false |
| materialIntroListener | Callback when user dismisses a view | NA |
| customShape | Use custom shape (Usage to be updated) | NA |

# Methods
| Method        | Description                    |
| setInfoTextColor(Int) | set text color of info text |
| setInfoText(CharSequence) | Set text of info text |
| setInfoTextSize(Float) | Set text size in sp for info text|

# Configuration Method
## Incomplete yet
```kotlin
//Create global config instance to not write same config to builder
//again and again.
val config = MaterialIntroConfiguration().apply {
setDelayMillis(1000)
setFadeAnimationEnabled(true)
}
...
materialInfoViewBuilder.setConfiguration(config)
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
# TODO
> To Be Updated

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







