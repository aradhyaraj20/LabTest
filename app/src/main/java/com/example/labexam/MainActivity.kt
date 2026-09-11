package com.example.labexam

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _: Boolean ->
        // Permission granted or denied handling if needed
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        val btnAcademic = findViewById<Button>(R.id.btnAcademic)
        val btnEvents = findViewById<Button>(R.id.btnEvents)
        val btnPlacements = findViewById<Button>(R.id.btnPlacements)

        // Load default fragment on start (Academic)
        if (savedInstanceState == null) {
            loadNewsFragment(
                "Academic Update",
                "Important updates regarding semester examinations, academic calendar, and class schedules.",
                "Here is the complete academic update article. All students are advised to check their respective departmental notice boards for detailed schedules regarding mid-semester examinations, assignments submission deadlines, and attendance criteria.",
                R.drawable.ic_academic
            )
        }

        btnAcademic.setOnClickListener {
            loadNewsFragment(
                "Academic Update",
                "Important updates regarding semester examinations, academic calendar, and class schedules.",
                "Here is the complete academic update article. All students are advised to check their respective departmental notice boards for detailed schedules regarding mid-semester examinations, assignments submission deadlines, and attendance criteria.",
                R.drawable.ic_academic
            )
        }

        btnEvents.setOnClickListener {
            loadNewsFragment(
                "Campus Events",
                "Annual tech fest, cultural fest, and sports meet registrations are now open!",
                "Here is the complete campus events article. Get ready for an action-packed semester with TechnoHack 2026, Rhythm Cultural Night, and Inter-College Sports Championship. Register with your faculty coordinators before Friday.",
                R.drawable.ic_events
            )
        }

        btnPlacements.setOnClickListener {
            loadNewsFragment(
                "Placement Drive",
                "Top tech companies visiting the campus for recruitment. Check eligibility criteria.",
                "Here is the complete placement drive article. Global Tech Solutions, Innovate Corp, and Data Dynamics are visiting campus next week. Eligible students with CGPA >= 7.5 must register on the placement portal by tomorrow evening.",
                R.drawable.ic_placements
            )
        }
    }

    private fun loadNewsFragment(title: String, description: String, content: String, imageResId: Int) {
        val fragment = NewsFragment.newInstance(title, description, content, imageResId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
