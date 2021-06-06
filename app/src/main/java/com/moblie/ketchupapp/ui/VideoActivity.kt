package com.moblie.ketchupapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.moblie.ketchupapp.R
import android.content.pm.ActivityInfo
import android.graphics.Typeface
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.moblie.ketchupapp.base.BaseVideoActivity
import com.moblie.ketchupapp.databinding.ActivityVideoBinding
import com.moblie.ketchupapp.databinding.ExoPlaybackControlViewYtBinding
import com.moblie.ketchupapp.ui.viewmodels.VideoActivityViewModel
import com.moblie.ketchupapp.ui.views.player.youtube.YouTubeOverlay
import com.moblie.ketchupapp.utils.DisplayUtils.toPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoActivity : BaseVideoActivity() {

    private var isVideoFullscreen = false
    private var currentVideoId = -1

    // View bindings
    private lateinit var binding: ActivityVideoBinding
    private lateinit var controlsBinding: ExoPlaybackControlViewYtBinding

    private val viewModel: VideoActivityViewModel by viewModels()

    // Views (to eliminate repeating 'binding.'-prefixes)
    private lateinit var ytOverlay: YouTubeOverlay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup layout views with View binding
        binding = ActivityVideoBinding.inflate(layoutInflater)
        val view = binding.root
        val controls = view.findViewById<ConstraintLayout>(R.id.exo_controls_root)
        controlsBinding = ExoPlaybackControlViewYtBinding.bind(controls)
        setContentView(view)

        ytOverlay = binding.ytOverlay
        videoPlayer = binding.previewPlayerView

        initDoubleTapPlayerView()
        startNextVideo()

        controlsBinding.fullscreenButton.setOnClickListener {
            toggleFullscreen()
        }
    }

    private fun initDoubleTapPlayerView() {
        ytOverlay
            // Uncomment this line if the DoubleTapPlayerView is not set via XML
            //.playerView(previewPlayerView)
            .performListener(object : YouTubeOverlay.PerformListener {
                override fun onAnimationStart() {
                    binding.previewPlayerView.useController = false
                    ytOverlay.visibility = View.VISIBLE
                }
                override fun onAnimationEnd() {
                    ytOverlay.visibility = View.GONE
                    binding.previewPlayerView.useController = true
                }
            })

        binding.previewPlayerView.doubleTapDelay = 800
        // Uncomment this line if the PlayerDoubleTapListener is not set via XML
        // previewPlayerView.controller(ytOverlay)
    }


    private fun startNextVideo() {
        releasePlayer()
        initializePlayer()
        ytOverlay.player(player!!)
        val title = intent.extras?.getString(EXTRA_TITLE)
        val id = intent.extras?.getLong(EXTRA_ID)
        if(title != null && id != null){
            viewModel.getVideo(title, id)
        }
        viewModel.url.observe(this){
            buildMediaSource(Uri.parse(it))
        }
        player?.play()
    }

    private fun toggleFullscreen() {
        if (isVideoFullscreen) {
            setFullscreen(false)
            if (supportActionBar != null) {
                supportActionBar?.show()
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            isVideoFullscreen = false
        } else {
            setFullscreen(true)
            if (supportActionBar != null) {
                supportActionBar?.hide()
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            isVideoFullscreen = true
        }
    }

    override fun onBackPressed() {
        if (isVideoFullscreen) {
            toggleFullscreen()
            return
        }
        super.onBackPressed()
    }

    companion object {
        const val EXTRA_ID = ".hqVid.id"
        const val EXTRA_TITLE = ".hqVid.title"
    }
}