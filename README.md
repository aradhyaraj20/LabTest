# CampusNews Android Application

CampusNews is an Android application developed for students to receive important campus updates. It adheres to academic lab exam requirements (**Q2. Campus News**), utilizing standard Android components such as Activities, Fragments, Intents, and Notifications.

---

## Features & Architecture

### 1. Home Activity (`MainActivity`)
- **College Logo**: Displays the custom college logo vector asset (`ic_logo`).
- **Application Title**: Displays **CampusNews**.
- **Welcome Message**: Greets students with a welcome banner.
- **Category Buttons**: Three interactive category buttons:
  - **Academic**
  - **Events**
  - **Placements**

### 2. Fragments (`NewsFragment`)
- Instead of creating separate activities for each category, category updates are displayed dynamically inside a reusable **Fragment**.
- **Fragment Components**:
  - Category Title
  - Category News / Update Image (`ic_academic`, `ic_events`, or `ic_placements`)
  - Short Description
  - **Read More** button

### 3. Intents & Article Activity (`ArticleActivity`)
- Clicking **Read More** triggers an `Intent` that passes the selected article title, full content, and update image to **ArticleActivity**.
- **Article Activity Components**:
  - College Logo header
  - Full Article Title & Image
  - Complete Article Text
  - **Back** button to return to the Home Activity.

### 4. Popup (Heads-Up) Notifications
- When **Read More** is selected, a high-priority local notification (`NotificationCompat` with `NotificationManager.IMPORTANCE_HIGH`) is generated.
- Appears as a **heads-up popup banner** on the screen containing the selected article title.
- Tapping the notification opens the corresponding article directly.
- Handles Android 13+ (`POST_NOTIFICATIONS`) runtime permissions gracefully.

---

## Project Structure

```text
com.example.labexam/
├── MainActivity.kt        # Home Activity with category buttons & Fragment container
├── NewsFragment.kt        # Reusable Fragment displaying category news & triggering notifications
└── ArticleActivity.kt     # Detail Activity displaying full article content & Back button

res/
├── layout/
│   ├── activity_main.xml     # Home layout
│   ├── fragment_news.xml     # News fragment layout
│   └── activity_article.xml  # Article detail layout
└── drawable/
    ├── ic_logo.xml           # College logo
    ├── ic_academic.xml       # Academic category image
    ├── ic_events.xml         # Events category image
    └── ic_placements.xml     # Placements category image
```

---

## How to Build and Run

1. Open the project in **Android Studio**.
2. Sync Gradle if prompted.
3. Click the **Run** button (▶) to build and deploy the app to an emulator or physical Android device (API 24+).
  

## screenshots
![Screenshot 2026-09-11 140006.png](Screenshots/Screenshot%202026-09-11%20140006.png)
![Screenshot 2026-09-11 140019.png](Screenshots/Screenshot%202026-09-11%20140019.png)
![Screenshot 2026-09-11 140030.png](Screenshots/Screenshot%202026-09-11%20140030.png)
![Screenshot 2026-09-11 140055.png](Screenshots/Screenshot%202026-09-11%20140055.png)
![Screenshot 2026-09-11 140118.png](Screenshots/Screenshot%202026-09-11%20140118.png)