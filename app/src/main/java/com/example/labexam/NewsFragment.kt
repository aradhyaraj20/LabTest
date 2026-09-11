package com.example.labexam

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment

class NewsFragment : Fragment() {

    private var categoryTitle: String? = null
    private var shortDescription: String? = null
    private var fullContent: String? = null
    private var imageResId: Int = R.drawable.ic_logo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryTitle = it.getString(ARG_TITLE)
            shortDescription = it.getString(ARG_DESC)
            fullContent = it.getString(ARG_CONTENT)
            imageResId = it.getInt(ARG_IMAGE, R.drawable.ic_logo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        val tvTitle = view.findViewById<TextView>(R.id.tvCategoryTitle)
        val tvDesc = view.findViewById<TextView>(R.id.tvShortDescription)
        val ivNews = view.findViewById<ImageView>(R.id.ivNewsImage)
        val btnReadMore = view.findViewById<Button>(R.id.btnReadMore)

        tvTitle.text = categoryTitle ?: "Campus Update"
        tvDesc.text = shortDescription ?: "No description available."
        ivNews.setImageResource(imageResId)

        btnReadMore.setOnClickListener {
            // Trigger notification
            sendNotification(categoryTitle ?: "Campus News")

            // Open Article Activity using Intent
            val intent = Intent(requireContext(), ArticleActivity::class.java).apply {
                putExtra("EXTRA_TITLE", categoryTitle)
                putExtra("EXTRA_CONTENT", fullContent)
                putExtra("EXTRA_IMAGE", imageResId)
            }
            startActivity(intent)
        }

        return view
    }

    private fun sendNotification(title: String) {
        val channelId = "campus_news_channel_high"
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Campus News Popup Notifications"
            val descriptionText = "High priority notifications for campus updates and articles"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
                enableVibration(true)
            }
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(requireContext(), ArticleActivity::class.java).apply {
            putExtra("EXTRA_TITLE", title)
            putExtra("EXTRA_CONTENT", fullContent)
            putExtra("EXTRA_IMAGE", imageResId)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            requireContext(),
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(requireContext(), channelId)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setContentTitle("New Article Opened")
            .setContentText(title)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(requireContext())
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_DESC = "arg_desc"
        private const val ARG_CONTENT = "arg_content"
        private const val ARG_IMAGE = "arg_image"

        fun newInstance(title: String, desc: String, content: String, imageResId: Int): NewsFragment {
            return NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESC, desc)
                    putString(ARG_CONTENT, content)
                    putInt(ARG_IMAGE, imageResId)
                }
            }
        }
    }
}
