# Walkthrough - CampusNews Application (Logo & Images Update)

I have successfully updated the **CampusNews** application with a dedicated college logo and unique update/news images for each category.

## Updates Made

### 1. Custom Vector Logo & Category Images (`res/drawable/`)
- **[ic_logo.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/drawable/ic_logo.xml)**: Dedicated college logo displayed in Home Activity header.
- **[ic_academic.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/drawable/ic_academic.xml)**: Unique academic news/update image.
- **[ic_events.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/drawable/ic_events.xml)**: Unique campus events news/update image.
- **[ic_placements.xml](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/res/drawable/ic_placements.xml)**: Unique placement drive news/update image.

### 2. Fragment & Activity Image Integration
- **[MainActivity.kt](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/java/com/example/labexam/MainActivity.kt)**: Passes the appropriate category image resource (`ic_academic`, `ic_events`, `ic_placements`) when loading `NewsFragment`.
- **[NewsFragment.kt](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/java/com/example/labexam/NewsFragment.kt)**: Displays the category image and passes it via Intent to `ArticleActivity`.
- **[ArticleActivity.kt](file:///C:/Users/aradh/AndroidStudioProjects/LabEXam/app/src/main/java/com/example/labexam/ArticleActivity.kt)**: Receives and displays the corresponding update image alongside the full article content.

---

## Verification Results

### Automated Tests
- **Build**: Successfully executed `app:assembleDebug` with **0 errors**.
