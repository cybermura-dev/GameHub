# GameHub

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Steps](#steps)
- [Usage](#usage)
  - [Game Listings](#game-listings)
  - [Game Details](#game-details)
- [How it Works](#how-it-works)
  - [Navigation](#navigation)
  - [Network Requests](#network-requests)
- [Project Structure](#project-structure)
  - [Core Components](#core-components)
  - [UI Components](#ui-components)
  - [Data Models](#data-models)
- [Build Configuration](#build-configuration)
  - [Gradle Dependencies](#gradle-dependencies)
  - [Retrofit Setup](#retrofit-setup)
- [API References](#api-references)
  - [IGDB API](#igdb-api)
- [License](#license)

## Overview

**GameHub** is a mobile application designed to provide users with a comprehensive list of games, including details such as descriptions, ratings, covers, platforms, and release dates. The app supports searching, pagination, and viewing detailed information about each game.

## Features

- **Game Listings:** Browse a list of games with pagination support.
- **Game Details:** View detailed information about a selected game, including its cover, description, platforms, and rating.
- **Search Functionality:** Filter the game list by entering a search term.

## Technologies

The project utilizes the following technologies:
- **Kotlin:** The app is written in Kotlin, leveraging modern Android development practices.
- **Retrofit:** For making HTTP requests to the IGDB API.
- **Glide:** For loading and displaying images efficiently.
- **OkHttp:** For handling HTTP client operations.

## Installation

### Prerequisites

Ensure the following tools and SDKs are installed before setting up the project:

- **Android Studio:** Make sure you have [Android Studio](https://developer.android.com/studio) installed.
- **Android Emulator or Device:** A physical Android device or emulator to run the app.
- **Android SDK** (min SDK: 26).
- **Kotlin** version 2.0.0 or higher.
- **Gradle** version 8.7.3.

### Steps

1. **Clone the Repository:**
     ```bash
     git clone https://github.com/cybermura-dev/GameHub.git
     cd GameHub
     ```

2. **Open in Android Studio:** Open the project in Android Studio.

3. **Sync the project with Gradle:**
  - Go to **File** > **Sync Project with Gradle Files**.

4. **Add your IGDB Client-ID and Token:** In the `IGDBApiService.kt` file, replace the following placeholders with your actual Client-ID and Token:
   ```kotlin
   @Headers(
        "Client-ID: YOUR_CLIENT_ID",
        "Authorization: Bearer YOUR_TOKEN",
        "Content-Type: text/plain"
   )
   ```

5. **Run the App:** Use the Android Studio toolbar to select a device and click Run to start the app.

## Usage

### Game Listings

**The HomeActivity** displays a list of games with pagination support.
1. Open the app.
2. Scroll through the list of games.
3. Tap on a game card to view detailed information.

### Game Details

**The GameDetailsActivity** shows detailed information about a selected game.
1. Open the app.
2. Scroll through the list of games.
3. Tap on a game card.
4. View the game's title, description, release date, rating, platforms, and cover image.

## How it Works

### Navigation

The app uses Android's native **Navigation Component** to handle in-app navigation between different screens such as the game list and game details.

### Network Requests

Network requests are handled using **Retrofit** to interact with the IGDB API for fetching game data.

### Project Structure

Here's an overview of the project's directory structure:

```bash
src/
├── main/
│   ├── AndroidManifest.xml          # Android Manifest file
│   ├── java/
│   │   └── ru/
│   │       └── takeshiko/
│   │           └── gamehub/
│   │               ├── adapters/   # Adapters for RecyclerViews
│   │               │   ├── GameAdapter.kt
│   │               │   └── GameDiffCallback.kt
│   │               │
│   │               ├── data/       # Data handling (repositories, data sources)
│   │               │   └── GameRepository.kt
│   │               │
│   │               ├── models/     # Data models representing entities
│   │               │   ├── Cover.kt
│   │               │   ├── Game.kt
│   │               │   └── Platform.kt
│   │               │
│   │               ├── network/    # Network layer, API calls and retrofit client
│   │               │   ├── IGDBApiService.kt
│   │               │   └── RetrofitClient.kt
│   │               │
│   │               ├── ui/         # UI components (activities, fragments)
│   │               │   ├── GameDetailsActivity.kt
│   │               │   ├── HomeActivity.kt
│   │               │   └── SplashActivity.kt
│   │               │
│   │               └── viewmodel/  # ViewModel layer for UI data management
│   │                   └── GameListViewModel.kt
└── res/                             # Application resources (layout, values, etc.)
```

### Core Components

Core components handle the essential functionalities of the app, such as networking, data retrieval, and core services:

- **`GameRepository.kt`**: Manages interactions with the IGDB API for fetching game data.
- **`RetrofitClient.kt`**: Configures and initializes the Retrofit client for network requests.

### UI Components

UI components define the app's user interface elements, including activities, adapters, and UI-specific logic:

- **Activities:**
  - `HomeActivity.kt`: The main entry point of the app where users can browse a list of games.
  - `GameDetailsActivity.kt`: Displays detailed information about a selected game.
  - `SplashActivity.kt`: Shows a splash screen when the app is launched.
  
- **Adapters:**
  - `GameAdapter.kt`: Binds game data to the RecyclerView for display.

- **Models**:
  - `Game.kt`: Represents a game with various attributes.
  - `Cover.kt`: Represents the cover image of a game.
  - `Platform.kt`: Represents a platform a game can be played on.
 
## Build Configuration

### Gradle Dependencies

The application uses the following dependencies:

- **Retrofit** for network requests.
- **Glide** for image loading.
- **OkHttp** for HTTP client operations.
- **AndroidX** libraries for UI components.

```gradle
dependencies {
    // App dependencies
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.dotsindicator)
    // Networking
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.glide)
}
```

```toml
[versions]
agp = "8.7.3"
constraintlayout = "2.2.0"
coreKtx = "1.15.0"
appcompat = "1.7.0"
fragmentKtx = "1.8.5"
glide = "4.16.0"
kotlin = "2.0.0"
loggingInterceptor = "4.12.0"
material = "1.12.0"
retrofit = "2.9.0"
shapeofview = "1.4.7"

[libraries]
androidx-appcompat = { module = "androidx.appcompat:appcompat", version.ref = "appcompat" }
androidx-constraintlayout = { module = "androidx.constraintlayout:constraintlayout", version.ref = "constraintlayout" }
androidx-core-ktx = { module = "androidx.core:core-ktx", version.ref = "coreKtx" }
androidx-fragment-ktx = { module = "androidx.fragment:fragment-ktx", version.ref = "fragmentKtx" }
converter-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "retrofit" }
glide = { module = "com.github.bumptech.glide:glide", version.ref = "glide" }
logging-interceptor = { module = "com.squareup.okhttp3:logging-interceptor", version.ref = "loggingInterceptor" }
material = { module = "com.google.android.material:material", version.ref = "material" }
retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
shapeofview = { module = "io.github.florent37:shapeofview", version.ref = "shapeofview" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
```

## Retrofit Setup
The **Retrofit** client is configured to use the IGDB API endpoint and includes logging for debugging purposes.

## API Reference

### IGDB API

The **IGDB API** is used to fetch game data, including names, covers, ratings, summaries, platforms, and release dates. Visit the [IGDB API Documentation](https://api-docs.igdb.com/) for more information.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Copyright (c) 2025 cybermura
