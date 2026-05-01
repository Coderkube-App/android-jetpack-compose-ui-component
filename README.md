# Android Jetpack Compose Design System and Showcase App

A professional, production-ready Design System built with Jetpack Compose and Material 3. This project demonstrates advanced Android UI architecture, responsive layouts, and a comprehensive component library suitable for high-fidelity portfolio showcases.

## Key Features

### 1. Core Design System Foundation
*   **Dynamic Theme Engine**: Full support for Light/Dark modes and Brand-based Dynamic Themes using ColorScheme generation from seed colors.
*   **Design Tokens**: Centralized management of Spacing, Elevation, Typography, and Shapes using CompositionLocal.
*   **Material You**: Ready for Android 12+ dynamic wallpaper colors.

### 2. Responsive and Adaptive Layouts
*   **Window Size Classes**: Adaptive UI supporting Phones, Foldables, and Tablets.
*   **Responsive Scaffold**: Automatically switches between a Bottom Navigation Bar (Phone) and a Navigation Rail (Tablet/Landscape).
*   **Adaptive Grid**: Intelligent grid system that scales from 1 to 3 columns based on screen width.
*   **Master-Detail Layout**: Optimized side-by-side view for expanded screens.

### 3. Advanced Component Library
*   **Data and Insights**: AppBarChart for elegant, state-driven data visualization.
*   **Media Components**: AppAudioPlayer featuring playback controls, progress sliders, and gradient aesthetics.
*   **Advanced Inputs**:
    *   AppOtpField: A secure, 4-digit code input with custom states.
    *   AppTagPicker: Dynamic tag selection using Input Chips.
    *   AppSearchBar: Modern search interface with window inset support.
*   **Lists and Feedback**: Expandable list items, Modal Bottom Sheets, Snackbars, and interactive Dialogs.

### 4. Animation and Motion
*   **Motion Primitives**: Reusable FadeInVisibility and SlideUpVisibility wrappers.
*   **Visual Polish**: Shimmer loading skeletons, animated state transitions, and smooth navigation fades.

### 5. Interactive Documentation (Storybook)
*   **Component Catalog**: A comprehensive "Storybook" screen where every component is showcased in various states and variations.
*   **Typography and Color Showcase**: Visual guides to the system's design tokens and palette.
*   **Form Validation**: Real-time validation examples demonstrating error handling and state-driven UI logic.

## Architecture

The project follows Clean Architecture principles and the MVVM (Model-View-ViewModel) pattern:

*   **core/designsystem**: The "Single Source of Truth" for themes, tokens, and reusable components.
*   **core/navigation**: Centralized navigation graph and adaptive scaffold logic.
*   **features/**: Modular feature screens (Home, Catalog, Profile, Settings) with isolated ViewModels and state management via StateFlow.

## Tech Stack
*   **Language**: Kotlin
*   **UI Framework**: Jetpack Compose (Material 3)
*   **Architecture**: MVVM and Clean Architecture
*   **State Management**: StateFlow, ViewModel
*   **Navigation**: Jetpack Navigation Compose
*   **Concurrency**: Coroutines

---

## Getting Started

1.  Clone the repository.
2.  Open in Android Studio Hedgehog (or newer).
3.  Build and run on a phone or tablet emulator to see the responsive layout in action.
4.  Navigate to Settings to experiment with the Dynamic Brand Color Picker.

## License
This project is for educational and portfolio purposes. Feel free to use the components and architecture in your own projects!