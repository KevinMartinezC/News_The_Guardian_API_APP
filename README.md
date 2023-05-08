# 📚 News App  🌐

🎉 Welcome to  News App! 📰 This is a modern, easy-to-use news application built with Jetpack Compose. Find the latest articles from The Guardian, save your favorite articles, and explore in detail.

## 📱 Features

- 📰 Search articles and filter by category
- 💖 Save your favorite articles
- 🌐 Browse articles in detail
- 🔄 Paginated article list

## 🧩 Components

### 🔍 SearchScreen

The `SearchScreen` component is used to search for articles and filter them by category. It displays a list of articles and allows users to save their favorite articles. This component takes several parameters such as a modifier, UI state, articles, loading status, search function, filter saving function, selected filter, and navigation controller.

### ⭐ FavoriteScreen

The `FavoriteScreen` component displays a list of favorite articles saved by the user. Users can remove articles from their favorites list and navigate to the detailed view of each article. This component takes parameters like favorite articles flow, remove from favorites function, and a navigation controller.

### 📖 DetailScreen

The `DetailScreen` component is used to display the detailed content of a selected article. It allows users to read the full article within the app. This component takes a modifier and the URL of the article as parameters.

### 🏠 App

The `App` class is the main application class and serves as the entry point for the application. It sets up the necessary dependencies and initializes the application. The `App` class also contains a constant for the database name used in the app.

### 📦 appModule

The `appModule` is a Koin module that sets up the dependency injection for the application. It provides instances of required services, repositories, and view models. The module includes definitions for HttpClient, GuardianApiService, GuardianRepository, DataStoreProvider, NewsDatabase, FavoriteArticlesRepository, SearchViewModel, and FavoritesViewModel.
