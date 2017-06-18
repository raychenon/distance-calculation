# Rayct-Native

React Native (RN) is not just "mobile web app" or a “hybrid app”.
RN uses the same fundamental UI building blocks as regular iOS and Android apps. React Native as the name suggests builds **native apps**.

# Introduction

This  Hack Week was an opportunity as an introduction to React-Native (RN). This is written from a native app developer's perspective during 2.5 days participation.

Not being familiar with Javascript and React, I followed this tutorial
https://app.pluralsight.com/library/courses/build-ios-apps-react-native/

And this one
https://app.pluralsight.com/library/courses/build-react-native-exponent-redux-apps

# Use cases

| Use case        | React-Native           | Native apps         |
| --------------- |:----------------------:| -------------------:|
| Designers and Developers finalize the UI with fast iteration| ++ hot reload makes interaction with designers much faster | Android and iOS apps must be re-compiled for any small changes |
| Desktop version is already written in React.js| ++ Can re-use the code in theory | Rewrite the same feature on each platform|
| Platform specific animations | Code in Javascript is often more complex than Java or Swift | ++ Nothing beats native apps  |

# The Good, the Bad and the Ugly

**Pros :**
- Don't Waste Time Recompiling. **The feedback loop between designers and developers is much faster.** Compare 3 sec to 5 min. Now designers and developers can sit next to each other to finalize the UI
- Use Native Code Only When You Need.
- most React Native views have an implementation in the platform
- calling Java/Swift code <--> RN code in both directions is possible. However there is no cross-language callback .

*List of Components*

| React Native | iOS         | Android  |
| ------------ |:-----------:| --------:|
|Text          | UILabel      | TextView |
|TextInput     | UITextField  | EditTextView|
|Image         | UIImageView | ImageView|
|ActivityIndicator | UIActivityIndicator | ProgressBar (not implemented)|
|.... | ... | ... |

**Cons :**
- not all native components are supported by React Native. Ex : Android's ProgressBar is not implemented by React's ActivityIndicator.
- iOS has lot of support. Android is 2nd class citizen. Specifics UI effects ( ripple effect, text underlined by default ... ) on Android are missing.
- Alongside its permissive BSD-style license, React Native ships with Facebook’s Additional Grant of Patent Rights, Version 2

**Cons specific to Javascript and React ( due to my unfamiliarity with React.js ) :**
- writing code in JS is much more complex than in Java or Swift. Ex : write Auth request , load image url into an Image ...
- the React API evolves too quickly. A lot of code is deprecated quickly.

# Which companies use React native
- https://facebook.github.io/react-native/showcase.html
- FACEBOOK GROUPS ( http://www.reactnative.com/built-with-react-native/ )

# Set up / Installation of React Native

In order to run this project, install HomeBrew http://brew.sh/ . After this, enter in the terminal
```
brew install node
brew install --HEAD watchman
brew install flow
```

**install React Native**
```
npm install -g react-native-cli
```

# Set up Android and iOS

You don't have to install XCode and the Android SDK.
You can install ExponentJS https://getexponent.com/

**Run React Native**

To run the app , go at the root of this project and run this cmd . Note : this command must be running while you play with the emulators.
```
react-native start
```

To install on Android device
```
react-native run-android
```

You can debug on localhost:8081/debugger-ui

# Special Thanks

for their time and patience during the HW#5 2016

- Lora Vardarova
- David Lubos
- Elisabeth Dietz
- Said Marouf
- Antonio Gutierrez , Pavel Zagalsky and Hafiz Hasanov  for presenting my project "Rayct Native" and theirs "Pappy" at the Project Fair :) .
