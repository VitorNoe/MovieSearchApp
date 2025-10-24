# 🎬 MovieSearchApp - Android Movie Search Application

A modern Android application built with Kotlin and Jetpack Compose that allows users to search for movies using the OMDb API. This app demonstrates best practices in Android development including MVVM architecture, Retrofit for API calls, and Material Design 3.

## 📱 Features

- 🔍 Search movies by title
- 🖼️ Display movie posters, titles, and release years
- 📱 Modern Material Design 3 UI
- ⚡ Reactive state management with Jetpack Compose
- 🌐 REST API integration with Retrofit
- 🎨 Loading states and error handling
- 📦 Clean architecture with MVVM pattern

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Retrofit + Gson
- **Image Loading:** Coil
- **Async Operations:** Kotlin Coroutines
- **Design:** Material Design 3
- **Minimum SDK:** API 34 (Android 14)

## 📋 Prerequisites

- Android Studio Hedgehog or newer
- Minimum SDK: API 34
- OMDb API Key (get it free at [omdbapi.com](https://www.omdbapi.com/apikey.aspx))

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/MovieSearchApp.git
cd MovieSearchApp
```

### 2. Get Your API Key

1. Visit [OMDb API](https://www.omdbapi.com/apikey.aspx)
2. Sign up for a free API key
3. Check your email for the activation link

### 3. Configure API Key

Open `app/src/main/java/com/example/moviesearchapp/data/remote/MovieApi.kt` and replace `YOUR_API_KEY`:

```kotlin
interface MovieApi {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String = "YOUR_API_KEY", // Replace with your key
        @Query("s") searchQuery: String
    ): MovieSearchResponse
}
```

### 4. Build and Run

1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Click **Run** or press `Shift + F10`
4. Select your device/emulator

## 📂 Project Structure

```
com.example.moviesearchapp/
├── data/                          # Data layer
│   ├── model/                     # Data models
│   │   ├── Movie.kt              # Movie data class
│   │   └── MovieSearchResponse.kt # API response model
│   ├── remote/                    # Network layer
│   │   ├── MovieApi.kt           # Retrofit API interface
│   │   └── RetrofitInstance.kt   # Retrofit singleton
│   └── repository/                # Repository pattern
│       └── MovieRepository.kt     # Data repository
├── ui/                            # UI layer
│   ├── components/                # Reusable UI components
│   │   ├── SearchBar.kt          # Search input component
│   │   ├── MovieCard.kt          # Movie item display
│   │   └── LoadingIndicator.kt   # Loading state UI
│   ├── screens/                   # App screens
│   │   └── MovieSearchScreen.kt  # Main search screen
│   └── theme/                     # App theming
│       └── Theme.kt              # Material 3 theme
├── viewmodel/                     # ViewModel layer
│   └── MovieViewModel.kt         # Business logic
└── MainActivity.kt                # App entry point
```

## 🏗️ Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture pattern:

```
┌─────────────┐
│     View    │  (Jetpack Compose UI)
│  (Screen)   │
└──────┬──────┘
       │
       ↓
┌─────────────┐
│  ViewModel  │  (State Management & Business Logic)
└──────┬──────┘
       │
       ↓
┌─────────────┐
│ Repository  │  (Data Source Abstraction)
└──────┬──────┘
       │
       ↓
┌─────────────┐
│   API/Data  │  (Retrofit + Network)
└─────────────┘
```

### Key Components

#### Data Layer
- **Movie.kt**: Data class representing a movie entity
- **MovieSearchResponse.kt**: API response wrapper
- **MovieApi.kt**: Retrofit interface defining API endpoints
- **RetrofitInstance.kt**: Singleton providing Retrofit instance
- **MovieRepository.kt**: Abstracts data sources

#### ViewModel Layer
- **MovieViewModel.kt**: Manages UI state and business logic
  - Handles search queries
  - Manages loading states
  - Handles errors
  - Exposes reactive state to UI

#### UI Layer
- **MovieSearchScreen.kt**: Main composable screen
- **SearchBar.kt**: Search input component
- **MovieCard.kt**: Individual movie display card
- **LoadingIndicator.kt**: Loading state indicator

## 🔑 Key Features Explained

### 1. Reactive State Management

Using Jetpack Compose's `mutableStateOf`:

```kotlin
var movies by mutableStateOf<List<Movie>>(emptyList())
var isLoading by mutableStateOf(false)
```

The UI automatically recomposes when state changes.

### 2. Asynchronous API Calls

Using Kotlin Coroutines and `viewModelScope`:

```kotlin
viewModelScope.launch {
    isLoading = true
    val response = repository.searchMovies(query)
    isLoading = false
    // Handle response
}
```

### 3. Image Loading with Coil

```kotlin
AsyncImage(
    model = movie.Poster,
    contentDescription = movie.Title,
    modifier = Modifier.fillMaxSize()
)
```

### 4. Error Handling

Graceful error handling with user-friendly messages:

```kotlin
try {
    RetrofitInstance.api.searchMovies(searchQuery = query)
} catch (e: Exception) {
    MovieSearchResponse(
        Response = "False",
        Error = e.message ?: "Unknown error occurred"
    )
}
```

## 📦 Dependencies

```kotlin
// Core Android
implementation("androidx.core:core-ktx:1.12.0")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

// Compose
implementation(platform("androidx.compose:compose-bom:2024.02.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")

// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

// Retrofit for networking
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Coil for image loading
implementation("io.coil-kt:coil-compose:2.5.0")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

## 🎨 UI/UX Features

- **Material Design 3**: Modern, accessible design system
- **Responsive Layout**: Adapts to different screen sizes
- **Loading States**: Visual feedback during API calls
- **Error Messages**: Clear communication of issues
- **Empty States**: Helpful prompts when no results
- **Card-based Layout**: Clean, organized movie display

## 🧪 Testing the App

### Manual Testing Steps

1. **Initial State**
   - Launch app
   - Verify "Search for your favorite movies!" message appears

2. **Search Functionality**
   - Enter "Batman" in search field
   - Click search button
   - Verify loading indicator appears
   - Verify results display with posters

3. **Error Handling**
   - Search with empty field → Error message appears
   - Search for "xyz123abc" → "No movies found" message

4. **Edge Cases**
   - Test with special characters
   - Test with very long movie titles
   - Test with no internet connection

## 🚀 Future Enhancements

- [ ] **Movie Details Screen**: Full information view
- [ ] **Favorites System**: Save favorite movies locally
- [ ] **Dark/Light Theme Toggle**: Manual theme switching
- [ ] **Pagination**: Load more results
- [ ] **Filter Options**: By year, type (movie/series)
- [ ] **Recent Searches**: History of searches
- [ ] **Offline Mode**: Cache recent searches
- [ ] **Share Functionality**: Share movies with friends
- [ ] **Advanced Search**: Multiple filters
- [ ] **Watchlist**: Track movies to watch

## 📝 Code Examples

### Making an API Call

```kotlin
// In MovieViewModel.kt
fun searchMovies() {
    viewModelScope.launch {
        isLoading = true
        errorMessage = null
        
        val response = repository.searchMovies(query)
        
        isLoading = false
        
        if (response.Response == "True") {
            movies = response.Search ?: emptyList()
        } else {
            errorMessage = response.Error ?: "Failed to fetch movies"
        }
    }
}
```

### Creating a Composable

```kotlin
@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {
    Card(onClick = onClick) {
        Row {
            AsyncImage(model = movie.Poster)
            Column {
                Text(text = movie.Title)
                Text(text = movie.Year)
            }
        }
    }
}
```

## 🐛 Troubleshooting

### Common Issues

**Issue**: "Unable to resolve dependency"
- **Solution**: Click "Sync Now" in build.gradle.kts

**Issue**: No internet permission error
- **Solution**: Verify `<uses-permission android:name="android.permission.INTERNET" />` in AndroidManifest.xml

**Issue**: API returns "Invalid API key"
- **Solution**: Check your API key and activation status

**Issue**: Images not loading
- **Solution**: Ensure Coil dependency is added and internet permission granted

**Issue**: App crashes on search
- **Solution**: Verify all imports are correct and Retrofit is properly configured

## 📚 Learning Resources

### Concepts Covered

| Concept | Description | Files |
|---------|-------------|-------|
| **Kotlin Basics** | Data classes, suspend functions | `Movie.kt`, `MovieApi.kt` |
| **Jetpack Compose** | Declarative UI framework | All files in `ui/` |
| **MVVM Architecture** | Separation of concerns | `MovieViewModel.kt`, `MovieRepository.kt` |
| **State Management** | `mutableStateOf`, reactive updates | `MovieViewModel.kt` |
| **Retrofit** | REST API consumption | `MovieApi.kt`, `RetrofitInstance.kt` |
| **Coroutines** | Asynchronous programming | `MovieViewModel.kt`, `MovieRepository.kt` |
| **Material Design 3** | Modern UI components | `Theme.kt`, all UI components |
| **Coil** | Async image loading | `MovieCard.kt` |
| **Repository Pattern** | Data abstraction | `MovieRepository.kt` |

### Additional Resources

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Material Design 3](https://m3.material.io/)
- [OMDb API Documentation](https://www.omdbapi.com/)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### Steps to Contribute

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [OMDb API](https://www.omdbapi.com/) for providing the movie database
- [Material Design](https://material.io/) for design guidelines
- Android Developer Community for excellent documentation

## 📱 Screenshots


