# 📱 Performance Monitor Android App

A modular, clean-architecture Android app built with Jetpack Compose for real-time performance monitoring including CPU, RAM, FPS, Internet Speed, and Battery Health.

---

## 🧱 Project Structure

```
app/
└── src/
    └── main/
        └── java/
            └── com/
                └── mories/
                    └── performance_monitor/
                        ├── core/                  # System-level utilities (CPU, RAM, Battery, Network)
                        │   ├── monitor/
                        │   └── util/
                        ├── data/                  # Repository layer
                        ├── domain/                # UseCases
                        ├── presentation/
                        │   └── navigation/        # AppNavGraph, AppDestination
                        └── ui/
                            └── screen/
                                ├── viewmodel/     # HomeViewModel, HomeState, HomeEvent
                                └── HomeRoute.kt   # Composable route
```

---

## ⚙️ Features

- 🧠 **CPU Monitor**: Usage percentage, core count, frequency, vendor, architecture
- 💾 **RAM Monitor**: Total, used, and free memory
- 🌐 **Internet Speed**: Download speed test in Mbps (real-time)
- 🎮 **FPS Monitor**: Frame-per-second using Choreographer
- 🔋 **Battery Health**: Level, temperature, charging status

---

## 🔨 Tech Stack

- **Jetpack Compose**
- **Kotlin Coroutines & StateFlow**
- **Android Architecture Components**
- **MVVM + UseCase + Clean Architecture**
- **Manual DI (No Hilt)**
- **Modular Codebase**

---

## 🚀 Getting Started

1. Clone the repo:
```bash
git clone https://github.com/elnasio/performance_monitor.git
```

2. Open in Android Studio.

3. Run on a physical device or emulator (Android 8.0+).

---

## 🔄 Live Data Updates

- All system metrics are updated continuously using Kotlin Flow.
- The ViewModel launches 3 independent coroutines for CPU, RAM, and Internet monitoring.
- FPS is captured using `Choreographer.FrameCallback` and pushed to UI via `StateFlow`.

---

## 📂 Main Files

### 🧭 Navigation
- `AppNavGraph.kt`: Defines routes using sealed classes and type-safe parameters
- `AppDestination.kt`: Manages route name and deep link construction

### 🖼 UI
- `HomeRoute.kt`: Entry point with ViewModel injection
- `HomeScreen.kt`: Displays all performance metrics with scroll support
- `AppToolbar.kt`: Reusable top bar with customizable paddings

### 🧠 ViewModel
- `HomeViewModel.kt`: Aggregates all monitoring logic
- `HomeState.kt`: Contains all performance data
- `HomeEvent.kt`: Triggers actions like `LoadAll`

---

## 🧪 Test Coverage

Unit testing enabled for:
- UseCases (Cpu, Ram, InternetSpeed)
- ViewModel state emissions

To run tests:
```bash
./gradlew testDebugUnitTest
```

---

## 📱 Screenshot (optional)
> To be added

---

## 📌 TODOs

- [ ] Add dark mode toggle
- [ ] Export reports
- [ ] Add CPU temperature (via NDK or root-only paths)

---

## 🧑‍💻 Author

**Mories Deo Hutapea**  
Indonesia 🇮🇩

---

## 📃 License

MIT License © 2025