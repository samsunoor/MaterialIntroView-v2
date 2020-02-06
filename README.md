# MaterialIntroView
Material Intro View is a showcase android library based originally on [iammert/MaterialIntroView](https://github.com/iammert/MaterialIntroView).

Modifications/additions from the base lib:
- [ ] Migrated to Kotlin
- [ ] Added Sequence
- [ ] Singleton-based approach for unified experience across your app
- [ ] Center-align text in balloon
- [ ] Custom helper icon in ballon
- [ ] Bug fixes


# Screen
<img src="https://raw.githubusercontent.com/shripal17/MaterialIntroView/master/art/materialintroviewgif.gif"/>

# Usage
```kotlin
MaterialIntroView.Builder(this).apply {
enableDotAnimation(true)
enableIcon(false)
setFocusGravity(FocusGravity.CENTER)
setFocusType(Focus.MINIMUM)
setDelayMillis(500)
enableFadeAnimation(true)
performClick(true)
setInfoText("Hi There! Click this card and see what happens.")
setShapeType(ShapeType.CIRCLE)
setTarget(view)
setUsageId("intro_card") //THIS SHOULD BE UNIQUE ID
show()
}
```

# Import
Not yet available through maven

# Builder Methods
```kotlin
.setMaskColor(Color.Blue) 
```
```kotlin
.setDelayMillis(3000) //starts after 3 seconds passed
```
```kotlin
.enableFadeAnimation(true) //View will appear/disappear with fade in/out animation
```
```kotlin
//ie. If your button's width has MATCH_PARENT.
//Focus.ALL is not a good option. You can use
//Focus.MINIMUM or Focus.NORMAL. See demos below.
.setFocusType(Focus.MINIMUM)
.setFocusType(Focus.NORMAL)
.setFocusType(Focus.ALL)
```
```kotlin
//ie. You can focus on left of RecyclerView list item.
.setFocusGravity(FocusGravity.LEFT)
.setFocusType(FocusGravity.CENTER)
.setFocusType(FocusGravity.RIGHT)
```
```kotlin
.setTarget(myButton) //Focus on myButton
```
```kotlin
.setTargetPadding(30) //add 30px padding to focus circle
```
```kotlin
.setInfoText("This is info text!") //Setting text will enable info dialog
```
```kotlin
.setTextColor(Color.Black) //Info dialog's text color is set to black
```
```kotlin
.setInfoTextSize(30) //Change text size
```
```kotlin
.setShapeType(ShapeType.CIRCLE) //Change shape of focus area
.setShapeType(ShapeType.RECTANGLE) //Change shape of focus area
```
```kotlin
.setCustomShape(Shape shape) //Use custom shape
```
```kotlin
// Allow this showcase overlay to only show up once. Prevents multiple screens from showing at the same time.
// Useful if you wish to show a tour step in a code that gets called multiple times
.setIdempotent(true)
```
```kotlin
.setUsageId("intro_fab_button") //Store intro view status whether it is learnt or not
```
```kotlin
.enableDotAnimation(true) //Shows dot animation center of focus area
```
```kotlin
.enableIcon(false) //Turn off helper icon, default is true
```
```kotlin
.performClick(true) //Trigger click operation when user click focused area.
```
```kotlin
//If you don't want to perform click automatically
//You can disable perform clik and handle it yourself
.setListener{ viewUsageId: String ->

}               
```
# Configuration Method
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







