# CampusNews App Implementation Plan

Implement the **CampusNews** Android application based on the exam specification (Q2. Campus News).

## User Review Required

> [!IMPORTANT]
> The app will use standard Android Views, Fragments, Intents, and Notifications (`NotificationCompat` with Notification Channels for modern Android compatibility), conforming to standard academic lab exam expectations.

## Open Questions

- None. The requirements are clear: Home Activity with logo, title, welcome message, and 3 category buttons (Academic, Events, Placements). Selecting a category loads a Fragment containing category title, update image, short description, and a "Read More" button. Clicking "Read More" opens an Article Activity via Intent and triggers a Notification with the article/category title.

## Proposed Changes

### UI & Layouts (`res/layout`)

#### [NEW] [activity_main.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/layout/activity_main.xml) (Update existing)
- Update layout to include:
  - College logo (ImageView)
  - Application title ("CampusNews")
  - Welcome message (TextView)
  - Three category buttons (`btn_academic`, `btn_events`, `btn_placements`)
  - A `FrameLayout` / `FragmentContainerView` (`fragment_container`) to host the category Fragment.

#### [NEW] [fragment_news.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/layout/fragment_news.xml)
- Layout for the news Fragment containing:
  - Category Title (TextView)
  - News Image (ImageView)
  - Short Description (TextView)
  - Read More Button (Button)

#### [NEW] [activity_article.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/layout/activity_article.xml)
- Layout for Article Activity containing:
  - Article Title / Header
  - Complete Article Text (TextView)
  - Back Button / Up navigation (Button or Toolbar)

### Activities & Fragments (`java/com/example/labexam`)

#### [MODIFY] [MainActivity.kt](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/java/com/example/labexam/MainActivity.kt)
- Handle click listeners for the three category buttons.
- Dynamically replace or load the News Fragment (`NewsFragment`) passing the selected category details (title, image, short description, full article content).

#### [NEW] [NewsFragment.kt](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/java/com/example/labexam/NewsFragment.kt)
- Fragment implementation:
  - Binds category title, image, and short description.
  - Handles "Read More" click event:
    - Launches `ArticleActivity` via an `Intent`.
    - Generates a local Notification containing the selected article/category title.
    - Requests `POST_NOTIFICATIONS` permission if running on API 33+.

#### [NEW] [ArticleActivity.kt](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/java/com/example/labexam/ArticleActivity.kt)
- Receives intent extras (article title and complete content).
- Displays complete article content.
- Provides a Back button to return to Home Activity.

### Manifest & Resources (`AndroidManifest.xml`, `strings.xml`)

#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/AndroidManifest.xml)
- Register `ArticleActivity`.
- Add `POST_NOTIFICATIONS` permission for Android 13+.

#### [MODIFY] [strings.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/values/strings.xml)
- Add necessary string resources for titles, welcome message, category names, descriptions, and article content.

## Verification Plan

### Automated Tests
- Build verification using `gradle_build` (`app:assembleDebug`).

### Manual Verification
- Deploy app to emulator/device using `deploy`.
- Verify Home Activity UI (logo, title, welcome message, category buttons).
- Click "Academic", "Events", and "Placements" buttons to verify Fragment loading.
- Click "Read More" to verify transition to Article Activity and check that notification appears with the article title.
- Verify Article Activity displays complete content and Back button functions correctly.
