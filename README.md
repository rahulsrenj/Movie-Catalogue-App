# MovieCatalogue Android App

An Android app that fetches and displays movies from The Movie Database (TMDb) API in multiple categories with a clean and immersive UI.

This app uses modern Android development practices and libraries:

- **Retrofit**: For efficient and easy network calls to the TMDb API.  
- **Glide**: To load and cache movie poster images smoothly.  
- **MVVM Architecture**: Separates UI logic from data handling using `ViewModel` and `Repository` for clean, maintainable code.  
- **LiveData**: Enables reactive UI updates by observing data changes automatically.  
- **Data Binding**: Binds UI components in layouts directly to data sources, reducing boilerplate and improving performance.

---

## Features

- Fetches and displays movies in multiple categories:  
  - Popular  
  - Upcoming  
  - Now Playing  
  - Top Rated  
- Click on any movie to see detailed preview via a bottom sheet dialog  
- Clean UI with horizontal RecyclerViews and ViewPager2 image slider  
- MVVM architecture with LiveData and ViewModel  
- Retrofit2 integration for network calls  
- Data Binding and Glide for image loading  

---

## Screenshots

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/7b8f4d35-8416-4459-816c-fd4ad94e9b6a" alt="Home Page" width="300"/><br>
      <em>Home Page</em>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/39e70de0-b42a-4df9-8435-5e2aac92a88a" alt="Scroll Down" width="300"/><br>
      <em>Scroll Down</em>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/b1c7e5cd-8dd1-4bd5-a860-e076984d0581"  alt="Scroll Down" width="300"/><br>
      <em>Movie Preview</em>
    </td>
  </tr>
</table>

---

## Getting Started

### Prerequisites

- Android Studio  
- A TMDb API key ([Sign up here](https://www.themoviedb.org/))  

### Dependencies

- AndroidX Libraries  
- Retrofit2  
- Gson Converter  
- Glide  
- Data Binding  

### Installation

```bash
git clone https://github.com/rahulsrenj/Movie-Catalogue-App.git
