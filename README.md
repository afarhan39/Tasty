<h1 align="center">Tasty</h1>

<p align="center">  
Tasty is a small demo application based on modern Android application tech-stacks and MVVM architecture.<br>This project is for focusing especially on the Uberfusion task of creating Recipe app.
</p>
</br>

<p align="center">
<img src="/misc/tastyBackdrop.png"/>
</p>

## Download
Go to the [Releases](https://github.com/afarhan39/Tasty/blob/master/misc/Tasty-debug.apk) to download the latest APK.

## Tech stack & Open-source libraries
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.

## Architecture
Tasty is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)


Huge credits to __[skydoves](https://github.com/skydoves)__ for his nice template of Readme!

# License
```xml
Designed and developed by 2022 afarhan39 (Amir Farhan)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
